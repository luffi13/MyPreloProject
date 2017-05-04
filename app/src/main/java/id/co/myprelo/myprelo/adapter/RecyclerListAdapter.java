package id.co.myprelo.myprelo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.co.myprelo.myprelo.R;
import id.co.myprelo.myprelo.activity.DetailItem;
import id.co.myprelo.myprelo.holder.ItemHolder;
import id.co.myprelo.myprelo.model.Detail;
import id.co.myprelo.myprelo.model.Item;
import id.co.myprelo.myprelo.model.User;

/**
 * Created by Luffi Aditya Sandy on 19/04/2017.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter <ItemHolder>{

    private ArrayList<Item> listData;
    private Activity activity;

    public RecyclerListAdapter(ArrayList<Item> listData, Activity activity) {
        this.listData = listData;
        this.activity = activity;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return  new ItemHolder(view);

    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final Item item = listData.get(position);
        holder.tv_name.setText(item.getName());
        holder.tv_price.setText("Rp "+item.getPrice());
        Glide.with(activity).load(item.getDisplay_picts()[0]).into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailItem.class);
                intent.putExtra("id",item.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData==null) return 0;

        return listData.size();
    }

    public ArrayList<Item> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Item> listData) {
        this.listData = listData;
    }
}
