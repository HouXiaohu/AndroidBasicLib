package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.component.mvp.newmvp.view.BaseView;

import daily_wagepro.xiaoai.com.daily_wagepro.R;

/**
 * Created by hxh on 2018/3/19.
 */

public class TestView extends BaseView{
    ViewPager viewpager1;
    @Override
    public void initView(Bundle saveInstanceState) {

        viewpager1 = findViewBy(R.id.viewpager1);
        viewpager1.setAdapter(new HomePagerAdapter(AppManager.getCurrentCompatActivity().getSupportFragmentManager()));
    }

    @Override
    public void release() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }
}
