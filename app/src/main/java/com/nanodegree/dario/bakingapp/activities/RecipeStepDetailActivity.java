package com.nanodegree.dario.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.fragments.RecipeDetailFragment;
import com.nanodegree.dario.bakingapp.fragments.RecipeStepDetailFragment;
import com.nanodegree.dario.bakingapp.model.Step;

public class RecipeStepDetailActivity extends AppCompatActivity {

    public static final String STEP = "STEP";
    private Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        Intent intent = getIntent();
        step = intent.getExtras().getParcelable(STEP);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(step);
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
