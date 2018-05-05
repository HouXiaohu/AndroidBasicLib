package daily_wagepro.xiaoai.com.daily_wagepro;

import android.app.Application;
import android.view.LayoutInflater;

import com.hxh.component.basicore.Base.app.AppComponent;
import com.hxh.component.basicore.Base.app.AppDelegate;
import com.hxh.component.basicore.Base.app.IApp;
import com.hxh.component.basicore.Base.app.UIProvider;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.component.net.NetProvider;
import com.hxh.component.basicore.ui.loading.CustomLoadingDialog;

/**
 * Created by hxh on 2018/1/22.
 */

public class App extends Application implements IApp {

    AppDelegate appDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        appDelegate = new AppDelegate();
        appDelegate.onCreate(this, new CoreLib
                .Builder()
                .setContext(this)
                .setNetProvider(new NetProvider.Builder()

                        .configHttpBaseUrl("http://v.juhe.cn/")
                        .build())
                .setEnableUMengStatistics(true)
                .setUIProvider(
                        new UIProvider.Builder()
                                .configLoadingDialogUI(new CustomLoadingDialog(LayoutInflater.from(this).inflate(R.layout.layout_loading, null)))

                                .build()


                )
                .build()
                .init());


        AppinitDelegate appinitDelegate = new AppinitDelegate(appDelegate, this);


    }

    @Override
    public AppComponent getAppComponent() {
        return appDelegate.getAppComponent();
    }

    @Override
    public Application getApplication() {
        return this;
    }
}
