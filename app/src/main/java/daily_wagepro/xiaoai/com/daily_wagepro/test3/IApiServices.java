package daily_wagepro.xiaoai.com.daily_wagepro.test3;

import com.hxh.component.basicannotation.annotation.ApiServices;
import com.hxh.component.basicannotation.annotation.UseMRecycleView;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hxh on 2018/5/11.
 */
@ApiServices(value = "http://v.juhe.cn/")
public interface IApiServices {
    @GET("toutiao/index")
    @UseMRecycleView
    Observable<NewsDTO> getNews(@Query("type")String type,@Query("key")String key);

}
