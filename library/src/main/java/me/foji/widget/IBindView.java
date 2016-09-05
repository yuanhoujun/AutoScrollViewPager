package me.foji.widget;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * @author Scott Smith @Date 2016年09月16/9/3日 23:07
 */
public abstract class IBindView {
    private int count;

    public abstract void onBindView(View itemView, int position);
    public abstract int getCount();
    public abstract @LayoutRes int onLayoutId();

    public void setCount(int count) {
        this.count = count;
    }

    public int count() {
        return count;
    }
}
