package com.hxh.component.basicore.imageLoader;

/**
 * Created by hxh on 2017/4/6.
 */
public class ImageFactory {

    private static IImageLoader loader;

    public static IImageLoader getLoader()
    {
        if(null == loader)
        {
            synchronized (ImageFactory.class)
            {
                if(loader == null)
                {
                    loader = new GliderLoader();
                }
            }
        }
        return loader;
    }

}
