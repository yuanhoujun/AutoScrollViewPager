package me.foji.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by scott on 15/8/17.
 */
public class Util {
    private Context mContext;

    public Util(Context context) {
        mContext = context;
    }

    //Dp to Px
    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,mContext.getResources().getDisplayMetrics());
    }
}
