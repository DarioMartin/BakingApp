package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.fragments.RecipesMainFragment;
import com.nanodegree.dario.bakingapp.idlingResources.SimpleIdlingResource;
import com.nanodegree.dario.bakingapp.model.Recipe;

public class MainActivity extends AppCompatActivity implements RecipesMainFragment.OnRecipeClickListener {


    @Nullable
    private SimpleIdlingResource idlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIdlingResource();
        if (idlingResource != null) idlingResource.setIdleState(false);
    }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.RECIPE, recipe);
        startActivity(intent);
    }

    @Override
    public void onDataLoaded() {
        if (idlingResource != null) idlingResource.setIdleState(true);
    }
}
