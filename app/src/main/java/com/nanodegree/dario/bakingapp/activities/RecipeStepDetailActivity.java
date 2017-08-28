package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.fragments.RecipeStepDetailFragment;
import com.nanodegree.dario.bakingapp.model.Recipe;

public class RecipeStepDetailActivity extends AppCompatActivity implements
        RecipeStepDetailFragment.StepDetailsListener {

    public static final String RECIPE = "RECIPE";
    public static final String CURRENT_STEP = "CURRENT_STEP";
    private Recipe recipe;
    private int currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        recipe = intent.getExtras().getParcelable(RECIPE);
        currentStep = intent.getExtras().getInt(CURRENT_STEP);

        setTitle(recipe.getName());

        if (savedInstanceState != null) {
            currentStep = savedInstanceState.getInt(CURRENT_STEP);
        }

        updateState(currentStep);
    }

    private void updateState(int stepPosition) {
        if (stepPosition >= 0 && stepPosition < recipe.getSteps().size()) {
            currentStep = stepPosition;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(
                    recipe.getSteps().get(currentStep),
                    currentStep == 0,
                    isLasStep());
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
    }

    private boolean isLasStep() {
        return currentStep == (recipe.getSteps().size() - 1);
    }

    @Override
    public void onNextStep() {
        if (!isLasStep()) {
            updateState(currentStep + 1);
        }
    }

    @Override
    public void onPreviousStep() {
        if (currentStep != 0) updateState(currentStep - 1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP, currentStep);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
