package tydic.cn.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yechenglong on 2016/12/12.
 */

public class ByFiger extends View{
    int mLastX = 0;
    int mLastY = 0;
    public ByFiger(Context context) {
        this(context,null);
    }

    public ByFiger(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ByFiger(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-mLastX;
                int deltaY = y-mLastY;
                int transX;
        }
        return super.onTouchEvent(event);
    }
}
