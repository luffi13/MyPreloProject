package id.co.myprelo.myprelo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myprelo.myprelo.R;
import id.co.myprelo.myprelo.model.Detail;
import id.co.myprelo.myprelo.model.Item;
import id.co.myprelo.myprelo.services.ApiController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailItem extends AppCompatActivity {

    ImageView itemImage;
    TextView itemName, itemPrice, sellerName, sellerEmail;
    CircleImageView sellerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        String id = getIntent().getStringExtra("id");

        itemImage = (ImageView)findViewById(R.id.item_image);
        itemName  = (TextView)findViewById(R.id.item_name_tv);
        itemPrice  = (TextView)findViewById(R.id.item_price_tv);
        sellerName  = (TextView)findViewById(R.id.seller_name);
        sellerEmail  = (TextView)findViewById(R.id.seller_email);
        sellerImage = (CircleImageView)findViewById(R.id.image_seller);

        callDetailApi(id);
    }

    private void callDetailApi(String id){
        ApiController apiController = new ApiController();

        Call<Detail> callLoginService = apiController.getServicesRequest().getDetailItem(id);
        callLoginService.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Detail detail = response.body();
                Glide.with(DetailItem.this).load(detail.getDisplay_picts()[0]).into(itemImage);
                Glide.with(DetailItem.this).load(detail.getSeller().getPict()).into(sellerImage);

                itemName.setText(detail.getName());
                itemPrice.setText(detail.getPrice());

                sellerName.setText(detail.getSeller().getUsername());
                sellerEmail.setText(detail.getSeller().getEmail());
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });
    }

//    private void callCheckout(String id){
//
//        HashMap<String,String> cart = new HashMap<>();
//        cart.put("product_id",id);
//        Gson gson = new Gson();
//
//        String json = gson.toJson(cart);
//
//        HashMap<String,String> shipping = new HashMap<>();
//        shipping.put("address",);
//        shipping.put("province_id",);
//        shipping.put("region_id",);
//        shipping.put("subdistrict_id",);
//        shipping.put("postal_code",);
//        shipping.put("recipient_name",);
//        shipping.put("recipient_phone",);
//
//        String json2
//
//    }


}
