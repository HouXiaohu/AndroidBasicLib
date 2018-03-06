package com.hxh.component.basicore.ui.statelayout;

import java.util.Map;

/**
 * Created by hxh on 2018/2/28.
 */

public class StateLayout {

    private StateLayoutBuild mStateBuild;
    public StateLayout(StateLayoutBuild build) {
        this.mStateBuild = build;
    }

    public void init()
    {
        if(mStateBuild.mStateMapResId!=null)
        {

        }
    }



    public class StateLayoutBuild {

        public StateLayoutBuild() {
        }

        private int mErrorLayoutResId=-1,mLoadingLayoutResId =-1,mNoNetWorkLayoutResId =-1;
        private Map<String,Integer> mStateMapResId;

        public StateLayoutBuild errorLayoutRes(int id)
        {
            this.mErrorLayoutResId = id;
            return this;
        }

        public StateLayoutBuild loadingLayoutRes(int id)
        {
            this.mLoadingLayoutResId = id;
            return this;
        }

        public StateLayoutBuild noNetWorkLayoutRes(int id)
        {
            this.mNoNetWorkLayoutResId = id;
            return this;
        }

        public StateLayoutBuild stateLayoutRes(Map<String,Integer> res)
        {
            this.mStateMapResId = res;
            return this;
        }

        public StateLayout build()
        {
            return new StateLayout(this);
        }

    }


}
