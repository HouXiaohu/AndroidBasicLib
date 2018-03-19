package daily_wagepro.xiaoai.com.daily_wagepro;

import android.graphics.Color;
import android.os.Bundle;

import com.hxh.component.basicore.Base.TopBarBaseActivity;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.component.mvp.persenter.IPresenter;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.MRecycleView;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.annotation.Safe;
import com.hxh.component.basicore.util.aspj.util.AspjManager;


public class MainActivity extends TopBarBaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    private MRecycleView recyclerview_main;
    @Override
    public void initData(Bundle saveInstanceState) {
        AspjManager.CheckLoginManager.getInstance().registerLoginView(this);
        recyclerview_main = (MRecycleView) findViewBy(R.id.recyclerview_main);




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
                .listener(new ActionBarConfig.OnClickLinstener()
                {
                    @Override
                    @Safe
                    public void onRightClick() {
                        super.onRightClick();
//                        recyclerview_main
//                                .setAdapter(new NewsAdapter(MainActivity.this))
//                                .getDataRepositoryBuilder()
//                                .setNetRepository(new NetRepository() {
//                                    @Override
//                                    public Observable<AbsNetResultBean> getData(HashMap params) {
//                                        return ApiFactory.getNews(params);
//                                    }
//                                })
//                                .setParams("type","top")
//                                .setParams("key","762d899f185b02567bdf4ade1fbd0e33")
//                                .setResponseInterceptor(new MRecycleViewResponseInterceptor() {
//                                    @Override
//                                    public AbsNetResultBean setData(AbsNetResultBean datas) {
//                                        NewsDTO dto = (NewsDTO) datas.getSource();
//                                        return null;
//                                    }
//                                })
//                                .fetch();
                        throw new IllegalArgumentException("12312312");
//                        startActivity(new Intent(MainActivity.this,Main2Activity.class));

                    }
                })
                .build();
    }
}
