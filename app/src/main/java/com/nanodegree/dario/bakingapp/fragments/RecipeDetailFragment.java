package com.nanodegree.dario.bakingapp.fragments;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeDetailFragment extends Fragment implements RecipeStepsAdapter.RecipeDetailsClickListener {

    private static final String RECIPE = "FRAGMENT_RECIPE";
    private Recipe recipe;

    @BindView(R.id.recipe_detail_recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private RecipeStepsAdapter adapter;
    private OnStepClickListener callback;

    public interface OnStepClickListener {
        void onStepSelected(int stepPosition);
    }

    public RecipeDetailFragment() {

    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE, recipe);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepClickListerner");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ButterKnife.bind(this, rootView);

        recipe = getArguments().getParcelable(RECIPE);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipeStepsAdapter();
        adapter.setStepClickListener(this);
        recyclerView.setAdapter(adapter);

        adapter.setRecipe(recipe);

        return rootView;
    }

    @Override
    public void stepSelected(int step) {
        if (step > 0) {
            callback.onStepSelected(step - 1);
        }
    }

}
