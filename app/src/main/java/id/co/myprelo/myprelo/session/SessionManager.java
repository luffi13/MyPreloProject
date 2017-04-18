package id.co.myprelo.myprelo.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.co.myprelo.myprelo.activity.LoginActivity;
import id.co.myprelo.myprelo.model.User;

/**
 * Created by Luffi Aditya Sandy on 18/04/2017.
 */

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String PREF_NAME = "PreloPreferences";
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_PHOTO_URL = "photoUrl";


    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;

        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setIsLoggedIn(boolean flag){

        editor.putBoolean(IS_LOGIN,flag);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public void setProfileData(User user){
        editor.putString(KEY_NAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_USERNAME,user.getUsername());
        editor.putString(KEY_ADDRESS,user.getAddress());
        editor.putString(KEY_TOKEN,user.getToken());
        editor.putString(KEY_PHOTO_URL,user.getPhotoUrl());
        editor.commit();
    }

    public User getCurrentUser(){
        User user = new User();
        user.setName(preferences.getString(KEY_NAME,null));
        user.setEmail(preferences.getString(KEY_EMAIL,null));
        user.setAddress(preferences.getString(KEY_ADDRESS,null));
        user.setUsername(preferences.getString(KEY_USERNAME,null));
        user.setPhotoUrl(preferences.getString(KEY_PHOTO_URL,null));
        user.setToken(preferences.getString(KEY_TOKEN,null));
        return user;
    }


    public void logOut(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
