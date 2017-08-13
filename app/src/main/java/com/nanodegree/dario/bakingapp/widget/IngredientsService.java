package com.nanodegree.dario.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

import com.nanodegree.dario.bakingapp.R;

import java.util.ArrayList;

public class IngredientsService extends IntentService {
    public static final String ACTION_UPDATE_INGREDIENTS = "update_ingredients";
    public static final String RECIPE_NAME = "recipe_name";
    public static final String INGREDIENTS = "ingredients";

    public IngredientsService() {
        super("IngredientsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENTS.equals(action)) {
                final String recipeName = intent.getStringExtra(RECIPE_NAME);
                final ArrayList<String> ingredients = intent.getStringArrayListExtra(INGREDIENTS);
                updateIngredientsWidget(recipeName, ingredients);
            }
        }
    }

    private void updateIngredientsWidget(String recipeName, ArrayList<String> ingredients) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidgetProvider.class));
        IngredientsWidgetProvider.updateIngredients(this, appWidgetManager, recipeName, ingredients, appWidgetIds);
    }

}
