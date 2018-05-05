package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseActivityPresenter;

/**
 * Created by hxh on 2018/3/19.
 */

public class TestActivity extends BaseActivityPresenter<TestView> {
    @Override
    public Class<TestView> getV() {
        return TestView.class;
    }
}
