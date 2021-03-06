package com.nanodegree.dario.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.adapters.RecipesAdapter;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.presenter.RecipeMainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipesMainFragment extends Fragment implements RecipesAdapter.RecipeClickListener {

    private static final String RECYCLER_VIEW_STATE = "RECYCLER_VIEW_STATE";
    @BindView(R.id.recipes_recycler_view)
    RecyclerView recyclerView;

    private GridLayoutManager layoutManager;
    private RecipesAdapter adapter;
    private OnRecipeClickListener callback;
    private RecipeMainPresenter presenter;
    private Parcelable recylerViewState;

    public interface OnRecipeClickListener {
        void onRecipeSelected(Recipe recipe);

        void onDataLoaded();
    }

    public RecipesMainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClickListerner");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_main, container, false);

        ButterKnife.bind(this, rootView);

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);

        layoutManager = new GridLayoutManager(getContext(), isTablet ? 3 : 1, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipesAdapter();
        adapter.setRecipeClickListener(this);
        recyclerView.setAdapter(adapter);

        presenter = new RecipeMainPresenter(this);

        if (savedInstanceState != null) {
            recylerViewState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE);
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getRecipes();
    }

    @Override
    public void recipeClicked(Recipe recipe) {
        callback.onRecipeSelected(recipe);
    }


    public void addRecipes(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
        callback.onDataLoaded();
        restoreLayoutManagerPosition();
    }

    private void restoreLayoutManagerPosition() {
        if (recylerViewState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(recylerViewState);
        }
    }

    public void loadFailed() {
        Toast.makeText(getContext(), R.string.recipe_loading_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RECYCLER_VIEW_STATE, recyclerView.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }
}
