package daily_wagepro.xiaoai.com.daily_wagepro;

import android.app.Application;

import com.hxh.component.basicore.Base.app.AppInitDelegate;
import com.hxh.component.basicore.Base.app.BugManager;
import com.hxh.component.basicore.Base.app.provider.UIProvider;
import com.hxh.component.basicore.Base.topbar.ActionBarProvider;
import com.hxh.component.basicore.Config;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.component.net.NetProvider;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.util.AspjManager;
import com.hxh.component.basicore.util.statistics.IStatistics;
import com.hxh.component.basicore.util.statistics.UmengStatistics;

import java.util.HashMap;

/**
 * Created by hxh on 2018/1/22.
 */

public class AppinitDelegate extends AppInitDelegate {

    public AppinitDelegate(Application app) {
        super(app);
    }

    @Override
    public void init() {
        initActionBar();
        initBugManager();
        initCheckLoginAspj();
        CoreLib.getInstance().getAppComponent().globalAppStatistics().openStatistics(new HashMap<String, String>());

    }

    private void initBugManager() {
        BugManager bug = new BugManager() {
            @Override
            public void postBug(Exception e) {

            }

            @Override
            public void postBug(Throwable e) {

            }

            @Override
            public void init() {

            }
        };
        CoreLib.getInstance().getAppComponent().globalBugManager(bug);
    }


    @Override
    public void A_initCoreLib() {
        IStatistics view = new UmengStatistics();
        new CoreLib
                .Builder()
                .setContext(mApplication)
                .setNetProvider(new NetProvider.Builder()
                        .configHttpBaseUrl("http://v.juhe.cn/")
                        .build())
                .setUIProvider(
                        new UIProvider.Builder()
                                .configLoadingDialogUI(R.layout.layout_loading)

                                .build()
                )
                .setAppStatisticsProvier(view)
                .build();

    }


    /**
     * 初始化自动登录的织入点
     */
    private void initCheckLoginAspj() {
        AspjManager.CheckLoginManager.getInstance().registerLoginView(Config.LOGINVIEWPATH, AspjManager.CheckLoginManager.MODE_ACTIVTY
        );
    }

    public void initActionBar() {
        CoreLib.getInstance().getUIProvider().setActionBarProvider(
                new ActionBarProvider.Builder()
                        .titleColor(R.color.white)
                        .background(R.color.actionbar_onelevel_color)
                        .backText("返回")
                        .enableImmeriveMode()
                        .backImg(Utils.BitmapUtils.getBitmap(R.mipmap.icon_back))
                        .build());

    }
}
