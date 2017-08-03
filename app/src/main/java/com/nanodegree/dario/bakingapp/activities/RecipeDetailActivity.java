package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    private static final java.lang.String CURRENT_STEP = "CURRENT_STEP";
    private Recipe recipe;
    private boolean isTablet;
    private int currentStep;

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
            currentStep = 0;
            if (savedInstanceState != null) {
                currentStep = savedInstanceState.getInt(CURRENT_STEP);
            }
            RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(recipe.getSteps().get(currentStep));
            fragmentTransaction.replace(R.id.container_detail, fragment_detail);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP, currentStep);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStepSelected(int stepSelected) {
        currentStep = stepSelected;
        if (isTablet) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(recipe.getSteps().get(stepSelected));
            fragmentTransaction.replace(R.id.container_detail, fragment_detail);
            fragmentTransaction.commit();
        } else {
            final Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(RecipeStepDetailActivity.STEP, recipe.getSteps().get(stepSelected));
            startActivity(intent);
        }
    }
}
