package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hxh.component.basicore.Config;
import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseActivityPresenter;

/**
 * Created by hxh on 2018/3/19.
 */
@Route(path = Config.LOGINVIEWPATH)
public class TestActivity extends BaseActivityPresenter<TestView> {
    @Override
    public Class<TestView> getV() {
        return TestView.class;
    }
}
