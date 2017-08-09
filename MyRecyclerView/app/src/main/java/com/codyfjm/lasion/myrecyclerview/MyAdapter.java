package com.codyfjm.lasion.myrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import java.util.List;

/**
 * 后续可以将MyAdapter<T>封装成接口
 * @param <T>
 */
public class MyAdapter<T> extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    public List<T> datas = null;

    public MyAdapter(List<T> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.content_name.setText(datas.get(position).toString());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);

        viewHolder.right_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(datas.get(position));
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        EasySwipeMenuLayout es_1;
        TextView right_del;
        TextView content_name;

        public ViewHolder(View view) {
            super(view);
            content_name = (TextView) view.findViewById(R.id.content_name);
            right_del = (TextView) view.findViewById(R.id.right_del);
            es_1 = (EasySwipeMenuLayout) view.findViewById(R.id.es_1);
        }
    }

    public void addItem(T content, int position) {
        datas.add(position, content);
        notifyItemInserted(position);
        notifyDataSetChanged();//数据更新
    }

    public void removeItem(T model) {
        int position = datas.indexOf(model);
        datas.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();//数据更新
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}