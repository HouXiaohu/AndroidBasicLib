package com.hxh.component.basicore.Base.delegate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.interfaces.IToolBarRelated;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.Base.topbar.ActionBarProvider;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.R;
import com.hxh.component.basicore.util.AppManager;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.annotation.Safe;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * ToolBar相关功能
 * 两种方法开启：
 * 1. new ToolBarDelegate(传入ActionBarConfig)
 * 2. instance = new ToolBarDelegate()  -> instance.init(传入ActionBarConfig)
 */
public class ToolBarDelegate_FullScreenMode implements IToolBarRelated {

    public ToolBarDelegate_FullScreenMode() {
    }

    /**
     * 你可以new的时候不用传入ActionBarConfig,
     *
     * @param config
     */
    public ToolBarDelegate_FullScreenMode(ActionBarConfig config) {
        //        if (null == config) {
        //            throw new IllegalStateException("if you enable toolbar,Then you must provide ActionBarConfig!");
        //        }
        this.mActionbarconfig = config;
    }


    private ActionBarConfig mActionbarconfig;
    private View layoutrootView;
    //跟布局
    private AutoRelativeLayout rootView;
    //BackView配置
    private ActionBarConfig.BackViewConfig mBackViewConfig;
    private OnToolbarClickListener clicklistener;

    //Toolbar的Title
    private TextView tv_title;

    //右边的三个view,分别是按钮型的
    private Button rightview_btn;//系统默认的右边布局
    private LinearLayout rightview_lingroup;
    private ImageView rightview_iv_img;

    //左边的两个View（TextView 和 ImageView）
    private ImageButton leftview_ib_back;
    private TextView leftview_ib_back_text;


    private int mCurrentClickRightButtonsPosition = 0;
    private View view_splitline;
    //region 关联ToolBar到View上
    /**
     * 调用步骤：
     * 1. new 此对象
     * 2. init
     * 3. 调用此方法
     * 将toolbar关联到某个view上,必须在onCreateView中调用
     *
     * @param rootView
     */
    private Toolbar toolbar;

