package tydic.cn.com.ListView;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2016/11/24.
 */

public class QQList extends ListView{
    private static final String TAG = "QQListView";
    //是否已打开滑动标志
    private boolean isSliding;
    private int minTouchSlip;
    private int xDowd,yDown;
    private int xMove,yMove;
    private LayoutInflater mInflater;
    private PopupWindow mPopupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;
    private Button mDeBtn;
    private DelButtonClickListener mListener;
    private View mCurrentView;
    private int mCurrentViewPos;



    public QQList(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        minTouchSlip = ViewConfiguration.get(context).getScaledTouchSlop();
        View view  = mInflater.inflate(R.layout.delete_btn,null);
        mDeBtn = (Button)view.findViewById(R.id.id_item_btn);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    interface DelButtonClickListener
    {
        public void clickHappend(int position);
    }
}
