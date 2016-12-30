package tydic.cn.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yechenglong on 2016/12/30.
 */

public class DiyImageView extends ImageView{
    public DiyImageView(Context context) {
        this(context,null);
    }

    public DiyImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