    public void fetchToView(View rootView, Fragment fragment) {
        fetchToolBar(rootView);
        ((AppCompatActivity) fragment.getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void fetchToView(View rootView, AppCompatActivity activity) {
        fetchToolBar(rootView);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void fetchToolBar(View rootView) {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (null == toolbar) {
            throw new IllegalStateException("There is no id is 'toolbar' view in your layout");
        }
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.addView(layoutrootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        toolbar.setVisibility(View.VISIBLE);
    }
    //endregion

    public void init(ActionBarConfig config) {
        this.mActionbarconfig = config;
        initRootView();
        initToolBar();
    }

    public void init() {
        initRootView();
        initToolBar();
    }


    private void initRootView() {
        layoutrootView = LayoutInflater.from(Utils.getApplicationContext()).inflate(R.layout.layout_default_toolbar_fullscreenmode, null);
        rootView = (AutoRelativeLayout) layoutrootView.findViewById(R.id.frame_toolbar);

        //左边
        leftview_ib_back = (ImageButton) rootView.findViewById(R.id.leftview_ib_back);
        leftview_ib_back_text = (TextView) rootView.findViewById(R.id.leftview_ib_back_text);

        //右边
        rightview_iv_img = (ImageView) rootView.findViewById(R.id.rightview_iv_img);
        rightview_lingroup = (LinearLayout) rootView.findViewById(R.id.rightview_lingroup);
        rightview_btn = (Button) rootView.findViewById(R.id.btn_rightview);

        //标题
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        //分割线
        view_splitline = rootView.findViewById(R.id.view_splitline);
    }



    private void initToolBar() {
        if (null == mActionbarconfig) {
            return;
        }
        clicklistener = new OnToolbarClickListener();
        //获取全局的ActionBar
        ActionBarProvider actionBarProvider = CoreLib.getInstance().getAppComponent().globalActionBarProvider();
        
        //region 左边布局
        if (mActionbarconfig.isEnable_backview()) {
            //region 使用用户自定义的Backview
            if (null != mActionbarconfig.getBackViewConfig())
            {
                mBackViewConfig = mActionbarconfig.getBackViewConfig();
                View view = mBackViewConfig.getBackView();
                if (null != view) {
                    view.setLayoutParams(getBackViewDefaultLayoutParam());
                    settingBackViewGravity(view);
                    rootView.addView(view);
                }
                else
                {
                    boolean isShowBackImg = false, isShowBackTitle = false;
                    Bitmap bacimg = null;
                    String bactitle = null;
                    int backviewColor = getColor(mBackViewConfig.getColor());
                    if (null != mBackViewConfig.getBackBitmap()) {
                        isShowBackImg = true;
                        bacimg = mBackViewConfig.getBackBitmap();
                    }
                    if (null != mBackViewConfig.getBackTitle() && !"".equals(mBackViewConfig.getBackTitle())) {
                        isShowBackTitle = true;
                        bactitle = mBackViewConfig.getBackTitle();
                    }


                    setBackView_ImageAndTextVisible(isShowBackTitle, isShowBackImg);
                    if (Utils.Text.isEmpty(mActionbarconfig.getTitleText()))
                    {
                        setBackViewTextIsLong();
                        //btn_defaultBackView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    } else {
                        setBackViewTitleMaxNumber();
                    }
                    setBackView(bacimg, bactitle, backviewColor);
                    leftview_ib_back.setOnTouchListener(clicklistener);
                    leftview_ib_back_text.setOnTouchListener(clicklistener);
                }


            }
            //endregion

            //region 使用全局BackView（由GlobalActionBarConfig 提供）,如果用户没有提供，那么将会不显示返回
            else {
                leftview_ib_back.setOnTouchListener(clicklistener);
                leftview_ib_back_text.setOnTouchListener(clicklistener);
                //region 如果用户指定了BackColor，那么改BackImg和BackTitle
                if (1234 != mActionbarconfig.getBackViewColor()) {
                    //使用全局
                    showBackView();
                    int backviewColor = getColor( mActionbarconfig.getBackViewColor());

                    if (null != actionBarProvider) {
                        setBackView(actionBarProvider.getM_backImg(), actionBarProvider.getM_backTitle(), actionBarProvider.getBackColor(), backviewColor);
                    }
                }
                //endregion
                //region 如果用户没有指定BackColor，只是想用默认的，不进行任何修改
                else if (null != actionBarProvider) {

                    showBackView();
                    //使用全局，包含图标，文字，颜色风格
                    int color = getColor(actionBarProvider.getBackColor());

                    //如果Color值是非颜色值，那么去返回图标中取主色
                    if (null != actionBarProvider.getM_backImg()) {
                        setBackView_noReplace(actionBarProvider.getM_backImg(), actionBarProvider.getM_backTitle(), color);
                    } else {
                        setBackView_noReplace(null, actionBarProvider.getM_backTitle(), color);
                    }

                }
                //endregion

            }
            //endregion
        } else {
            //关闭BackView
            hideBackView();

        }
        //endregion


        //region 右边
        if (mActionbarconfig.isEnable_rightview()) {
            if (1234 != mActionbarconfig.getRight_buttonImgResId()) {
                setActionBarRight(mActionbarconfig.getRight_buttonImgResId());
            } else if (null != mActionbarconfig.getRight_buttontitle()) {
                setActionBarRight(mActionbarconfig.getRight_buttontitle(), mActionbarconfig.getRight_buttontitleColor());
            } else if (null != mActionbarconfig.getRight_imgResIds()) {
                setActionBarRight(mActionbarconfig.getRight_imgResIds());
            }
        }
        //endregion

        //region 背景，标题
        if (null != mActionbarconfig.getTitleText()) {
            setActionBar_Title(mActionbarconfig.getTitleText());
            if (1234 != mActionbarconfig.getTitleColor()) {
                int color = getColor(mActionbarconfig.getTitleColor());
                tv_title.setTextColor(color);

            } else if (null != actionBarProvider) {
                try {
                    tv_title.setTextColor(ContextCompat.getColor(rootView.getContext(), actionBarProvider.getTitleColor()));
                } catch (Exception e) {
                    tv_title.setTextColor(actionBarProvider.getTitleColor());
                }
            }
        }

        // 背景
        if (null == mActionbarconfig.getBackgroundDrawable() && 1234 == mActionbarconfig.getBackgroundColor()) {
            if (null != actionBarProvider) {
                if (null == mActionbarconfig.getBackgroundDrawable()) {
                    int color = getColor( actionBarProvider.getBackgroundColor());

                    rootView.setBackgroundColor(color);

                } else {
                    if (Build.VERSION.SDK_INT >= 16) {
                        rootView.setBackground(actionBarProvider.getBackgroundDrawable());
                    } else
                        rootView.setBackgroundDrawable(actionBarProvider.getBackgroundDrawable());
                }
            }
        } else {
            if (null == mActionbarconfig.getBackgroundDrawable()) {
                if (mActionbarconfig.getBackgroundColor() != -1) {
                    int color = -1;
                    if (mActionbarconfig.getBackgroundColor() == 0) {
                        color = Color.WHITE;
                    } else {
                        color = getColor(mActionbarconfig.getBackgroundColor());
                    }
                    rootView.setBackgroundColor(color);
                } else {
                    rootView.setBackgroundColor(Color.WHITE);
                }
            } else {
                if (Build.VERSION.SDK_INT >= 16) {
                    rootView.setBackground(mActionbarconfig.getBackgroundDrawable());
                } else
                    rootView.setBackgroundDrawable(mActionbarconfig.getBackgroundDrawable());
            }
        }

        //endregion

        //region 是否开启splitLine
        if (mActionbarconfig.isEnable_splitline()) {
            view_splitline.setVisibility(View.VISIBLE);
        } else {
            view_splitline.setVisibility(View.GONE);
        }

        //endregion
    }

    private class OnRightImgesClicListener implements View.OnClickListener {
        private int position;

        public OnRightImgesClicListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (null != mActionbarconfig.getListener()) {
                mActionbarconfig.getListener().onRightButtonsClick(this.position);
            }
        }
    }


    private void setActionBarRight(int resid) {
        rightview_lingroup.setVisibility(View.VISIBLE);
        rightview_iv_img.setVisibility(View.VISIBLE);
        rightview_iv_img.setImageBitmap(Utils.BitmapUtils.getBitmap(resid, 38, 38));
        rightview_btn.setVisibility(View.GONE);
        rightview_lingroup.setOnClickListener(clicklistener);
        //  rightview_btn = null;
    }

    private void setActionBarRight(Drawable resid) {
        rightview_lingroup.setVisibility(View.VISIBLE);
        rightview_iv_img.setVisibility(View.VISIBLE);
        rightview_iv_img.setImageDrawable(resid);
        rightview_btn.setVisibility(View.GONE);
        rightview_lingroup.setOnClickListener(clicklistener);
        //    rightview_btn = null;
    }

    private void setActionBarRight(int[] resids) {
        rightview_lingroup.setVisibility(View.VISIBLE);
        rightview_iv_img.setVisibility(View.GONE);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(48, 48);
        param.rightMargin = 20;
        param.gravity = Gravity.CENTER_VERTICAL;
        for (int i = 0; i < resids.length; i++) {

            int resid = resids[i];
            ImageView imageView = new ImageView(rootView.getContext());
            imageView.setLayoutParams(param);

            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(resid);


            imageView.setOnClickListener(new OnRightImgesClicListener(i));
            rightview_lingroup.addView(imageView);
        }
        //        rightview_iv_img = null;
        //        rightview_btn = null;
    }

    private void setActionBarRight(String title, int titlecolor) {
        rightview_lingroup.setVisibility(View.GONE);
        rightview_iv_img.setVisibility(View.GONE);
        rightview_btn.setVisibility(View.VISIBLE);
        rightview_btn.setText(title);
        int titleColor = 0;
        try {
            titleColor = ContextCompat.getColor(rootView.getContext(), titlecolor);
        } catch (Exception e) {
            titleColor = titlecolor;
        }
        rightview_btn.setTextColor(titleColor);
        rightview_btn.setOnClickListener(clicklistener);
        //        rightview_lingroup = null;
        //        rightview_iv_img = null;
    }

    public void settingBackViewGravity(View view) {
        if (null != view) {
            if (view instanceof TextView) {
                ((TextView) view).setGravity(Gravity.CENTER);
            } else if (view instanceof Button) {
                ((Button) view).setGravity(Gravity.CENTER);
            }
        }
    }

    public FrameLayout.LayoutParams getBackViewDefaultLayoutParam() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = 20;
        return params;
    }


    class OnToolbarClickListener implements View.OnClickListener, View.OnTouchListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
           if (id == R.id.btn_rightview || id == R.id.rightview_lingroup) {
                if (null != mActionbarconfig.getListener()) {
                    mActionbarconfig.getListener().onRightClick();
                }
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                if (v.getId() == R.id.leftview_ib_back_text || v.getId() == R.id.leftview_ib_back) {
                    setBackViewAlpha(1f);
                    if (null != mActionbarconfig.getListener()) {
                        mActionbarconfig.getListener().onLeftClick();
                    } else {
                        AppManager.back();
                    }
                }

            }
            if (action == MotionEvent.ACTION_DOWN) {
                if (v.getId() == R.id.leftview_ib_back_text || v.getId() == R.id.leftview_ib_back) {
                    setBackViewAlpha(0.5f);
                }
            }
            return true;
        }
    }


