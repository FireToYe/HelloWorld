package tydic.cn.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/12/19.
 */

public class ArcTestView extends View{
    private int firstColor;
    private int secendColor;

    private int speed;

    private int arcWidth;
    private int textSize;
    private boolean flag=false;
    private int mProgress=-40;
    private Paint mPaint;
    public ArcTestView(Context context) {
        this(context,null);
    }

    public ArcTestView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.arc_test_view,defStyleAttr,0);
        firstColor =a.getColor(R.styleable.arc_test_view_firstColor, Color.RED);
        secendColor = a.getColor(R.styleable.arc_test_view_secendColor,Color.GRAY);
        speed = a.getInt(R.styleable.arc_test_view_speed,20);
        arcWidth = (int)a.getDimension(R.styleable.arc_test_view_radius,20);
        textSize = (int)a.getDimension(R.styleable.arc_test_view_mTextSize,14);
        a.recycle();
        mPaint = new Paint();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    mProgress+=40;
                    if (mProgress==400){
                        mProgress=0;
                        if (flag){
                            flag=false;
                        }else{
                            flag=true;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth()/2;
        int radius =center-arcWidth/2;
        //mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(arcWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(secendColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);
        if(flag){
            mPaint.setColor(secendColor);
            for (int i=0;i<9;i++){
                canvas.drawArc(rectF,i*40,2,false,mPaint);
            }
            //canvas.drawText();
           for(int i=0;i<mProgress/40;i++){
               mPaint.setColor(firstColor);
               canvas.drawArc(rectF,40*i,2,false,mPaint);
           }
        }else{
            mPaint.setColor(firstColor);
            for (int i=0;i<9;i++){
                canvas.drawArc(rectF,i*40,2,false,mPaint);
            }
            //canvas.drawText();
            for(int i=0;i<mProgress/40;i++){
                mPaint.setColor(secendColor);
                canvas.drawArc(rectF,40*i,2,false,mPaint);
            }
        }
        mPaint.setStrokeWidth(0);
        mPaint.setColor(secendColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int)(((float)mProgress / (float)360) * 100);
        float textWidth = mPaint.measureText(percent + "%");
        canvas.drawText(percent + "%", center - textWidth / 2, center + textSize/2, mPaint);
    }
}
