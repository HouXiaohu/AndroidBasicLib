package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hxh.component.basicore.Base.app.UIProvider;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.component.mvp.newmvp.view.BaseView;
import com.hxh.component.basicore.util.ToastUIType;

import daily_wagepro.xiaoai.com.daily_wagepro.R;

/**
 * Created by hxh on 2018/4/9.
 */

public class Test1FragmeView extends BaseView {

    private TextView tv_next,tv_next1;
    @Override
    public void initView(Bundle saveInstanceState) {
        tv_next = findViewBy(R.id.tv_next);
        tv_next1= findViewBy(R.id.tv_next1);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
            }
        });
        tv_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIProvider ui = CoreLib.getInstance().getUIProvider();
                ui.setToastUIType(ToastUIType.INSIDE);
                CoreLib.getInstance().setUIProvider(
                        ui
                );
                showLoading1();
            }
        });

    }

    private void showLoading() {
        toastShort("你好啊");
    }

    private void showLoading1() {
        toastShort("你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊", Gravity.CENTER);
    }

    @Override
    public void release() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_test;
    }
}
