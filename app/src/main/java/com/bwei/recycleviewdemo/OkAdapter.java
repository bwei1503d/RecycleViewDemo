package com.bwei.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by muhanxi on 17/5/27.
 */

public class OkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public  int singpic = 0 ;
    public  int multipic =   1 ;

    private Context context;
    private List<Beans.ListBean> list ;

    public OkAdapter(Context context,List<Beans.ListBean> list){
        this.context = context;
        this.list = list ;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == singpic){

           View view =  View.inflate(context,R.layout.singleitem,null);
            return new FirstViewHolder(view);
        } else {
            View view =  View.inflate(context,R.layout.multiitem,null);
            return new SecondViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof FirstViewHolder){

            FirstViewHolder firstViewHolder = (FirstViewHolder) holder ;

            firstViewHolder.textView.setText(list.get(position).getTitle());


            Glide.with(context).load(list.get(position).getPic()).into(firstViewHolder.imageView);

        } else {
            SecondViewHolder secondViewHolder = (SecondViewHolder) holder ;
            secondViewHolder.textView.setText(list.get(position).getTitle());
            String [] str =  list.get(position).getPic().split("\\|") ;

            if(str.length > 0){
                Glide.with(context).load(str[0]).into(secondViewHolder.imageViewleft);
            }

            if(str.length >= 1){
                Glide.with(context).load(str[1]).into(secondViewHolder.imageViewright);
            }



        }



    }


    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getType() == 1){
            return singpic;
        } else {
            return multipic;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //一张图片
     class FirstViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView imageView;

         public FirstViewHolder(View itemView) {
             super(itemView);
             textView = (TextView) itemView.findViewById(R.id.single_tv);
             imageView = (ImageView) itemView.findViewById(R.id.single_imagevew);

         }
     }

    //二张图片
    class SecondViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageViewleft;
        ImageView imageViewright;

        public SecondViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.multi_tv);
            imageViewleft = (ImageView) itemView.findViewById(R.id.multi_imagevew_left);
            imageViewright = (ImageView) itemView.findViewById(R.id.multi_imagevew_right);
        }
    }

}
