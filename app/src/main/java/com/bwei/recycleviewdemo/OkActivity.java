package com.bwei.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.bwei.recycleviewdemo.recycleview.LoadMoreFooterView;
import com.google.gson.Gson;

import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.xutils.x.getDb;

public class OkActivity extends Activity {

    private IRecyclerView iRecyclerView;

    private List<Beans.ListBean> list = new ArrayList<Beans.ListBean>();

    int page = 1 ;

    OkAdapter adapter ;
    private LinearLayoutManager linearLayoutManager;
    private LoadMoreFooterView footerView;

    IApplication application ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);

        application = (IApplication) getApplication() ;
        iRecyclerView = (IRecyclerView) findViewById(R.id.recycleview_idok);
        footerView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        iRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new OkAdapter(this,list);

        iRecyclerView.setIAdapter(adapter);


        try {
           List<Beans.ListBean>  temp = x.getDb(application.dbDao()).findAll(Beans.ListBean.class);
            list.addAll(temp);
            System.out.println("temp = " + temp.size());
            adapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }

        getData(page);


        iRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1 ;
                getData(page);


            }
        });

        iRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {


                footerView.setStatus(LoadMoreFooterView.Status.LOADING);
                page = 1 + page ;
                getData(page);
            }
        });


    }






    public void getData(final int page){


        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url("http://qhb.2dyt.com/Bwei/news?postkey=1503d&page=1&type=1").build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                       String result =   response.body().string();
                        final Beans beans = new Gson().fromJson(result,Beans.class);


                        getDb(application.dbDao()).save(beans.getList());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(page == 1){
                                    // 第一页
                                    list.clear();
                                    list.addAll(beans.getList());
                                    adapter.notifyDataSetChanged();
                                    iRecyclerView.setRefreshing(false);
                                } else {
                                    list.addAll(beans.getList());
                                    adapter.notifyDataSetChanged();
                                    footerView.setStatus(LoadMoreFooterView.Status.GONE);


                                }
                            }
                        });


                        System.out.println("result = " + result);
                    }
                });

            }
        }).start();



    }


}
