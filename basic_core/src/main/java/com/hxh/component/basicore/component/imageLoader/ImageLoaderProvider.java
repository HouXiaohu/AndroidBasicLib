package com.hxh.component.basicore.component.imageLoader;

import android.graphics.drawable.Drawable;

/**
 * Created by hxh on 2017/12/29.
 */

public class ImageLoaderProvider {
    private int errorImgResId;
    private int loadingImgResId;
    private Drawable errorImgDrawable;
    private Drawable loadingImgDrawable;

    public ImageLoaderProvider(int errorImgResId, int loadingImgResId) {
        this.errorImgResId = errorImgResId;
        this.loadingImgResId = loadingImgResId;
    }

    public ImageLoaderProvider(Drawable errorImgDrawable, Drawable loadingImgDrawable) {
        this.errorImgDrawable = errorImgDrawable;
        this.loadingImgDrawable = loadingImgDrawable;
    }

    public ImageLoaderProvider(Builder build)
    {
        this.errorImgDrawable = build.errorImgDrawable;
        this.errorImgResId = build. errorImgResId;
        this.loadingImgDrawable = build. loadingImgDrawable;
        this.loadingImgResId = build.loadingImgResId ;
    }

    public static class Builder
    {
        private int errorImgResId;
        private int loadingImgResId;
        private Drawable errorImgDrawable;
        private Drawable loadingImgDrawable;

        public Builder errorImg(int resId)
        {
            this.errorImgResId = resId;
            return this;
        }

        public Builder loadingImg(int resid)
        {
            this.loadingImgResId = resid;
            return this;
        }

        public Builder errorImg(Drawable imgDrawable)
        {
            this.errorImgDrawable = imgDrawable;
            return this;
        }

        public Builder loadingImg(Drawable imgDrawable)
        {
            this.loadingImgDrawable = imgDrawable;
            return this;
        }

        public ImageLoaderProvider build()
        {
            return new ImageLoaderProvider(this);
        }
    }
}
