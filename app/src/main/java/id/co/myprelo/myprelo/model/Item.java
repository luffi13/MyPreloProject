package id.co.myprelo.myprelo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Luffi Aditya Sandy on 19/04/2017.
 */

public class Item implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private  String price;

    @SerializedName("display_picts")
    private String[]  display_picts;


    public Item() {
    }

    public Item(String name, String price, String[] display_picts) {
        this.name = name;
        this.price = price;
        this.display_picts = display_picts;
    }

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

    public String[] getDisplay_picts() {
        return display_picts;
    }

    public void setDisplay_picts(String[] display_picts) {
        this.display_picts = display_picts;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", prize='" + price + '\'' +
                ", display_pict=" + Arrays.toString(display_picts) +
                '}';
    }
}
