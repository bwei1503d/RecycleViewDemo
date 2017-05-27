package com.bwei.recycleviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by muhanxi on 17/5/9.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.IViewHolder> {

    private Context context ;
    private List<String> mDatas;
    private OnItemClickListener listener ;

    public RecycleViewAdapter(Context context,List<String> mDatas){
        this.context = context ;
        this.mDatas = mDatas;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IViewHolder iViewHolder = new IViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));

        return iViewHolder;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder,final  int position) {


//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.textView.getLayoutParams() ;

//        params.width = 100 ;
//        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        holder.textView.setLayoutParams(params);
//
//        holder.textView.setBackgroundColor(Color.BLUE);

        if(position %2 ==0){
            holder.textView.setBackgroundColor(Color.GRAY);
        } else {
            holder.textView.setBackgroundColor(Color.BLUE);
        }
        holder.textView.setText(mDatas.get(position) + " -- " + position);


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(v,position);
            }
        });

        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener(v,position);
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class  IViewHolder extends RecyclerView.ViewHolder {

         TextView textView ;

         public IViewHolder(View view){
             super(view);
             textView = (TextView) view.findViewById(R.id.tv);
         }

     }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mDatas.size());

    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDatas.size());
    }

     public interface OnItemClickListener{
         void onItemClickListener(View view,int position);
         void onItemLongClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener ;
    }

}
