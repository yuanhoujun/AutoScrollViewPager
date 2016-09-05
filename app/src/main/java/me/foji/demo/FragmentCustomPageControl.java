package me.foji.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import me.foji.widget.AutoScrollBase;
import me.foji.widget.AutoScrollPagerAdapter;
import me.foji.widget.AutoScrollViewPager;

/**
 * @author Scott Smith  @Date 2016年09月16/9/5日 13:07
 */
public class FragmentCustomPageControl extends Fragment implements View.OnClickListener {
    private AutoScrollViewPager mViewPager;
    private Button start;
    private Button stop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_pagecontrol,container,false);
        mViewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager);
        start = (Button) view.findViewById(R.id.start);
        stop = (Button) view.findViewById(R.id.stop);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        mViewPager.setPageControl(new CustomPageControl(getContext()));

        final int[] images = {R.drawable.cat1,R.drawable.cat2};
        mViewPager.setAdapter(new AutoScrollPagerAdapter() {
            @Override
            public void onBindView(View itemView, int position) {
                ((ImageView)itemView).setImageResource(images[position]);
            }

            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public int onLayoutId() {
                return R.layout.image_view;
            }
        });
        ((CustomPageControl)mViewPager.pageControl()).setDescription("当前页面为第1页");
        mViewPager.setOnPageChangeListener(new AutoScrollBase.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((CustomPageControl)mViewPager.pageControl()).setDescription("当前页面为第" + (position + 1) + "页");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start: {
                mViewPager.autoScroll();
                break;
            }
            case R.id.stop: {
                mViewPager.stopAutoScroll();
                break;
            }
        }
    }
}
