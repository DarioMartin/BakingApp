package com.nanodegree.dario.bakingapp.network;

import com.google.gson.annotations.SerializedName;
import com.nanodegree.dario.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 7/8/17.
 */

public class RecipesPage {

    private Recipes recipes;

    public List<Recipe> getRecipes() {
        return recipes.recipes;
    }

    public class Recipes {
        private List<Recipe> recipes = new ArrayList<>();
    }
}
