package me.foji.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import me.foji.widget.AutoScrollBase;
import me.foji.widget.AutoScrollPagerAdapter;
import me.foji.widget.AutoScrollViewPager;

/**
 * 图片轮播
 *
 * @author Scott Smith  @Date 2016年09月16/9/5日 10:42
 */
public class FragmentImageSlider extends Fragment implements View.OnClickListener {
    private AutoScrollViewPager mViewPager;
    private Button start;
    private Button stop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slider,container,false);
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
        mViewPager.setOnItemClickListener(new AutoScrollBase.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view) {
                Toast.makeText(getContext(),"你点击了第" + (index + 1) + "张图片",Toast.LENGTH_SHORT).show();
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
