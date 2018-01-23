package daily_wagepro.xiaoai.com.daily_wagepro;

import android.app.Application;

import com.hxh.component.basicore.Base.app.AppDelegate;
import com.hxh.component.basicore.Base.app.AppInitDelegate;
import com.hxh.component.basicore.Base.topbar.ActionBarProvider;
import com.hxh.component.basicore.util.BugManager;
import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2018/1/22.
 */

public class AppinitDelegate extends AppInitDelegate {

    public AppinitDelegate(AppDelegate appDelegate, Application app) {
        super(appDelegate, app);


        ActionBarProvider actionBarProvider = new ActionBarProvider.Builder()
                .titleColor(R.color.white)
                .background(R.color.actionbar_onelevel_color)
                .backText("返回")
                .enableImmeriveMode()
                .backImg(Utils.BitmapUtils.getBitmap(R.mipmap.icon_back))
                .build();
        appDelegate
                .registerActionBarProvider(actionBarProvider);
    }

    @Override
    public BugManager A_initBugManager() {
        return null;
    }
}
