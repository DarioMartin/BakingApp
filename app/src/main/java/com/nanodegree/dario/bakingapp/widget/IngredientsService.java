package com.nanodegree.dario.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

import com.nanodegree.dario.bakingapp.model.Recipe;

public class IngredientsService extends IntentService {
    public static final String ACTION_UPDATE_RECIPE = "update_recipe";
    public static final String RECIPE = "recipe_name";

    public IngredientsService() {
        super("IngredientsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE.equals(action)) {
                Recipe recipe = intent.getExtras().getParcelable(RECIPE);
                updateIngredientsWidget(recipe);
            }
        }
    }

    private void updateIngredientsWidget(Recipe recipe) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidgetProvider.class));
        IngredientsWidgetProvider.updateIngredients(this, appWidgetManager, recipe, appWidgetIds);
    }

}
