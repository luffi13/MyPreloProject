package id.co.myprelo.myprelo.model;

/**
 * Created by Luffi Aditya Sandy on 18/04/2017.
 */

public class User {
    private String username, name, address, email, token, photoUrl;

    public User() {
    }

    public User(String username, String name, String address, String email, String token, String photoUrl) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.email = email;
        this.token = token;
        this.photoUrl = photoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
