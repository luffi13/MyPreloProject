package id.co.myprelo.myprelo.services;

import com.google.gson.JsonElement;

import java.util.HashMap;

import id.co.myprelo.myprelo.model.Detail;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Luffi Aditya Sandy on 18/04/2017.
 */

public interface ServicesRequest {

    //call login api
    //@FormUrlEncoded
    @POST("auth/login")
    Call<JsonElement> attemptLogin(@Body HashMap<String, Object> requestData);

    @FormUrlEncoded
    @POST("cart/checkout")
    Call<JsonElement> attemptLogin(@Header("Authorization") String token, @Field("cart_products") String jsonCart, @Field("shipping_address") String jsonShipping,
                                   @Field("payment_method") String paymentMethod);

    //call lovelist appi
    @GET("me/lovelist")
    Call<JsonElement> getLoveList(@Header("Authorization") String token);

    @GET("product/{param}")
    Call<Detail> getDetailItem(@Path("param") String id);


}
