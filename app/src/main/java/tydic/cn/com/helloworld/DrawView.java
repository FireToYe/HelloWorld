package tydic.cn.com.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/8/5.
 */
public class DrawView extends View{
    Paint p = new Paint();
    public float currentX = 40;
    public float currentY = 50;
    public DrawView(Context context){
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //设置画笔的颜色
        p.setColor(Color.RED);
        //设置画笔的形状
        canvas.drawCircle(currentX,currentY,15,p);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        currentX = event.getX();
        currentY = event.getY();
        //通知当前组件重新绘制自己
        invalidate();
        return true;
    }
}
