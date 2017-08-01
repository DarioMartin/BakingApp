package com.nanodegree.dario.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Recipe;

/**
 * Created by dariomartin on 1/8/17.
 */

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.StepViewHolder> {

    private final String TAG = getClass().getName();

    private Recipe recipe;
    private RecipeDetailsClickListener stepClickListener;

    public interface RecipeDetailsClickListener {
        void stepClicked(int step);
    }

    public RecipeDetailsAdapter() {
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_card, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        if (position == 0) {
            holder.setText("Ingredients");
        } else {
            holder.setText(recipe.getSteps().get(position - 1).getShortDescription());
        }
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

        private TextView name;

        public StepViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        public void setText(String step) {
            name.setText(step);
        }

        @Override
        public void onClick(View v) {
            try {
                stepClickListener.stepClicked(getAdapterPosition());
            } catch (NullPointerException e) {
                Log.d(TAG, "There is no click listener set");
            }
        }
    }


}
