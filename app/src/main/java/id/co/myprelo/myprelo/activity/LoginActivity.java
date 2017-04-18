package id.co.myprelo.myprelo.activity;

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
import id.co.myprelo.myprelo.services.ApiController;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText et_password;
    AutoCompleteTextView et_usernameOrEmail;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
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
                    String token = _data.get("token").getAsString();

                    Toast.makeText(LoginActivity.this, "success Login"+token, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(LoginActivity.this, "failed Login", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
