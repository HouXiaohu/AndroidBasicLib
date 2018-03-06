package daily_wagepro.xiaoai.com.daily_wagepro.test1;

import com.hxh.component.basicannotation.annotation.ApiServices;
import com.hxh.component.basicannotation.annotation.UseMRecycleView;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hxh on 2018/2/27.
 */

@ApiServices
public interface INewsServices {


    @UseMRecycleView
    @GET("toutiao/index")
    Observable<NewsDTO> getNews(@Query("type")String type,
                                                                      @Query("key")String key
                                );
}
