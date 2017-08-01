package com.nanodegree.dario.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dario.bakingapp.R;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeStepDetailFragment extends Fragment {

    public RecipeStepDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        return rootView;
    }
}
