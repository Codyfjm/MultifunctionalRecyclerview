package com.codyfjm.lasion.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    MyAdapter mAdapter;
    List<String> myData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //设置默认的动画效果
//        mRecyclerView.setItemAnimator(null);

        for (int i = 0; i < 1000; i++) {//加载一百万数据
            String data = "###################data" + i;
            myData.add(data);
        }

        //创建并设置Adapter
        mAdapter = new MyAdapter(myData);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, String data) {
//                Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
//                mAdapter.removeItem(data);//点击删除
//                mAdapter.addItem(data+"++++",view.getId()+1);
//            }
//        });
    }
}
