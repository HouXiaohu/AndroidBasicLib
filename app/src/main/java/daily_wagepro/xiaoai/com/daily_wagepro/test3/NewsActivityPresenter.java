package daily_wagepro.xiaoai.com.daily_wagepro.test3;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseActivityPresenter;
import com.hxh.component.basicore.ui.loading.CirCleLoadingDialog;

/**
 * Created by hxh on 2018/5/11.
 */

public class NewsActivityPresenter extends BaseActivityPresenter<NewsView2>{
    @Override
    public Class<NewsView2> getV() {
        return NewsView2.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CirCleLoadingDialog loadingDialog = new CirCleLoadingDialog(this,"123");
        loadingDialog.show();
        //loadRootFragment(R.id.frame_main,NewsFragmentPresenter.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
