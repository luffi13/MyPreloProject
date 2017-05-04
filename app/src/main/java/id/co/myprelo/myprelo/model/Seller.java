package id.co.myprelo.myprelo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luffi Aditya Sandy on 04/05/2017.
 */

public class Seller {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private  String email;

    @SerializedName("pict")
    private  String pict;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }
}
