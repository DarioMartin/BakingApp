package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.activities.RecipeDetailActivity;
import com.nanodegree.dario.bakingapp.fragments.RecipesMainFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;

public class MainActivity extends AppCompatActivity implements RecipesMainFragment.OnRecipeClickListerner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        Bundle b = new Bundle();

        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
