package tydic.cn.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/12/22.
 */

public class SevenView extends View{
    private int first,secend,third,fourth,fifth,sixth,seventh;
    private Paint paint;
    private int[] colorInt = new int[]{R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5};
    private float[] floatsArr = new float[]{3,2.1f,2.9f,4.4f,1,2.8f,4.9f};
    private String[] strArr = new String[]{"能力1","能力1","能力1","能力1","能力1","能力1","能力1"};
    public SevenView(Context context) {
        this(context,null);
    }

    public SevenView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SevenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray a =context.getTheme().obtainStyledAttributes(attrs, R.styleable.seven_view,defStyleAttr,0);
//        first = a.getInt(R.styleable.seven_view_first,3);
//        secend = a.getInt(R.styleable.seven_view_secend,3);
//        third = a.getInt(R.styleable.seven_view_third,3);
//        fourth = a.getInt(R.styleable.seven_view_fourth,3);
//        fifth = a.getInt(R.styleable.seven_view_fifth,3);
//        sixth = a.getInt(R.styleable.seven_view_sixth,3);
//        seventh = a.getInt(R.styleable.seven_view_seventh,3);
//        a.recycle();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(false);
        int width = getWidth()/2;
        float radius =width;
        paint.setStrokeWidth(3);
        canvas.translate(radius,radius);
        canvas.rotate(-90);
        int count = 7;
        for (int j=0;j<5;j++) {
            int c = 5-j;
            float a = radius /5*c*0.8f;
            Path path = new Path();
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    path.moveTo(a * cos(360 / count * i), a * sin(360 / count * i));
                } else {
                    path.lineTo(a * cos(360 / count * i), a * sin(360 / count * i));
                }
            }
            path.close();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(colorInt[j]));
            canvas.drawPath(path, paint);
        }
        for (int i = 0;i<count;i++){
            paint.setColor(Color.WHITE);
            canvas.drawLine(0, 0, radius * cos(360 / count * i)*0.8f, radius * sin(360 / count * i)*0.8f, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
            canvas.drawText(strArr[i],radius * cos(360 / count * i)*0.8f, radius * sin(360 / count * i)*0.8f, paint);
        }
        Path pathLine = new Path();
        for (int i=0;i<count;i++){
                paint.setColor(Color.BLACK);
                float a = radius/5*floatsArr[i]*0.8f;
                if (i == 0) {
                    pathLine.moveTo(a * cos(360 / count * i), a * sin(360 / count * i));
                } else {
                    pathLine.lineTo(a * cos(360 / count * i), a * sin(360 / count * i));
                }
            }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        pathLine.close();
        canvas.drawPath(pathLine, paint);
        canvas.rotate(90);
        canvas.translate(-radius, -radius);
    }
    float sin(int num){
        return (float) Math.sin(num*Math.PI/180);
    }

    /**
     * 与sin同理
     */
    float cos(int num){
        return (float) Math.cos(num*Math.PI/180);
    }
}
