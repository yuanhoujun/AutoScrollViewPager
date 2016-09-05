package me.foji.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * 视图控制器基类
 * @author scott
 */
public abstract class PageControlBase<V extends View> implements OnChangeListener {
    // 当前页
    protected int mCurrPage;
    // 总页数
    protected int mTotalPage;

    //单页数据是否隐藏指示器
    protected boolean hideForSinglePage = true;

    // 指示器间隔
    protected float mIndictorSpace;

    // OnItemClickListener监听器
    protected OnItemClickListener onItemClickListener;

    // 设置当前页
    public abstract void setCurrPage(int currPage);
    // 设置总页数
    public void setTotalPage(int totalPage) {
        mTotalPage = totalPage;
    }

    public abstract void setHideForSinglePage(boolean hideForSinglePage);

    public abstract void setIndictorSpace(float indictorSpace);

    public abstract V containerView();

    public abstract void setAdapter(Adapter adapter);

    public abstract void notifyDatasetChanged();

    public boolean getHideForSinglePage() {
        return hideForSinglePage;
    }

    public abstract void setVisible(boolean visible);

    public abstract boolean isVisible();

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView,int position);
    }

    @Override
    public void onChange() {
        notifyDatasetChanged();
    }

    public static abstract class Adapter<VH extends ViewHolder> {
        private int mCurrPosition;
        private PageControlBase mPageControl;

        public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

        public abstract void onBindViewHolder(VH holder, int position,int currentPosition);

        public final int getItemCount() {
            return mPageControl.mTotalPage;
        }

        public void setCurrPosition(int currPosition) {
            mCurrPosition = currPosition > getItemCount() - 1 ? getItemCount() - 1 : currPosition;
            mCurrPosition = mCurrPosition < 0 ? 0 : mCurrPosition;
            if(null != mPageControl) {
                mPageControl.notifyDatasetChanged();
            }
        }

        public int getCurrPosition() {
            return mCurrPosition;
        }

        public void setPageControl(PageControlBase pageControl) {
            mPageControl = pageControl;
        }
    }

    public static class ViewHolder {
        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }
}
