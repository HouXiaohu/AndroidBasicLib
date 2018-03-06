package daily_wagepro.xiaoai.com.daily_wagepro;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by hxh on 2018/2/1.
 */

public class ScrollZoomListView extends ListView {
    public ScrollZoomListView(Context context) {
        super(context);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        /**
         * deltaY 是增量
         * - 是下拉
         * + 是上拉
         */
        if(deltaY<0)
        {
            //改变实际高度
            mImageView.getLayoutParams().height = mImageView.getHeight()-deltaY;
            //重新摆放位置
            //为什么不用invalidate()  而这个方法只会调用onDraw()，也就是只绘制自己，但我们是要重新布局
            mImageView.requestLayout();
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
    private ImageView mImageView;
    public void setZoomImageView(ImageView iv)
    {
        this.mImageView = iv;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        View headView = (View) mImageView.getParent();
        int deltay = headView.getTop();
        if(deltay<0)
        {
            mImageView.getLayoutParams().height = mImageView.getHeight()+deltay;
            headView.layout(headView.getLeft(),0,headView.getRight(),headView.getHeight());
            mImageView.requestLayout();
        }
    }
}
