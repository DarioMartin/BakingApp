package com.nanodegree.dario.bakingapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nanodegree.dario.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dariomartin on 7/8/17.
 */

public class Controller {

    private static Controller instance;
    private static RecipesAPI recipesAPI;


    public Controller() {

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecipesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        recipesAPI = retrofit.create(RecipesAPI.class);
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void getRecipes(final RequestCallback callback) {


        recipesAPI.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.code() == 200) {
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    public interface RequestCallback<T> {
        void onResponse(T response);

        void onFailure(String message);
    }


}
