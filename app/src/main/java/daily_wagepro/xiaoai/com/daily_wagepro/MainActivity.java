package daily_wagepro.xiaoai.com.daily_wagepro;

import android.Manifest;
import android.os.Bundle;

import com.hxh.component.basicore.mvp.persenter.IPresenter;
import com.hxh.component.basicore.mvp.view.BaseActivity;
import com.hxh.component.basicore.util.aspj.annotation.PermissionCheck;

public class MainActivity extends BaseActivity {





    @PermissionCheck(permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION
    })
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle saveInstanceState) {
        loadRootFragment(R.id.frame_main,Framement.newInstance());
    }

    @Override
    public IPresenter newP() {
        return null;
    }


}
