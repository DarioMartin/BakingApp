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
import com.nanodegree.dario.bakingapp.widget.IngredientsService;

public class RecipeDetailActivity extends AppCompatActivity implements
        RecipeDetailFragment.OnStepClickListener,
        RecipeStepDetailFragment.StepDetailsListener {

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

        setTitle(recipe.getName());

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
            RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(
                    recipe.getSteps().get(currentStep),
                    currentStep == 0,
                    isLastStep());
            fragmentTransaction.replace(R.id.container_detail, fragment_detail);
        }

        runIngredientsService();

        fragmentTransaction.commit();

    }

    private void runIngredientsService() {
        Intent intent = new Intent(this, IngredientsService.class);
        intent.putExtra(IngredientsService.RECIPE, recipe);
        intent.setAction(IngredientsService.ACTION_UPDATE_RECIPE);
        getApplicationContext().startService(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP, currentStep);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStepSelected(int stepSelected) {
        if (stepSelected >= 0 && stepSelected < recipe.getSteps().size()) {
            currentStep = stepSelected;
            if (isTablet) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RecipeStepDetailFragment fragment_detail = RecipeStepDetailFragment.newInstance(
                        recipe.getSteps().get(currentStep),
                        currentStep == 0,
                        isLastStep());
                fragmentTransaction.replace(R.id.container_detail, fragment_detail);
                fragmentTransaction.commit();
            } else {
                final Intent intent = new Intent(this, RecipeStepDetailActivity.class);
                intent.putExtra(RecipeStepDetailActivity.RECIPE, recipe);
                intent.putExtra(RecipeStepDetailActivity.CURRENT_STEP, stepSelected);
                startActivity(intent);
            }
        }
    }

    private boolean isLastStep() {
        return currentStep == recipe.getSteps().size() - 1;
    }

    @Override
    public void onNextStep() {
        if (!isLastStep()) onStepSelected(currentStep + 1);
    }

    @Override
    public void onPreviousStep() {
        if (currentStep != 0) onStepSelected(currentStep - 1);
    }
}
