package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import android.os.Bundle;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseFragmentPresenter;

/**
 * Created by hxh on 2018/4/9.
 */

public class Test1Fragme extends BaseFragmentPresenter<Test1FragmeView> {
    public static Test1Fragme newInstance() {

        Bundle args = new Bundle();

        Test1Fragme fragment = new Test1Fragme();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<Test1FragmeView> getV() {
        return Test1FragmeView.class;
    }


}
