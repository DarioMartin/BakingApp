package com.nanodegree.dario.bakingapp.utils;

import android.content.Context;

import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.model.Ingredient;

/**
 * Created by dariomartin on 3/8/17.
 */

public class IngredientUtils {

    public static String getIngredientName(Ingredient.Measure measure, int quantity, Context context) {
        switch (measure) {
            case CUP:
                return context.getResources().getQuantityString(R.plurals.cup_unit, quantity);
            case G:
                return context.getString(R.string.g_unit);
            case K:
                return context.getString(R.string.k_unit);
            case OZ:
                return context.getString(R.string.oz_unit);
            case TBLSP:
                return context.getString(R.string.tblsp_unit);
            case TSP:
                return context.getString(R.string.tsp_unit);
            case UNIT:
                return context.getString(R.string.unit_unit);
            default:
                return context.getString(R.string.unknown_unit);
        }
    }
}
