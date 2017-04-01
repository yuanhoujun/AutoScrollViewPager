package me.foji.widget;

import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Quick auto scroll pager adpater
 *
 * @author Scott Smith 2017-04-01 23:28
 */
public abstract class QuickScrollPagerAdapter<T> extends AutoScrollPagerAdapter {
    private List<T> datas;

    public QuickScrollPagerAdapter(List<T> datas) {
        this.datas = datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas) {
        if(null == this.datas || this.datas.isEmpty()) return;

        if(null != datas && !datas.isEmpty()) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if(null == this.datas || this.datas.isEmpty()) return;

        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindView(View itemView, int position) {
        ImageView imageView = (ImageView) itemView;
        convert(imageView , position , datas.get(position));
    }

    @Override
    public int getCount() {
        return null == datas || datas.isEmpty() ? 0 : datas.size();
    }

    @Override
    public int onLayoutId() {
        return R.layout.image_view;
    }

    public abstract void convert(ImageView imageView , int position , T data);
}
