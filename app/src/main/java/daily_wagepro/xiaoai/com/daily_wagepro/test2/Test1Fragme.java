package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.BaseFragmentPresenter;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.util.AspjManager;
import com.hxh.component.ui.alertview.OnItemClickListener;

/**
 * Created by hxh on 2018/4/9.
 */

public class Test1Fragme extends BaseFragmentPresenter<Test1FragmeView> {
    public static Test1Fragme newInstance() {

        Bundle args = new Bundle();

        Test1Fragme fragment = new Test1Fragme();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<Test1FragmeView> getV() {
        return Test1FragmeView.class;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AspjManager.CheckLoginManager.getInstance().registerLoginViewTipDialog(Utils.DialogUtils.showDefaulStyleDialog("您尚未登录", new OnItemClickListener() {
            @Override
            public void onItemClick(String item, int position) {

            }
        }));
    }
}
