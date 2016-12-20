package tydic.cn.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/12/2.
 */

public class TestDiyView extends TextView{
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix matrix;
    int mViewWidth = 0;
    int mTranslate = 0;
    public TestDiyView(Context context) {
        this(context,null);
    }

    public TestDiyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestDiyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (matrix!=null){
            mTranslate += mViewWidth/5;
            if (mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            matrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, new int[]{
                        Color.BLUE, 0xffffffff, Color.BLUE
                }, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                matrix = new Matrix();
            }
        }
    }
}
