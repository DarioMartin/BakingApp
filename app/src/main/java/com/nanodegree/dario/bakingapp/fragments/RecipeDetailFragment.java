package com.nanodegree.dario.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.adapters.RecipeStepsAdapter;
import com.nanodegree.dario.bakingapp.model.Recipe;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeDetailFragment extends Fragment implements RecipeStepsAdapter.RecipeDetailsClickListener{

    private static final String RECIPE = "FRAG_RECIPE";
    private Recipe recipe;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecipeStepsAdapter adapter;

    public RecipeDetailFragment() {

    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE, recipe);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        recipe = getArguments().getParcelable(RECIPE);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recipe_detail_recycler_view);

        layoutManager = new LinearLayoutManager (getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipeStepsAdapter();
        adapter.setStepClickListener(this);
        recyclerView.setAdapter(adapter);

        adapter.setRecipe(recipe);

        return rootView;
    }

    @Override
    public void stepClicked(int step) {

    }
}
