package com.bwei.recycleviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends Activity implements RecycleViewAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    RecycleViewAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycleview);



        mDatas =  new ArrayList<String>();
        for(int i=0;i<1000;i++){
            mDatas.add(i+"");
        }

        adapter = new RecycleViewAdapter(this,mDatas) ;

        setLinearLayoutManagerVERTICAL_LIST();


        adapter.setOnItemClickListener(this);

        startActivity(new Intent(this, OkActivity.class));


    }



    private void setLinearLayoutManagerVERTICAL_LIST(){
        LinearLayoutManager linearLayoutManager =   new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));




        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }



    private void setLinearLayoutManagerHORIZONTAL_LIST(){
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext()) ;
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));


    }


    public void setGridLayotManager(){
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }


    public void setStaggeredGridLayoutManager(){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    private void add(int position){
        System.out.println("onItemClickListener view = "  +  " position  " + position + " -- " + mDatas.size());

        mDatas.add(position," " + position);

        adapter.notifyItemInserted(position);
//        adapter.notifyDataSetChanged();
        System.out.println("onItemClickListener view = "  +  " position 1 " + mDatas.size());

    }

    private void remove(int position){
        System.out.println("onItemClickListener view = "  +  " position  " + position + " -- " + mDatas.size());
        mDatas.remove(position);
        adapter.notifyItemRemoved(position);
        System.out.println("onItemClickListener view = "  +  " position 1 " + mDatas.size());

    }


    // click
    @Override
    public void onItemClickListener(View view, int position) {


        adapter.addData(position);

    }

    @Override
    public void onItemLongClickListener(View view, int position) {
        System.out.println("onItemLongClickListener view = " + view +  " position " + position);

    }
}
