package tydic.cn.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tydic.cn.com.helloworld.R;

/**
 * Created by yechenglong on 2017/1/3.
 */

public class ListUpdateAdapter extends BaseAdapter{
    private List<String> datas;
    private Context context;
    public ListUpdateAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_update,null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_update);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        setData(holder,position);
        return convertView;
    }
    private void setData(ViewHolder holder,int position){
         holder.tv.setText(datas.get(position));
    }

    public void updateView(View view,int position,String str){
        if (datas == null||view==null) {
            return;
        }
        datas.set(position,str);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tv = (TextView)view.findViewById(R.id.tv_update);
        setData(holder,position);
    }
    private class ViewHolder{
        TextView tv;
    }
}
