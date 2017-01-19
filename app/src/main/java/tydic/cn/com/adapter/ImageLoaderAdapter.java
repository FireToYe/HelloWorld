package tydic.cn.com.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import tydic.cn.com.helloworld.R;
import tydic.cn.com.imageUtil.ImageLoader;

/**
 * Created by yechenglong on 2017/1/16.
 */

public class ImageLoaderAdapter extends BaseAdapter{
    private List<String> mUrlList ;
    private Context mContext;
    private ImageLoader loader;
    public void setmUrlList(List<String> mUrlList) {
        this.mUrlList = mUrlList;
    }

    public ImageLoaderAdapter(Context mContext) {

        this.mContext = mContext;
        loader = ImageLoader.build(mContext);
    }

    @Override
    public int getCount() {
        return mUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUrlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler =null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_list_item,parent,false);
            hodler = new ViewHodler();
            hodler.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(hodler);
        }else{
            hodler = (ViewHodler) convertView.getTag();
        }
        ImageView imageView = hodler.imageView;
        final String tag = (String) imageView.getTag();
        final String uri  = (String) getItem(position);
        if (!uri.equals(tag)){
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image1));
        }
        imageView.setTag(uri);
        loader.bindBitMap(uri,imageView,40,40);
        return convertView;
    }
    private static class ViewHodler{
        ImageView imageView;
    }
}
