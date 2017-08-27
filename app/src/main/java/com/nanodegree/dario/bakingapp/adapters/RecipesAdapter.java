package com.nanodegree.dario.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private final String TAG = getClass().getName();

    private List<Recipe> recipes;
    private RecipeClickListener recipeClickListener;

    public interface RecipeClickListener {
        void recipeClicked(Recipe recipe);
    }

    public RecipesAdapter() {
        recipes = new ArrayList<>();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.setRecipe(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = new ArrayList<>(recipes);
        notifyDataSetChanged();
    }

    public void setRecipeClickListener(RecipeClickListener recipeClickListener) {
        this.recipeClickListener = recipeClickListener;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name) TextView name;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setRecipe(Recipe recipe) {
            name.setText(recipe.getName());
        }

        @Override
        public void onClick(View v) {
            try{
                recipeClickListener.recipeClicked(recipes.get(getAdapterPosition()));
            }catch (NullPointerException e){
                Log.d(TAG, "There is no click listener set");
            }
        }
    }


}
