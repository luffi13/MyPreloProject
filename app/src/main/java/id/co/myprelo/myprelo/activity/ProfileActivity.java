package id.co.myprelo.myprelo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myprelo.myprelo.R;
import id.co.myprelo.myprelo.adapter.RecyclerListAdapter;
import id.co.myprelo.myprelo.model.Item;
import id.co.myprelo.myprelo.model.User;
import id.co.myprelo.myprelo.services.ApiController;
import id.co.myprelo.myprelo.session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private User currentUser;
    private RecyclerView rv_list;
    private RecyclerListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(getApplicationContext());
        currentUser = sessionManager.getCurrentUser();
        adapter = new RecyclerListAdapter(null,this);
        layoutManager = new LinearLayoutManager(this);

        rv_list = (RecyclerView)findViewById(R.id.rv_list);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(adapter);


        //load profile layout
        loadProfileLayout();
        retrieveListLoveData();
    }

    private void loadProfileLayout(){
        CircleImageView profilePict ;
        TextView tv_username, tv_fullname, tv_address, tv_email;

        profilePict = (CircleImageView)findViewById(R.id.profilePicture);
        tv_username = (TextView)findViewById(R.id.tv_username);
        tv_fullname = (TextView)findViewById(R.id.tv_fullname);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_email= (TextView)findViewById(R.id.tv_email);

        tv_username.setText(currentUser.getUsername());
        tv_fullname.setText(currentUser.getName());
        tv_email.setText(currentUser.getEmail());
        tv_address.setText(currentUser.getAddress());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(profilePict);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.sign_out_menu:
                sessionManager.logOut();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void retrieveListLoveData(){
        ApiController apiController = new ApiController();

        final ProgressDialog progressDialog  = new ProgressDialog(this);
        progressDialog.setTitle("retrieving data");
        progressDialog.setMessage("please wait....");
        progressDialog.show();
        Log.d("token", "retrieveListLoveData: "+currentUser.getToken());
        Call<JsonElement> callLoginService = apiController.getServicesRequest().getLoveList("Token "+currentUser.getToken());
        callLoginService.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.isSuccessful()){
                    JsonObject jsonResponse = response.body().getAsJsonObject();
                    Gson gson = new Gson();
                    ArrayList<Item> listData = gson.fromJson(jsonResponse.get("_data"),new TypeToken<ArrayList<Item>>(){}.getType());
                    adapter.setListData(listData);
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileActivity.this, "connection problem", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
