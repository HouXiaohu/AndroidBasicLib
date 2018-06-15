package daily_wagepro.xiaoai.com.daily_wagepro;

import android.app.Application;

import com.hxh.component.basicore.Base.app.provider.AppProvider;
import com.hxh.component.basicore.Base.app.provider.AppComponentCallbacks;
import com.hxh.component.basicore.Base.app.AppDelegate;
import com.hxh.component.basicore.Base.app.IApp;

/**
 * Created by hxh on 2018/1/22.
 */

public class App extends Application implements IApp {

    AppDelegate appDelegate;

    @Override
    public void onCreate() {
        super.onCreate();

        AppinitDelegate appinitDelegate = new AppinitDelegate(this);
        appDelegate = new AppDelegate();
        appDelegate.onCreate(appinitDelegate);
        appDelegate.getAppComponent().globalAppListener(new Call());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        appDelegate.onStop();
    }

    @Override
    public AppProvider getAppComponent() {
        return appDelegate.getAppComponent();
    }


    private class Call extends AppComponentCallbacks
    {

        @Override
        public void onLowMemory() {
            super.onLowMemory();
        }
    }

}
