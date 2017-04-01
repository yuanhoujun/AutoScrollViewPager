package me.foji.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 简化版的AutoScrollViewPager，轻松实现图片轮播
 *
 * @author Scott Smith 2017-04-01 23:16
 */
public class QuickAutoScrollViewPager extends AutoScrollViewPager {
    private float mIndictorRadius;
    private int mSelectedIndictorColor;
    private int mUnSelectedIndictorColor;
    private QuickIndictorAdapter mIndictorAdapter;

    public QuickAutoScrollViewPager(Context context) {
        this(context , null);
    }

    public QuickAutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context , attrs);
    }

    private void init(Context context , AttributeSet attrs) {
        if(null != attrs) {
            TypedArray arr = context.obtainStyledAttributes(
                    attrs , R.styleable.AutoScrollViewPager , 0 , 0);
            mIndictorRadius = arr.getDimension(
                    R.styleable.AutoScrollViewPager_asvp_indictor_radius , util.dp2px(5));
            mSelectedIndictorColor = arr.getColor(
                    R.styleable.AutoScrollViewPager_asvp_selected_indictor_color , Color.RED);
            mUnSelectedIndictorColor = arr.getColor(
                    R.styleable.AutoScrollViewPager_asvp_unselected_indictor_color , Color.YELLOW);
            arr.recycle();
        }
        replaceIndictorAdapter();
    }

    private void replaceIndictorAdapter() {
        mIndictorAdapter = new QuickIndictorAdapter(getContext());
        super.setIndictorAdapter(mIndictorAdapter);
    }

    @Override
    public void setIndictorAdapter(PageControlBase.Adapter indictorAdapter) {
    }

    public <T> void setQuickAdapter(QuickScrollPagerAdapter<T> adapter) {
        setAdapter(adapter);
    }

    private class QuickIndictorAdapter extends PageControlBase.Adapter {
        private Context mContext;
        private GradientDrawable mSelectedDrawable;
        private GradientDrawable mUnSelectedDrawable;

        public QuickIndictorAdapter(Context context) {
            mContext = context;

            mSelectedDrawable = new GradientDrawable();
            mSelectedDrawable.setColor(mSelectedIndictorColor);
            mSelectedDrawable.setCornerRadius(mIndictorRadius);

            mUnSelectedDrawable = new GradientDrawable();
            mUnSelectedDrawable.setColor(mUnSelectedIndictorColor);
            mUnSelectedDrawable.setCornerRadius(mIndictorRadius);
        }

        @Override
        public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView indictor = new TextView(mContext);
            indictor.setClickable(true);
            indictor.setLayoutParams(new ViewGroup.LayoutParams(
                    (int)mIndictorRadius , (int)mIndictorRadius));
            return new PageControlBase.ViewHolder(indictor);
        }

        @Override
        public void onBindViewHolder(
                PageControlBase.ViewHolder holder, int position, int currentPosition) {
            TextView indictor = (TextView) holder.itemView;
            if(position == currentPosition) {
                indictor.setBackgroundDrawable(mSelectedDrawable);
            } else {
                indictor.setBackgroundDrawable(mUnSelectedDrawable);
            }
        }
    }
}
