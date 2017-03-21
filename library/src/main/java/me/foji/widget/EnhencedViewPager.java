package me.foji.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * 增强的ViewPager，增加更多控制
 *
 * @author Scott Smith 2017-03-21 10:58
 */
public class EnhencedViewPager extends ViewPager {
    private ViewPagerScroller mScroller;

    public EnhencedViewPager(Context context) {
        this(context , null);
    }

    public EnhencedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ViewPagerScroller(getContext() , (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    /**
     * 设置该值后，具体速度为正常速度的1/scrollFactor倍
     *
     * @param scrollFactor 滑动因子
     */
    public void setScrollDurationFactor(float scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }
}
