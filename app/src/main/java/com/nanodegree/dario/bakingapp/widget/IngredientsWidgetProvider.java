package com.nanodegree.dario.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.nanodegree.dario.bakingapp.BakingAppApplication;
import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.activities.MainActivity;
import com.nanodegree.dario.bakingapp.activities.RecipeDetailActivity;
import com.nanodegree.dario.bakingapp.model.Recipe;
import com.nanodegree.dario.bakingapp.utils.Utils;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Recipe recipe, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredients);

        //Set title pending intent
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
        views.setOnClickPendingIntent(R.id.widget_header, mainPendingIntent);

        if (recipe != null) {
            //Set title
            String recipeName = recipe.getName();
            views.setTextViewText(R.id.recipe_name, recipeName);

            //Set remote views adapter
            Intent rmIntent = new Intent(context, IngredientsRemoteViewsService.class);
            BakingAppApplication.widgetIngredients = Utils.getIngredientDescriptions(recipe, context);
            views.setRemoteAdapter(R.id.widget_list, rmIntent);
            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);

            //Set ingredients pending intent
            Intent recipeIntent = new Intent(context, RecipeDetailActivity.class);
            recipeIntent.putExtra(RecipeDetailActivity.RECIPE, recipe);
            PendingIntent ingredientsPendingIntent = PendingIntent.getActivity(context, 0, recipeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_header, ingredientsPendingIntent);
        }

        //Set empty view
        views.setEmptyView(R.id.widget_list, R.id.widget_empty);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, null, appWidgetId);
        }
    }

    public static void updateIngredients(Context context, AppWidgetManager appWidgetManager,
                                         Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }
}

