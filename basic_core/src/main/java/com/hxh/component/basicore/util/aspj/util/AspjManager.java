package com.hxh.component.basicore.util.aspj.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.Base.view.AppCompartAutoLayoutActivity;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.annotationenum.ShowType;
import com.hxh.component.ui.alertview.AlertView;
import com.hxh.component.ui.alertview.OnItemClickListener;

import java.lang.ref.WeakReference;

import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;


/**
 * @Title Aspj管理类，如果用了CheckLoginManager，且登录页面是一个DialogFragment，而且需要在关闭时刷新数据，那么请
 * 注册一个Listener,调用registerOnDissmissListener ，详情请见下面
 * @UpdateTime 2017/8/3
 */
public class AspjManager {
    //    private static AspjManager INSTANCE  = null;
    //
    //    public static AspjManager getInstance()
    //    {
    //        if(null == INSTANCE)
    //        {
    //            INSTANCE = new AspjManager();
    //        }
    //        return INSTANCE;
    //    }

    public static class CheckLoginManager {

        private static volatile CheckLoginManager singleton = null;

        private CheckLoginManager() {
        }

        public static CheckLoginManager getInstance() {
            if (singleton == null) {
                synchronized (CheckLoginManager.class) {
                    if (singleton == null) {
                        singleton = new CheckLoginManager();
                    }
                }
            }
            return singleton;
        }


        private WeakReference<Fragment> v4Fragment;
        private WeakReference<android.app.Fragment> v7Fragment;
        private WeakReference<Activity> v7Activity;
        private WeakReference<ActivityCompat> v4Activity;
        private View L_view;
        private String mRouterPath;

        private DialogInterface.OnDismissListener mOnDissmissListener;

        /**
         * @param listener
         * @Title 如果你的登录是一个Dialog类型的，那么请注册一个OnDissMissListner
         * 因为DialogFragment通过show显示，但是这个Fragment(即使全屏)也不会触发被盖住的Fragment的生命周期(setUserHint等)
         * 但是你又需要在关闭后刷新数据，所以提供此方法，如果你不满足我说的，那么不用注册
         */
        public void registerOnDissmissListener(DialogInterface.OnDismissListener listener) {
            this.mOnDissmissListener = listener;
        }

        public void showLoginview(ShowType type) {
            switch (type) {
                case DIALOG:
                    if (!Utils.Text.isEmpty(this.mTipDialog)) {
                        addTipDialogListener();
                        this.mTipDialog.show();
                    } else
                        buildLoginDialog().show();
                    break;
                case DIALOG_AND_NORMAL:
                    if (!Utils.Text.isEmpty(this.mTipDialog)) {
                        addTipDialogListener();
                        this.mTipDialog.show();
                    } else
                    {
                        buildLoginDialog().setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        addTipDialogListener();
                                    }
                                })
                                .create()
                                .show();

                    }



                    break;
                case NORMAL://展示Activity或者Fragment
                    navigationToLogin();
                    break;
                case TOAST:
                    Utils.Toast.toast("您尚未登录");

                    break;
            }
        }

        private void addTipDialogListener() {
            this.mTipDialog.addOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(String item, int position) {
                    if(-1 != position)
                        navigationToLogin();
                }
            });
        }

        private void navigationToLogin() {
            if (null != mRouterPath && !"".equals(mRouterPath)) {
                switch (mRouterType) {
                    case MODE_ACTIVTY:

                        ARouter.getInstance().build(mRouterPath).navigation();
                        break;
                    case MODE_FRAGMENT:
                        Object obj = ARouter.getInstance().build(mRouterPath).navigation();

                        //obj instanceof I ? ((I) obj) : null;
                        BottomSheetDialogFragment ins = obj instanceof BottomSheetDialogFragment ? ((BottomSheetDialogFragment) obj) : null;
                        if (null != ins) {
                            ins.show(AppManager.getCurrentCompatActivity().getSupportFragmentManager(), "");
                            if (null != mOnDissmissListener) {
                                ins.getDialog().setOnDismissListener(mOnDissmissListener);
                            }
                        } else {
                            Fragment fragment = obj instanceof Fragment ? ((Fragment) obj) : null;
                            FragmentTransaction tr = AppManager.getCurrentCompatActivity().getSupportFragmentManager().beginTransaction();
                            tr.add(fragment, "");
                            tr.commit();

                        }

                        break;
                }
            } else {
                navigationToLoginView();
            }
        }

        private void navigationToLoginView() {
            if (null != v4Activity) {

                AppManager.getCurrentActivity().startActivity(new Intent(AppManager.getCurrentActivity(), v4Activity.get().getClass()));
            } else if (null != v4Fragment) {
                if (AppManager.getCurrentCompatActivity() instanceof ISupportActivity) {
                    AppCompartAutoLayoutActivity support = (AppCompartAutoLayoutActivity) AppManager.getCurrentCompatActivity();
                    if (null != support && v4Fragment instanceof ISupportFragment) {
                        support.start(((ISupportFragment) v4Fragment));
                    }
                }
            } else if (null != v7Activity) {
                AppManager.getCurrentActivity().startActivity(new Intent(AppManager.getCurrentActivity(), v7Activity.get().getClass()));
            } else if (null != v7Fragment) {
                if (AppManager.getCurrentCompatActivity() instanceof ISupportActivity) {
                    AppCompartAutoLayoutActivity support = (AppCompartAutoLayoutActivity) AppManager.getCurrentCompatActivity();
                    if (null != support && v7Fragment instanceof ISupportFragment) {
                        support.start(((ISupportFragment) v7Fragment));
                    }
                }
            }
        }

        @Deprecated
        public void registerLoginView(Fragment fragment) {

            if (null == v4Fragment)
                mRouterPath = null;
            this.v4Fragment = new WeakReference<Fragment>(fragment);
        }

        @Deprecated
        public void registerLoginView(android.app.Fragment fragment) {
            if (null == v7Fragment)
                mRouterPath = null;
            this.v7Fragment = new WeakReference<android.app.Fragment>(fragment);
        }

        @Deprecated
        public void registerLoginView(Activity activity) {
            mRouterPath = null;

            if (null == v7Activity) this.v7Activity = new WeakReference<Activity>(activity);
        }

        @Deprecated
        public void registerLoginView(ActivityCompat activity) {
            mRouterPath = null;
            if (null == v4Activity) this.v4Activity = new WeakReference<ActivityCompat>(activity);
        }

        public static final int MODE_ACTIVTY = 0x1;
        public static final int MODE_FRAGMENT = 0x2;
        private int mRouterType;
        private AlertView mTipDialog;

        public void registerLoginView(String routerPath, int routerTargerType) {
            this.mRouterPath = routerPath;
            this.mRouterType = routerTargerType;
            this.v4Fragment = null;
            this.v7Fragment = null;
            this.v4Activity = null;
            this.v7Activity = null;
        }

        public void registerLoginViewTipDialog(AlertView dialog) {
            this.mTipDialog = dialog;
        }

        private AlertDialog.Builder buildLoginDialog() {
            return new AlertDialog.Builder(AppManager.getCurrentActivity())
                    .setMessage("你尚未登录");
        }

        /**
         * 检查是否已经注册过了
         */
        private boolean checkLoginViewIsRegisrered() {
            if (null == v4Fragment && null == v7Fragment
                    && null == v4Activity &&
                    null == v7Activity && null == L_view) {
                return false;
            }
            return true;
        }

    }


}
