package id.co.myprelo.myprelo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luffi Aditya Sandy on 04/05/2017.
 */

public class Detail implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private  String price;


    @SerializedName("seller")
    private  Seller seller;

    @SerializedName("display_picts")
    private String[]  display_picts;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String[] getDisplay_picts() {
        return display_picts;
    }

    public void setDisplay_picts(String[] display_picts) {
        this.display_picts = display_picts;
    }
}
