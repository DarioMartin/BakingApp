package com.nanodegree.dario.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.nanodegree.dario.bakingapp.utils.IngredientUtils;

/**
 * Created by dariomartin on 1/8/17.
 */

public class Ingredient implements Parcelable {

    public enum Measure {
        CUP, G, UNIT, TBLSP, TSP, K, OZ
    }

    private double quantity;
    private Measure measure;
    @SerializedName("ingredient")
    private String name;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel source) {
            Ingredient ingredient = new Ingredient();
            ingredient.quantity = source.readDouble();
            ingredient.name = source.readString();
            String msr = source.readString();
            if (msr != null) ingredient.measure = Measure.valueOf(msr);
            return ingredient;
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(quantity);
        parcel.writeString(name);
        parcel.writeString(measure != null ? measure.name() : null);
    }

}
