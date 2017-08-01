package com.nanodegree.dario.bakingapp.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nanodegree.dario.bakingapp.fragments.RecipesMainFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeMainPresenter {

    private RecipesMainFragment view;

    public RecipeMainPresenter(RecipesMainFragment view) {
        this.view = view;
    }

    public void getRecipes(){

        String recipesString = loadRecipesFromAsset();

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(recipesString, listType);

        view.addRecipes(recipes);
    }

    public String loadRecipesFromAsset() {
        String json = null;
        try {
            InputStream is = view.getContext().getAssets().open("baking_recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
