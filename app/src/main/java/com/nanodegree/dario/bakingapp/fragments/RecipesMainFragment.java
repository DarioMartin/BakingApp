package com.nanodegree.dario.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.adapters.RecipesAdapter;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.presenter.RecipeMainPresenter;

import java.util.List;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipesMainFragment extends Fragment implements RecipesAdapter.RecipeClickListener {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private RecipesAdapter adapter;
    private OnRecipeClickListerner callback;
    private RecipeMainPresenter presenter;

    public interface OnRecipeClickListerner {
        void onRecipeSelected(Recipe recipe);
    }

    public RecipesMainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnRecipeClickListerner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClickListerner");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_main, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recipes_recycler_view);


        layoutManager = new GridLayoutManager(getContext(), 1, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipesAdapter();
        adapter.setRecipeClickListener(this);
        recyclerView.setAdapter(adapter);

        presenter = new RecipeMainPresenter(this);
        presenter.getRecipes();

        return rootView;
    }

    @Override
    public void recipeClicked(Recipe recipe) {
        callback.onRecipeSelected(recipe);
    }


    public void addRecipes(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
    }


}
