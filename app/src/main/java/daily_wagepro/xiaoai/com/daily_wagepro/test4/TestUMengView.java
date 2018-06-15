package daily_wagepro.xiaoai.com.daily_wagepro.test4;

import android.os.Bundle;
import android.widget.EditText;

import com.hxh.component.basicore.component.mvp.newmvp.view.BaseView;

import daily_wagepro.xiaoai.com.daily_wagepro.R;

/**
 * Created by hxh on 2018/6/14.
 */

public class TestUMengView extends BaseView implements TestUMengContract.V{
    public EditText et_acount,et_pwd;


    @Override
    public void initView(Bundle saveInstanceState) {
        et_acount = findViewBy(R.id.et_acount);
        et_pwd = findViewBy(R.id.et_pwd);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activitiy_testumeng;
    }

}
