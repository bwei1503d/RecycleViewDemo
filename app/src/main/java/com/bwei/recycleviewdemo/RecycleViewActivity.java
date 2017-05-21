package com.bwei.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.RefreshHeaderLayout;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bwei.recycleviewdemo.recycleview.BatVsSupperHeaderView;
import com.bwei.recycleviewdemo.recycleview.LoadMoreFooterView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * RecyclerView 上下拉刷新
 * https://juejin.im/entry/570f716a1ea493006b5ecfbb
 * https://www.kancloud.cn/digest/fastdev4android/109670
 */
public class RecycleViewActivity extends Activity implements CAdapter.OnItemClickListener  {

    private IRecyclerView recyclerView;

    private List<String> list = new ArrayList<String>();
    private LinearLayoutManager linearLayoutManager;
    private CAdapter adapter;
    private SwipeToLoadLayout swipeToLoadLayout;


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1 :

                    for(int i=50;i<100;i++){
                        list.add(i+"  haha ");
                    }
                    adapter.notifyDataSetChanged();
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);


                    break;
                case 2:
                    for(int i=0;i<50;i++){
                        list.add(i+"  haha ");
                    }
                    adapter.notifyDataSetChanged();

                    recyclerView.setRefreshing(false);
                    break;
            }
        }
    } ;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreFooterView loadMoreFooterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);


        for(int i=0;i<50;i++){
            list.add(i+"  haha ");
        }

        adapter = new CAdapter(this,list);


        recyclerView = (IRecyclerView) findViewById(R.id.recycleview_id);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();


        recyclerView.setIAdapter(adapter);


        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        adapter.setOnItemListener(this);


        recyclerView.setOnRefreshListener(new com.aspsine.irecyclerview.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setRefreshing(true);

                handler.sendEmptyMessageDelayed(2,2000);

            }
        });


        recyclerView.setOnLoadMoreListener(new com.aspsine.irecyclerview.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);

                handler.sendEmptyMessageDelayed(1,2000);
            }
        });




        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                   int findFirstVisibleItemPosition =  linearLayoutManager.findFirstVisibleItemPosition() ;
                   int findFirstCompletelyVisibleItemPosition =  linearLayoutManager.findFirstCompletelyVisibleItemPosition() ;
//                    System.out.println("onScrollStateChanged =onScrolled findFirstVisibleItemPosition  " + findFirstVisibleItemPosition);
//                    System.out.println("onScrollStateChanged =onScrolled findFirstCompletelyVisibleItemPosition  " + findFirstCompletelyVisibleItemPosition);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition =  linearLayoutManager.findLastVisibleItemPosition();
                int lastCompletePosition =  linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                System.out.println("po =onScrolled findLastVisibleItemPosition  " + lastVisibleItemPosition);
            }
        });



    }




    @Override
    public void setOnItemClickListener(View view, int position) {

    }

    @Override
    public void setOnItemLongClickListener(View view, int position) {

    }

}
