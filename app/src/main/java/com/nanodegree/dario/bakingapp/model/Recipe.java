package com.nanodegree.dario.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dariomartin on 1/8/17.
 */

public class Recipe implements Parcelable {

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private String image;
    private int servings;

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Creator<Recipe>() {
        public Recipe createFromParcel(Parcel source) {
            Recipe recipe = new Recipe();
            recipe.id = source.readInt();
            recipe.name = source.readString();
            recipe.ingredients = source.readArrayList(Ingredient.class.getClassLoader());
            recipe.steps = source.readArrayList(Step.class.getClassLoader());
            recipe.image = source.readString();
            recipe.servings = source.readInt();
            return recipe;
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeString(image);
        parcel.writeInt(servings);
    }

}
