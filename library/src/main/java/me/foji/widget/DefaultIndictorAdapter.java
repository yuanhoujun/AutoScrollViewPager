package me.foji.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author Scott Smith @Date 2016年08月16/8/6日 13:39
 */
public class DefaultIndictorAdapter extends PageControlBase.Adapter {
    private Context mContext;
    private GradientDrawable mSelectedDrawable;
    private GradientDrawable mUnSelectedDrawable;
    private Util mUtil;

    public DefaultIndictorAdapter(Context context) {
        mContext = context;
        mUtil = new Util(context);

        mSelectedDrawable = new GradientDrawable();
        mSelectedDrawable.setColor(Color.RED);
        mSelectedDrawable.setCornerRadius(mUtil.dp2px(30));

        mUnSelectedDrawable = new GradientDrawable();
        mUnSelectedDrawable.setColor(Color.YELLOW);
        mUnSelectedDrawable.setCornerRadius(mUtil.dp2px(30));
    }

    @Override
    public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        imageView.setClickable(true);
        imageView.setLayoutParams(new ViewGroup.LayoutParams((int)mUtil.dp2px(30), (int)mUtil.dp2px(30)));
        return new PageControlBase.ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(PageControlBase.ViewHolder holder, int position, int currentPosition) {
        if(position == currentPosition) {
            ((ImageView)holder.itemView).setImageDrawable(mSelectedDrawable);
        } else {
            ((ImageView)holder.itemView).setImageDrawable(mUnSelectedDrawable);
        }
    }
}
