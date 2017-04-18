package id.co.myprelo.myprelo.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luffi Aditya Sandy on 18/04/2017.
 */

public class ApiController {

    public final String BASE_URL = "https://dev.prelo.id/api/";
    private Retrofit retrofit;
    private ServicesRequest servicesRequest;

    public ApiController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesRequest = retrofit.create(ServicesRequest.class);
    }

    public ServicesRequest getServicesRequest() {
        return servicesRequest;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
