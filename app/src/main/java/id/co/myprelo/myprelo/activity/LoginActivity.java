package id.co.myprelo.myprelo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;

import id.co.myprelo.myprelo.R;
import id.co.myprelo.myprelo.model.User;
import id.co.myprelo.myprelo.services.ApiController;
import id.co.myprelo.myprelo.session.SessionManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText et_password;
    AutoCompleteTextView et_usernameOrEmail;
    Button btn_login;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()){
            gotoProfileActivity();
        }
    }

    //get view
    private void getView(){
        et_usernameOrEmail = (AutoCompleteTextView) findViewById(R.id.usernameOrEmail_et);
        et_password = (EditText)findViewById(R.id.password_et);
        btn_login  = (Button)findViewById(R.id.login_btn);

        //set click handler
        btn_login.setOnClickListener(clickHandler);

    }

    //instance click listener
    private View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_btn:
                    login();
            }
        }
    };

    //verify
    private boolean verifyInput(){

        boolean result = true;

        if(et_usernameOrEmail.getText().toString().equals("")){
            et_usernameOrEmail.setError("tidak boleh kosong");
            result = false;
        }

        if(et_password.getText().toString().length()<6){
            et_password.setError("minimal 6 karakter");
            result = false;
        }

        return result;
    }

    //authentication
    private void login(){
        if (verifyInput()){
            callLoginApi(et_usernameOrEmail.getText().toString(),et_password.getText().toString());
        }

    }

    //get profile data from json
    private void loadSession(JsonObject _data){
        User user = new User();
        user.setToken(_data.get("token").getAsString());
        user.setName(_data.get("fullname").getAsString());
        user.setUsername(_data.get("username").getAsString());
        user.setEmail(_data.get("email").getAsString());

        JsonObject defaultAddress = _data.getAsJsonObject("default_address");
        user.setAddress(defaultAddress.get("subdistrict_name").getAsString()+", "+
                defaultAddress.get("region_name").getAsString()+", "+
                defaultAddress.get("province_name").getAsString()
        );

        JsonObject profile  = _data.getAsJsonObject("profile");
        user.setPhotoUrl(profile.get("pict").getAsString());

        sessionManager.setIsLoggedIn(true);
        sessionManager.setProfileData(user);
    }

    //go to profile activity
    private void gotoProfileActivity(){
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    //call auth api
    public void callLoginApi(String username, String password){
        final ApiController apiController = new ApiController();
        HashMap<String, Object> requestData = new HashMap<>();
        requestData.put("username_or_email",username);
        requestData.put("password",password);

        Call<JsonElement> callLoginService = apiController.getServicesRequest().attemptLogin(requestData);
        callLoginService.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()){
                    JsonObject jsonResponse = response.body().getAsJsonObject();
                    JsonObject _data = jsonResponse.getAsJsonObject("_data");

                    //load data to session
                    loadSession(_data);
                    gotoProfileActivity();
                }
                else if (response.code()==400){
                    Converter<ResponseBody, JsonElement> errorConverter =
                            apiController.getRetrofit().responseBodyConverter(JsonElement.class, new Annotation[0]);
                    // Convert the error body into our Error type.
                    try {
                        JsonElement json = errorConverter.convert(response.errorBody());
                        Toast.makeText(LoginActivity.this, json.getAsJsonObject().get("_message").getAsString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "please check your connection", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
