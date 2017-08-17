package com.example.administrator.view_shichatexiao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 张祺钒
 * on2017/8/16.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] str;

    public MyAdapter(Context context, String[] str) {
        this.context = context;
        this.str = str;
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item,null);
            holder.textView=view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(str[i]);
        return view;
    }
    static  class ViewHolder{
        TextView textView;
    }
}
