package com.hxh.component.basicore.component.imageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.hxh.component.basicore.Config;

import java.io.File;

/**
 * Created by hxh on 2017/4/6.
 * 此类为图片加载的基类，不管使用任何框架，都要建立那个框架的工具类并实现此接口，以此来实现动态替换框架
 */
public interface IImageLoader {
    void init(Context context);
    void loadFormNet(ImageView iv,String url,Options options);
    void loadFormNet(ImageView iv,String url,Options options,CallBack callback);
    void loadFormNet(ImageView iv,String url,Options options,CallBack callback,boolean enableCache);
    void loadFormNet(ImageView iv,String url,Options options,CallBack callback,String savePath,boolean enableCache);
    void loadFormNet(ImageView iv,String url,Options options,CallBack callback,String savePath);
    void loadFormNet(ImageView iv,String url,Options options,String savePath);
    void loadFormNet(ImageView iv,String url,Options options,String savePath,boolean enableCache);
    void loadFormNet(ImageView iv,String url,Options options,boolean enableCache);
    void loadFormNet(ImageView iv,String url,boolean asgif);



    void loadResource(ImageView iv,int resId,boolean asgif);

    void loadResource(ImageView iv,int resId,Options options);
    void loadAssest(ImageView iv,String assestName,Options options);
    void loadFile(ImageView iv, File file,Options options);
    void clearMemoryCache(Context context);
    void clearDiskCache(Context context);

    //绑定生命周期
    void resume(Context context);
    void pause(Context context);

    public interface CallBack
    {
        void onSuccess(Object obj);
        void onError(String message);
    }

    public class Options
    {
        public int loadingResId = -1;
        public int loadErrorResId = -1;
        public ImageView.ScaleType scaleType = null;
        public boolean isEnableAnimate = false;
        public static Options defaultOptions()
        {
            return new Options(Config.LOADING_RESID,Config.LOADING_FAILD,false);
        }

        public static Options defaultHeadimgOptions()
        {
            return new Options(Config.DEFAULT_HEADIMG,Config.DEFAULT_HEADIMG,false);
        }

        public Options(int loadingResId,int loadErrorResId,boolean isEnableAnimate)
        {
            this.loadErrorResId = loadErrorResId;
            this.loadingResId = loadingResId;
            this.isEnableAnimate = isEnableAnimate;
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

        public Options(int loadErrorResId, boolean isEnableAnimate) {
            this.loadErrorResId = loadErrorResId;
            this.isEnableAnimate = isEnableAnimate;
        }

        public Options(int loadingResId, int loadErrorResId, ImageView.ScaleType scaleType, boolean isEnableAnimate) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
            this.scaleType = scaleType;
            this.isEnableAnimate = isEnableAnimate;
        }

        public Options(int loadingResId, int loadErrorResId, ImageView.ScaleType scaleType) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
            this.scaleType = scaleType;
        }

        public Options(int loadErrorResId) {
            this.loadErrorResId = loadErrorResId;
        }

        public Options setScaleType(ImageView.ScaleType type)
        {
            scaleType = type;
            return this;
        }


    }
}
