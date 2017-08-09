package com.nanodegree.dario.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Ingredient;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.StepViewHolder> {

    private final String TAG = getClass().getName();

    private Recipe recipe;
    private RecipeDetailsClickListener stepClickListener;
    private Context context;

    public interface RecipeDetailsClickListener {
        void stepSelected(int step);
    }

    public RecipeStepsAdapter() {
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_card, parent, false);
        context = view.getContext();
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.setStep(position);
    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size() + 1;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        notifyDataSetChanged();
    }

    public void setStepClickListener(RecipeDetailsClickListener stepClickListener) {
        this.stepClickListener = stepClickListener;
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView name;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                stepClickListener.stepSelected(getAdapterPosition());
            } catch (NullPointerException e) {
                Log.d(TAG, "There is no click listener set");
            }
        }

        public void setStep(int step) {
            if (step == 0) {
                name.setText(prepareIngredientsList(recipe.getIngredients()));
                itemView.setEnabled(false);
            } else {
                name.setText(recipe.getSteps().get(step - 1).getShortDescription());
            }
        }

        private String prepareIngredientsList(List<Ingredient> ingredients) {
            String ingredientsText = "Ingredients for " + recipe.getServings() + " " +
                    context.getResources().getQuantityString(R.plurals.people, recipe.getServings()) + "\n\n";

            for (Ingredient ingredient : ingredients) {
                ingredientsText += "\u2022 " + ingredient.getQuantity() + " " +
                        Utils.getIngredientDescription(ingredient, context) + "\n";
            }

            return ingredientsText;
        }
    }


}
