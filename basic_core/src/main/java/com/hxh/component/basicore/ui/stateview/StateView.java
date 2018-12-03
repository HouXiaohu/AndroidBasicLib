package com.hxh.component.basicore.ui.stateview;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.util.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 状态View
 * 会覆盖当前屏幕View
 */
public class StateView  {
    private List<Pair<Integer, View>> mStates;
    private WeakReference<Activity> mActivity;
    private Animation outAnim;
    private Animation inAnim;
    private ViewGroup decorView;//activity的根View,我要把我的RootView放入进去
    private List<EventHook> mEventHooks;
    private ViewGroup.MarginLayoutParams decorViewLayoutParam;
    private View mCurrentView;
    private boolean isShowing;
    private int mCurrentStateType;
    private HashMap<String,ViewGroup> mStoreViews;
    public StateView(List<Pair<Integer, View>> states) {
        this.mStates = states;
    }

    private void init()
    {
        if(null == mStoreViews){
            mStoreViews = new HashMap<>();
        }
        this.mActivity = new WeakReference<>(AppManager.getCurrentActivity());
        if(mStoreViews.containsKey(getActivityName(mActivity.get()))){
            decorView = mStoreViews.get(getActivityName(mActivity.get()));
        }else{
            decorView = (ViewGroup) ((Activity) mActivity.get()).getWindow().getDecorView().findViewById(android.R.id.content);
            mStoreViews.put(getActivityName(this.mActivity.get()),decorView);
        }

        setLimitHeight(66);
    }

    private String getActivityName(Activity ac){
        return ac.getComponentName().getClassName();
    }

    private void showState(int type){
        showState(type,false);
    }

    public void showState(int type,boolean isAnim){
        init();

        this.mCurrentStateType = type;
        this.mCurrentView = getViewForType(type);
        if(null == mCurrentView){
            throw new IllegalArgumentException("没有指定type的View");
        }
        if(null != mEventHooks){
            for (EventHook even : mEventHooks) {
                setEventHook(even.onBindView(),even);
            }
        }

        decorView.removeView(mCurrentView);
        decorView.addView(mCurrentView);
        if(null != inAnim){
            mCurrentView.startAnimation(inAnim);
        }
    }

    public void closeState(){
        this.mCurrentStateType = -1;
        if(null != outAnim){
            outAnim.setAnimationListener(outAnimListener);
            mCurrentView.startAnimation(outAnim);
        }else{
            removeViewInner();
        }
    }

    private void removeViewInner()
    {
        decorView.removeView(mCurrentView);
        isShowing = false;
    }

    private void setEventHook(int[] views,final EventHook even ) {
        for (int id : views) {
            View view = mCurrentView.findViewById(id);
            if(null != view){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        even.onEvent(v);
                    }
                });
            }
        }
    }

    private View getViewForType(int type)
    {
        for (Pair<Integer, View> item : mStates) {
            if(item.first == type){
                return item.second;
            }
        }
        return null;
    }

    public void setLimitHeight(int heightDp){
        if(null == decorViewLayoutParam){
            if(null != decorView){
                decorViewLayoutParam = (ViewGroup.MarginLayoutParams) decorView.getLayoutParams();
            }else{
                decorViewLayoutParam = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
        }
        decorViewLayoutParam.topMargin =  Utils.Dimens.dpToPxInt(heightDp);
    }

    public void registerEventHook(EventHook hook)
    {
        if(null == mEventHooks){
            mEventHooks = new ArrayList<>();
        }
        mEventHooks.add(hook);
    }

    public static class Builder {
        public Builder(Context context){
            this.context = context;
        }

        private Context context;
        private List<Pair<Integer, View>> mStates = null;
        private Animation outAnim;
        private Animation inAnim;

        /**
         * 第一个参数为 in  第二个为out
         * @param anim
         * @return
         */
        public Builder addAnimation(Animation... anim){
            if(anim.length > 2 ){
                throw new IllegalArgumentException("最多只能设置两个动画");
            }
            this.inAnim = anim[0];
            this.outAnim = anim[1];
            return this;
        }

        public Builder addState(Integer key, View view) {
            checkState();
            mStates.add(new Pair<Integer, View>(key, view));
            return this;
        }

        public Builder addState(Integer key, int layoutId) {
            checkState();
            mStates.add(new Pair<Integer, View>(key, Utils.Resource.getView(context,layoutId)));
            return this;
        }

        public StateView build() {
            return new StateView(mStates);
        }

        private void checkState() {
            if (null == mStates) {
                mStates = new ArrayList<>();
            }
        }
    }


    private Animation.AnimationListener outAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            removeViewInner();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


}
