package daily_wagepro.xiaoai.com.daily_wagepro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hxh.component.basicore.mvp.persenter.IPresenter;
import com.hxh.component.basicore.mvp.view.BaseActivity;
import com.hxh.component.basicore.util.aspj.annotation.CheckLogin;
import com.hxh.component.basicore.util.aspj.annotationenum.ShowType;


public class Main2Activity extends BaseActivity {

//    protected ActionBarConfig setActionBarConfig() {
//        return new ActionBarConfig.Builder()
//                .title("1123123")
//                .enableRightView("保存", Color.DKGRAY)
//                .backgroundColor(R.color.actionbar_onelevel_color)
//                .enableBackView(new ActionBarConfig.BackViewConfig(Color.RED, Utils.BitmapUtils.getBitmap(R.mipmap.icon_right_grav),"返回"))
//                .build();
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initData(Bundle saveInstanceState) {

       Button btn1 = (Button) findViewBy(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            @CheckLogin(type = ShowType.DIALOG_AND_NORMAL)
            public void onClick(View v) {
                toastShort("nihao a ");
            }
        });
    }




    @Override
    public IPresenter newP() {
        return null;
    }
}
