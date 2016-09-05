package me.foji.widget;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott Smith @Date 2016年08月16/8/5日 20:23
 */
public class ActualPagerAdapter extends PagerAdapter implements OnChangeListener {
    private AutoScrollViewPager mViewPager;
    private List<View> mAssistViews;
    private List<View> mViews;
    private IBindView mBindView;

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public void setBindView(IBindView bindView) {
        mBindView = bindView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        prepare(container);

        final View itemView;
        if(null != mAssistViews) {
            itemView = mAssistViews.get(position % mAssistViews.size());
        } else {
            itemView = mViews.get(position % mViews.size());
        }

        if(null != itemView.getParent()) {
            container.removeView(itemView);
        }

        mBindView.onBindView(itemView,position % mViews.size());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != mViewPager.onItemClickListener) {
                    mViewPager.onItemClickListener.onItemClick(position % mViews.size(),itemView);
                }
            }
        });
        container.addView(itemView);
        return itemView;
    }

    private void prepare(ViewGroup container) {
        int count = mBindView.getCount();
        if(null == mViews) {
            mViews = new ArrayList<>();
            for(int i = 0;i < count;i ++) {
                mViews.add(LayoutInflater.from(container.getContext()).inflate(mBindView.onLayoutId(),container,false));
            }
        }
        if(count == 2 && null == mAssistViews) {
            mAssistViews = new ArrayList<>(mViews);
            mAssistViews.add(LayoutInflater.from(container.getContext()).inflate(mBindView.onLayoutId(),container,false));
        }
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mBindView.getCount() > 1 ? Integer.MAX_VALUE : mBindView.getCount();
    }

    public void setViewPager(AutoScrollViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public void notifyDataSetChanged() {
        mViews = null;
        super.notifyDataSetChanged();
        if(null != mViewPager.mPageControl) {
            mViewPager.mPageControl.setTotalPage(mBindView.getCount());
        }
        mViewPager.updateIndictorStatus();
    }
}
