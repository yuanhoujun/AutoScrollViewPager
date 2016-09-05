package me.foji.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 *  圆形的TextView
 *  @author scott
 */
public class RoundedTextView extends TextView {
    // 填充颜色
    private int fillColor = 0;
    // 半径
    private float radius = 0;
    // 文字颜色
    private int textColor = 0;

    private Paint mPaint;

    public RoundedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);

        if(null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedTextView, 0, 0);
            fillColor = typedArray.getColor(R.styleable.RoundedTextView_fillColor, Color.parseColor("#ff000000"));
            radius = typedArray.getDimension(R.styleable.RoundedTextView_circleRadius,0);
            typedArray.recycle();
        }

        mPaint = getPaint();
        if(null != getTextColors()) {
            textColor = getTextColors().getDefaultColor();
        }
    }

    public void setFillColor(int color) {
        fillColor = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if(radius <= 0) {
            radius = width > height ? height / 2 : width / 2;
        }
        mPaint.setColor(fillColor);
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);

        mPaint.setColor(textColor);
        super.onDraw(canvas);
    }
}
