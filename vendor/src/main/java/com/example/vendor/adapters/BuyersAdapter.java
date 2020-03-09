package com.example.vendor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;

import java.util.List;

public class BuyersAdapter  extends RecyclerView.Adapter<BuyersAdapter.MyViewHolder>{

    Context mContext;
    List<BuyersTestClass> mData;

    public BuyersAdapter(Context mContext, List<BuyersTestClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public BuyersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.buyer_detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyersAdapter.MyViewHolder holder, int position) {

        holder.buyer_name.setText(mData.get(position).getName());
        holder.buyer_logo.setImageResource(mData.get(position).getOrderId() );

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(mContext, BuyerDetail.class);
//                intent.putExtra("name",mData.get(position).getName());
//                intent.putExtra("branch",mData.get(position).getBranch());
//                intent.putExtra("id",mData.get(position).getId());
//                intent.putExtra("logo",mData.get(position).getOrderId());
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView buyer_name;
        ImageView buyer_logo;
        public MyViewHolder(View itemView){
            super(itemView);
            buyer_name=(TextView) itemView.findViewById(R.id.buyerName);
            buyer_logo=(ImageView) itemView.findViewById(R.id.buyerLogo);
            cardView = (CardView) itemView.findViewById(R.id.cardViewId);
        }

    }
}