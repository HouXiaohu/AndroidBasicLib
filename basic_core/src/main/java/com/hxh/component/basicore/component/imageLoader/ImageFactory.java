package com.hxh.component.basicore.component.imageLoader;

/**
 * Created by hxh on 2017/4/6.
 */
public class ImageFactory {
    public static final String GLIDE_LOADER = "glide";
    public static final String FERSCO = "fersco";
    public static final String PICASSO = "picasso";


    public static IImageLoader getLoader(String type)
    {
        switch (type)
        {
            case GLIDE_LOADER:

                return GliderLoader.getInstance();
            case FERSCO:

                break;
            case PICASSO:

                break;
        }


        return GliderLoader.getInstance();
    }


    public static IImageLoader getGlideLoader()
    {
        return GliderLoader.getInstance();
    }

}
