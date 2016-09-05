package me.foji.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.foji.widget.PageControlBase;
import me.foji.widget.Util;

/**
 * @author Scott Smith @Date 2016年09月16/9/4日 15:29
 */
public class CustomPageControl extends PageControlBase<RelativeLayout> {
    private RecyclerView mListView;
    private TextView mTextView;
    private RelativeLayout mContainerView;
    private Context mContext;
    private Util util;
    private Adapter mAdapter;

    public CustomPageControl(Context context) {
        mContext = context;
        util = new Util(mContext);
    }

    @Override
    public void setCurrPage(int currPage) {
        if(null != mAdapter) mAdapter.setCurrPosition(currPage);
    }

    @Override
    public void setHideForSinglePage(boolean hideForSinglePage) {
        this.hideForSinglePage = hideForSinglePage;
    }

    @Override
    public void setIndictorSpace(float indictorSpace) {
        mIndictorSpace = indictorSpace;
        mListView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set((int) (mIndictorSpace / 2), 0, (int) (mIndictorSpace / 2), 0);
            }
        });
    }

    @Override
    public RelativeLayout containerView() {
        if(null == mContainerView) {
            mContainerView = new RelativeLayout(mContext);
            mContainerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mContainerView.setPadding(0,(int) util.dp2px(5f),0,(int) util.dp2px(5f));
            mContainerView.setBackgroundColor(Color.parseColor("#66000000"));

            mTextView = new TextView(mContext);
            mTextView.setTextSize(14f);
            mTextView.setTextColor(Color.WHITE);
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp1.addRule(RelativeLayout.CENTER_VERTICAL);
            lp1.leftMargin = (int) util.dp2px(10f);
            mTextView.setLayoutParams(lp1);
            mContainerView.addView(mTextView);

            mListView = new RecyclerView(mContext);
            mListView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lp2.addRule(RelativeLayout.CENTER_VERTICAL);
            lp2.rightMargin = (int) util.dp2px(10f);
            mListView.setLayoutParams(lp2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
            mListView.setLayoutManager(linearLayoutManager);
            mContainerView.addView(mListView);
        }
        return mContainerView;
    }

    @Override
    public void setAdapter(final Adapter adapter) {
        if(null != adapter) {
            mAdapter = adapter;
            mAdapter.setPageControl(this);
            RecyclerView.Adapter rAdapter = new RecyclerView.Adapter() {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    ViewHolder viewHolder = adapter.onCreateViewHolder(parent,viewType);
                    return new RecyclerView.ViewHolder(viewHolder.itemView) {
                        @Override
                        public String toString() {
                            return super.toString();
                        }
                    };
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(null != onItemClickListener) {
                                onItemClickListener.onItemClick(view,position);
                            }
                        }
                    });
                    adapter.onBindViewHolder(new ViewHolder(holder.itemView),position,adapter.getCurrPosition());
                }

                @Override
                public int getItemCount() {
                    return adapter.getItemCount();
                }
            };
            mListView.setAdapter(rAdapter);
        }
    }

    @Override
    public void notifyDatasetChanged() {
        mListView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setVisible(boolean visible) {
        containerView().setVisibility(visible? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isVisible() {
        return containerView().getVisibility() == View.VISIBLE;
    }

    public void setDescription(CharSequence text) {
        mTextView.setText(text);
    }
}
