package me.foji.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 可以自定义滑动速度的Scroller
 *
 * @author Scott Smith 2017-03-21 11:03
 */
public class ViewPagerScroller extends Scroller {
    // 滑动速度因子
    private float mScrollFactor = 1;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    /**
     * 设置该值后，具体速度为正常速度的1/scrollFactor倍
     *
     * @param scrollFactor 滑动因子
     */
    public void setScrollDurationFactor(float scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
    }
}
