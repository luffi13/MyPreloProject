package id.co.myprelo.myprelo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.myprelo.myprelo.R;
import id.co.myprelo.myprelo.model.User;
import id.co.myprelo.myprelo.session.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(getApplicationContext());
        currentUser = sessionManager.getCurrentUser();

        //load profile layout
        loadProfileLayout();
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
}
