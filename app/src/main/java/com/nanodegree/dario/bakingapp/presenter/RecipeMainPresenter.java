package com.nanodegree.dario.bakingapp.presenter;

import android.support.test.espresso.IdlingResource;

import com.nanodegree.dario.bakingapp.fragments.RecipesMainFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.network.Controller;

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
