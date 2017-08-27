package com.nanodegree.dario.bakingapp.utils;

import android.content.Context;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Ingredient;
import com.nanodegree.dario.bakingapp.model.Recipe;

import java.util.ArrayList;

/**
 * Created by dariomartin on 3/8/17.
 */

public class Utils {

    public static String getIngredientDescription(Ingredient ingredient, Context context) {

        double quantity = ingredient.getQuantity();
        String measure;

        switch (ingredient.getMeasure()) {
            case CUP:
                measure = context.getResources().getQuantityString(R.plurals.cup_unit, (int) quantity);
                break;
            case G:
                measure = context.getString(R.string.g_unit);
                break;
            case K:
                measure = context.getString(R.string.k_unit);
                break;
            case OZ:
                measure = context.getString(R.string.oz_unit);
                break;
            case TBLSP:
                measure = context.getResources().getQuantityString(R.plurals.tblsp_unit, (int) quantity);
                break;
            case TSP:
                measure = context.getResources().getQuantityString(R.plurals.tsp_unit, (int) quantity);
                break;
            case UNIT:
                measure = context.getResources().getQuantityString(R.plurals.unit_unit, (int) quantity);
                break;
            default:
                measure = context.getString(R.string.unknown_unit);
        }

        return quantity + " " + measure + " " + ingredient.getName();
    }

    public static ArrayList<String> getIngredientDescriptions(Recipe recipe, Context context) {
        ArrayList<String> ingredientDescriptions = new ArrayList<>();

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredientDescriptions.add(getIngredientDescription(ingredient, context));
        }

        return ingredientDescriptions;
    }
}
