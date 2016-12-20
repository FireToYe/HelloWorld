package tydic.cn.com.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class ImageAdapter extends PagerAdapter {
    private List list;
    public ImageAdapter(List list){
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size()*1000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(position<0){
            position = position+list.size();
        }
        View view = (View)list.get(position%4);
        ViewParent vp = view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView((View)list.get(position%4));
        return list.get(position%4);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)list.get(position%4));
    }
}
