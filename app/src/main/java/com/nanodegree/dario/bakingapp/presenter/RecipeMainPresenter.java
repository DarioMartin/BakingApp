package com.nanodegree.dario.bakingapp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nanodegree.dario.bakingapp.fragments.RecipesMainFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.network.Controller;

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

    public void getRecipes() {

        Controller.getInstance().getRecipes(new Controller.RequestCallback<List<Recipe>>() {
            @Override
            public void onResponse(List<Recipe> recipes) {
                view.addRecipes(recipes);
            }

            @Override
            public void onFailure(String message) {
            }

        });

    }

}
