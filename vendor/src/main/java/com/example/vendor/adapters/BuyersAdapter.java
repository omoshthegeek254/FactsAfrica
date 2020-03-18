package com.example.vendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.models.User;

import java.util.List;

public class BuyersAdapter  extends RecyclerView.Adapter<BuyersAdapter.MyViewHolder>{

    Context mContext;
    List<User> mData;
    private static ClickListener clickListener;

    public BuyersAdapter(Context mContext, List<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    public BuyersAdapter(){}

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
        holder.buyer_email.setText(mData.get(position).getEmail());
        holder.buyer_address.setText(mData.get(position).getCreatedAt());
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView buyer_email;
        TextView buyer_name;
        TextView buyer_address;
        public MyViewHolder(View itemView){
            super(itemView);
            buyer_name = itemView.findViewById(R.id.business_name_hardcoded);
            buyer_email = itemView.findViewById(R.id.business_email_hardcoded);
            buyer_address = itemView.findViewById(R.id.business_address_one_hardcoded);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnClickListener(ClickListener clickListener){
        BuyersAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

}