package com.nanodegree.dario.bakingapp.network;


import com.nanodegree.dario.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dariomartin on 7/8/17.
 */

public interface RecipesAPI {

    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();


}
