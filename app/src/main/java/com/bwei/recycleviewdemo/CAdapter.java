package com.bwei.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by muhanxi on 17/5/14.
 */

public class CAdapter extends RecyclerView.Adapter<CAdapter.CViewHolder> {

    Context context;
    List<String> list ;

    public int header = 1;
    public int content = 0 ;
    public int footer = 2 ;

    public CAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }





    @Override
    public CViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(viewType == header){
//            View view =  LayoutInflater.from(context).inflate(R.layout.header,parent,false);
//            HViewHolder viewHolder = new HViewHolder(view);
//
//            return viewHolder;
//        } else  if(viewType == content){
//            View view =  LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//            CViewHolder viewHolder = new CViewHolder(view);
//
//            return viewHolder;
//        } else {
//            View view =  LayoutInflater.from(context).inflate(R.layout.footer,parent,false);
//            FViewHolder viewHolder = new FViewHolder(view);
//
//            return viewHolder;
//        }
            View view =  LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            CViewHolder viewHolder = new CViewHolder(view);

            return viewHolder;

    }



    @Override
    public void onBindViewHolder(CViewHolder holder, final int position) {

//        if(holder instanceof HViewHolder){
//            HViewHolder holder1 = (HViewHolder) holder ;
//            holder1.textView.setText("我是头");
//        }
//
//        if(holder instanceof CViewHolder){
//            CViewHolder holder1 = (CViewHolder) holder ;
//            holder1.textView.setText(list.get(position-1));
//        }
//
//        if(holder instanceof FViewHolder){
//
//            FViewHolder holder1 = (FViewHolder) holder ;
//            holder1.textView.setText("我是footer");
//
//        }

        if(holder instanceof CViewHolder){
            CViewHolder holder1 = (CViewHolder) holder ;
            holder1.textView.setText(list.get(position));
        }


    }


//    @Override
//    public int getItemViewType(int position) {
//        if(position == 0){
//            return header ;
//        } else if(position == list.size()+1){
//            return footer;
//        } else {
//            return  content;
//        }
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class CViewHolder extends RecyclerView.ViewHolder {

        TextView textView ;

        public CViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.tv);

        }

    }

    class HViewHolder extends RecyclerView.ViewHolder {

        TextView textView ;

        public HViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.header_tv);

        }

    }

    class FViewHolder extends RecyclerView.ViewHolder {

        TextView textView ;

        public FViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.footer_tv);

        }

    }


    interface OnItemClickListener {

        public void setOnItemClickListener(View view,int position);
        public void setOnItemLongClickListener(View view,int position);

    }
    OnItemClickListener onItemClickListener ;

    public void setOnItemListener(OnItemClickListener listener){
        this.onItemClickListener = listener ;
    }

}
