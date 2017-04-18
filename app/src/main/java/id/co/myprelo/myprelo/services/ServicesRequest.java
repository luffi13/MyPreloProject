package id.co.myprelo.myprelo.services;

import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Luffi Aditya Sandy on 18/04/2017.
 */

public interface ServicesRequest {

    //call login api
    //@FormUrlEncoded
    @POST("auth/login")
    Call<JsonElement> attemptLogin(@Body HashMap<String, Object> requestData);

    //call lovelist appi
    @GET("me/lovelist")
    Call<JsonElement> getLoveList(@Header("Authorization") String token);


}
