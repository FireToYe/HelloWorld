package tydic.cn.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yechenglong on 2016/12/15.
 */

public class CircleView extends View{
    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
        int height = getMeasuredHeight()-getPaddingTop()-getPaddingBottom();
        int radius = Math.min(width,height)/2;
        //canvas.drawCircle(width/2+getPaddingLeft(),height/2+getPaddingTop(),radius,mPaint);
        //canvas.drawArc();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode==MeasureSpec.AT_MOST){
            widthSize=200;
        }
        if (heightMode==MeasureSpec.AT_MOST){
            heightSize=200;
        }
        setMeasuredDimension(widthSize,heightSize);
    }
}
