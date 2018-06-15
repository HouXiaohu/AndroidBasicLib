package daily_wagepro.xiaoai.com.daily_wagepro.test3;

import android.os.Bundle;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseFragmentPresenter;

/**
 * Created by hxh on 2018/5/29.
 */

public class NewsFragmentPresenter extends BaseFragmentPresenter<NewsView>  {
    public static NewsFragmentPresenter newInstance() {
        
        Bundle args = new Bundle();
        
        NewsFragmentPresenter fragment = new NewsFragmentPresenter();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public Class<NewsView> getV() {
        return NewsView.class;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mView.initData();
    }
}
