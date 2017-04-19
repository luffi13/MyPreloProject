package id.co.myprelo.myprelo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.co.myprelo.myprelo.R;

/**
 * Created by Luffi Aditya Sandy on 19/04/2017.
 */

public class ItemHolder extends RecyclerView.ViewHolder{

    public TextView tv_name, tv_price;
    public ImageView photo;

    public ItemHolder(View itemView) {
        super(itemView);
        tv_name = (TextView)itemView.findViewById(R.id.tv_name);
        tv_price = (TextView)itemView.findViewById(R.id.tv_price);
        photo = (ImageView)itemView.findViewById(R.id.img_itemPhoto);
    }


}
