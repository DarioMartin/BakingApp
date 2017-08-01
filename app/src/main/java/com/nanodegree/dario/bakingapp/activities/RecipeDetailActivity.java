package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.fragments.RecipeDetailFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE = "RECIPE";
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        Intent intent = getIntent();
        recipe = intent.getExtras().getParcelable(RECIPE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipe);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

    }
}
