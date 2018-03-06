package daily_wagepro.xiaoai.com.daily_wagepro;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hxh on 2018/1/26.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder()
                .removeHeader("pragma")
                .header("Cache-Control", "max-age=60")
                .build();
    }
}
