package daily_wagepro.xiaoai.com.daily_wagepro.test3;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.hxh.component.basicore.component.mvp.newmvp.view.BaseView;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.MDataSource;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.MRecycleView;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetRepository;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetResultBean;

import java.util.HashMap;

import daily_wagepro.xiaoai.com.daily_wagepro.R;
import rx.Observable;

/**
 * Created by hxh on 2018/5/11.
 */

public class NewsView extends BaseView{
    private MRecycleView recycleView;
    @Override
    public void initView(Bundle saveInstanceState) {
        recycleView = findViewBy(R.id.recycleView);

    }

    public void initData(){
        recycleView
                .setAdapter(new NewsAdapter(getContext()))
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,false))
                .getDataRepositoryBuilder()
                .setNetRepository(new NetRepository() {
                    @Override
                    public Observable<NetResultBean> getData(HashMap params) {
                        return ApiFactory.getNews(params);
                    }
                })
                .setParams("type","top")
                .setParams("key","762d899f185b02567bdf4ade1fbd0e33")
                .setNoDataStateWhenRequest(MDataSource.EMPTYVIEWFORCED_WHENNODATA)
                .fetch();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activiity_news;
    }
}