    //region 以下是你可以获得的
    public View getToolBarRootView() {
        return layoutrootView;
    }

    /**
     * 设置ActionbarTitle
     *
     * @param title
     */
    public void setActionBar_Title(String title) {
        if (!Utils.Text.isEmpty(title)) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }
    }


    public View getActionbar_rightView() {
        if (null != rightview_iv_img && View.VISIBLE == rightview_iv_img.getVisibility()) {
            return rightview_iv_img;
        } else if (null != rightview_btn && View.VISIBLE == rightview_btn.getVisibility() ) {
            return rightview_btn;
        } else if (null != rightview_lingroup && View.VISIBLE == rightview_lingroup.getVisibility()) {
            return rightview_lingroup;
        }
        return null;
    }

    public ImageView getActionbar_rightImageView() {
        return rightview_iv_img;
    }

    public LinearLayout getActionbar_rightImageViewButtons() {
        return rightview_lingroup;
    }


    public TextView getActionbar_title() {
        return tv_title;
    }


    public void setActionBar_Title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public ActionBarConfig getActionBarConfig() {
        return mActionbarconfig;
    }

    @Safe
    public void setBackViewTitle(String title) {
        setBackViewText(title);
    }

    @Safe
    public void setActionbar_rightText(String text) {
        setActionBarRight(text, mActionbarconfig.getRight_buttontitleColor());
        //  rightview_btn.setText(text);
    }

    @Safe
    public void setActionbar_rightImg(int resId) {
        setActionBarRight(resId);
        //rightview_iv_img.setImageResource(resId);
    }

    @Safe
    public void setActionbar_rightImg(Drawable drawable) {
        setActionBarRight(drawable);
        //rightview_iv_img.setImageDrawable(drawable);
    }

    public void setActionBarConfig(ActionBarConfig config) {
        this.mActionbarconfig = config;
        if (null == rootView || null == rightview_btn || null == rightview_lingroup) {
            init();
        } else {
            initToolBar();
        }
    }



    //endregion


    private int getColor(int resid)
    {
        int color = -1;
        try {
            color = ContextCompat.getColor(rootView.getContext(), resid);
            if(-1 == color)
            {
               return  resid;
            }
            return color;
        } catch (Exception e) {
            return resid;
        }
    }

    //region 左边设置方法
    private void showBackView()
    {
        this.leftview_ib_back.setVisibility(View.VISIBLE);
        this.leftview_ib_back_text.setVisibility(View.VISIBLE);
    }

    private void hideBackView()
    {
        this.leftview_ib_back.setVisibility(View.GONE);
        this.leftview_ib_back_text.setVisibility(View.GONE);
    }

    private void setBackView_Color(int color) {
        this.leftview_ib_back_text.setTextColor(color);
        Bitmap map = BitmapFactory.decodeResource(Utils.getApplicationContext().getResources(), R.mipmap.icon_back_white);
        this.leftview_ib_back.setImageBitmap(Utils.BitmapUtils.replaceBitmapColor(map, Color.WHITE, color));
    }

    /**
     * 配置BackView
     *
     * @param backMap   返回图标
     * @param backTitle 返回文字
     * @param color     整体颜色
     */
    private void setBackView(Bitmap backMap, String backTitle, int color) {
        if (null == backMap) {
            this.leftview_ib_back.setVisibility(View.GONE);
        } else {
            this.leftview_ib_back.setVisibility(View.VISIBLE);
            //
            //int color1 = Color.WHITE;
            this.leftview_ib_back.setImageBitmap(Utils.BitmapUtils.replaceBitmapColor(backMap, Color.WHITE, color));
        }
        if (null != backTitle || !"".equals(backTitle)) {


            this.leftview_ib_back_text.setTextColor(color);
            this.leftview_ib_back_text.setText(backTitle);
        } else {
            this.leftview_ib_back_text.setVisibility(View.GONE);
        }

    }

    private void setBackViewTitleMaxNumber()
    {
        this.leftview_ib_back_text.setSingleLine();
        this.leftview_ib_back_text.setMaxEms(5);
        this.leftview_ib_back_text.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 配置BackView
     * @param backMap    返回图标
     * @param backTitle    返回文字
     */
    private void setBackView_noReplace(Bitmap backMap,String backTitle,int color)
    {

        if (null == backMap) {
            this.leftview_ib_back.setVisibility(View.GONE);
        } else
        {
            this.leftview_ib_back.setVisibility(View.VISIBLE);
            this.leftview_ib_back.setImageBitmap(backMap);
        }
        if(null!=backTitle || !"".equals(backTitle) )
        {
            this.leftview_ib_back_text.setTextColor(color);
            this.leftview_ib_back_text.setText(backTitle);
        }else
        {
            this.leftview_ib_back_text.setVisibility(View.GONE);
        }

    }


    /**
     *
     * 配置BackView
     * @param backMap       返回图标
     * @param backTitle     返回文字
     * @param oldColor      原来图标中的主色值
     * @param newColor      替换为什么颜色值
     */
    private void setBackView(Bitmap backMap,String backTitle,int oldColor,int newColor)
    {
        //0  65536
        // this.leftview_ib_back_text.setTextColor(newColor);
        if (null == backMap) {
            this.leftview_ib_back.setVisibility(View.GONE);
        } else
        {
            this.leftview_ib_back.setVisibility(View.VISIBLE);
            this.leftview_ib_back.setImageBitmap(Utils.BitmapUtils.replaceBitmapColor(backMap, oldColor,newColor));
        }
        if(null!=backTitle || !"".equals(backTitle) )
        {
            this.leftview_ib_back_text.setTextColor(newColor);
            this.leftview_ib_back_text.setText(backTitle);
        }else
        {
            this.leftview_ib_back_text.setVisibility(View.GONE);
        }
    }



    private void setBackView_ImageAndTextVisible(boolean isshowbacktext, boolean isshowbackimg)
    {
        this.leftview_ib_back_text.setVisibility(isshowbacktext==true?View.VISIBLE:View.GONE);
        this.leftview_ib_back.setVisibility(isshowbackimg==true?View.VISIBLE:View.GONE);
    }


    private void setBackViewText(String text)
    {
        this.leftview_ib_back_text.setVisibility(View.VISIBLE);
        this.leftview_ib_back_text.setText(text);
    }


    private TextView getBackTextView()
    {
        return this.leftview_ib_back_text;
    }
    
    
    private void setBackViewTextIsLong()
    {
        AutoRelativeLayout.LayoutParams params = (AutoRelativeLayout.LayoutParams) this.leftview_ib_back_text.getLayoutParams();
        params.removeRule(RelativeLayout.LEFT_OF);
        this.leftview_ib_back_text.setLayoutParams(params);
    }

    private void setBackViewAlpha(float alpha)
    {
        this.leftview_ib_back.setAlpha(alpha);
        this.leftview_ib_back_text.setAlpha(alpha);
    }
    //endregion




}
