package tydic.cn.com.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/12/30.
 */

public class DiyImageView extends ImageView{
    Paint mpaint ,arcPaint;
    private float percent;
    public DiyImageView(Context context) {
        this(context,null);
    }

    public DiyImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mpaint = new Paint();
        arcPaint = new Paint();
//        new Thread(new Thread(){
//            @Override
//            public void run() {
//                while (percent<60){
//                    percent+=1;
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    postInvalidate();
//                }
//            }
//        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setAntiAlias(true);//去锯齿
        mpaint.setColor(getResources().getColor(R.color.white));
        mpaint.setStrokeWidth(10);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        int center = getWidth()/2;
        int radius =center-50;
        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);
        canvas.drawArc(rectF,-225,270,false,mpaint);
        canvas.drawCircle(center,center,radius/5,mpaint);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeWidth(20);
        //RectF rectc = new RectF(center-radius,center-radius,center+radius,center+radius);
        canvas.drawCircle(center,center,10,mpaint);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setAntiAlias(true);//去锯齿
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setColor(getResources().getColor(R.color.color6));
        arcPaint.setStrokeWidth(10);
        canvas.drawArc(rectF,-225,270*percent/100,false,arcPaint);
        mpaint.setStrokeWidth(10);
        float round = (float) (Math.PI*(-225+percent*270/100)/180);
        canvas.translate(center,center);
        //canvas.drawLine(0,0,(float) Math.sin(round)*radius,(float)Math.cos(round)*radius,mpaint);
        canvas.drawLine(0,0,(float)Math.cos(round)*(radius-20),(float) Math.sin(round)*(radius-20),mpaint);
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        postInvalidate();
        this.percent = percent;
    }
    public void setArcPaint(float percent){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(this,"percent",0,percent);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator( new DecelerateInterpolator());
        objectAnimator.start();
    }
}
