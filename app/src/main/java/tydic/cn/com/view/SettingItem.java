package tydic.cn.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/12/2.
 */

public class SettingItem extends LinearLayout{
    private ImageView ivRed,ivIcon;
    private TextView tvLabel;
    public SettingItem(Context context) {
        this(context, null);
    }

    public SettingItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SettingItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.setting_item,this);
        ivRed = (ImageView)view.findViewById(R.id.iv_red);
        ivIcon = (ImageView)view.findViewById(R.id.iv_icon);
        tvLabel = (TextView) view.findViewById(R.id.tv_label);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SettingAttr);
        CharSequence label = a.getText(R.styleable.SettingAttr_label);
        Drawable drawable = a.getDrawable(R.styleable.SettingAttr_drawIcon);//同上,这里的属性是:名字_属性名
        setLabel(label);
        setIcon(drawable);
        a.recycle();

    }

    public void setLabel(CharSequence label) {
        tvLabel.setText(label);
    }

    public void setIcon(Drawable icon) {
        ivIcon.setImageDrawable(icon);
    }

    public void setIcon(@DrawableRes int icon) {
        ivIcon.setImageResource(icon);
    }

    public void setIvRedShow(boolean isShow){
        if (isShow){
            ivRed.setVisibility(VISIBLE);
        }else {
            ivRed.setVisibility(GONE);
        }

    }
}
