package daily_wagepro.xiaoai.com.daily_wagepro;

import android.graphics.Color;
import android.os.Bundle;

import com.hxh.component.basicore.Base.TopBarBaseLazyFragment;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.mvp.persenter.IPresenter;
import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2018/1/23.
 */

public class Framement extends TopBarBaseLazyFragment{
    public static Framement newInstance() {
        
        Bundle args = new Bundle();
        
        Framement fragment = new Framement();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.layout_test;
    }

    @Override
    public void initData(Bundle saveInstanceState) {

    }

    @Override
    public IPresenter newP() {
        return null;
    }

    @Override
    protected ActionBarConfig setActionBarConfig() {
        return new ActionBarConfig.Builder()
                .title("1123123")
                .enableRightView("保存", Color.DKGRAY)
                .backgroundColor(R.color.actionbar_onelevel_color)
                .enableBackView(new ActionBarConfig.BackViewConfig(Color.RED, Utils.BitmapUtils.getBitmap(R.mipmap.icon_right_grav),"返回"))
                .build();
    }
}
