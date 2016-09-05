package me.foji.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.foji.widget.AutoScrollBase;
import me.foji.widget.AutoScrollPagerAdapter;
import me.foji.widget.AutoScrollViewPager;

/**
 * @author Scott Smith  @Date 2016年09月16/9/5日 11:58
 */
public class FragmentAnySlider extends Fragment implements View.OnClickListener {
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
                TextView textView = (TextView) itemView.findViewById(R.id.description);
                textView.setText("当前页面>>>" + (position + 1) + "<<<");
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public int onLayoutId() {
                return R.layout.item_any_slider;
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
