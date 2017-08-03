package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.fragments.RecipeDetailFragment;
import com.nanodegree.dario.bakingapp.fragments.RecipeStepDetailFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.model.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener {

    public static final String RECIPE = "RECIPE";
    private Recipe recipe;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        recipe = intent.getExtras().getParcelable(RECIPE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipe);
        fragmentTransaction.replace(R.id.container, fragment);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        if (isTablet) {
            RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(recipe.getSteps().get(0));
            fragmentTransaction.replace(R.id.container_detail, fragment_detail);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onStepSelected(Step step) {
        if (isTablet) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(step);
            fragmentTransaction.replace(R.id.container_detail, fragment_detail);
            fragmentTransaction.commit();
        } else {
            final Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(RecipeStepDetailActivity.STEP, step);
            startActivity(intent);
        }
    }
}
