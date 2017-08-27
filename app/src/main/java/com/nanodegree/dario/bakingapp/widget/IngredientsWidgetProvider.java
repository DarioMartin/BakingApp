package com.nanodegree.dario.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.nanodegree.dario.bakingapp.BakingAppApplication;
import com.nanodegree.dario.bakingapp.R;
import com.nanodegree.dario.bakingapp.activities.MainActivity;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                String recipeName, ArrayList<String> ingredients, int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredients);

        //Set title pending intent
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.RECIPE_NAME, recipeName);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_header, pendingIntent);

        //Set title
        views.setTextViewText(R.id.recipe_name, recipeName);

        //Set remote views adapter
        Intent rmIntent = new Intent(context, IngredientsRemoteViewsService.class);
        BakingAppApplication.widgetIngredients = ingredients;
        views.setRemoteAdapter(R.id.widget_list, rmIntent);

        AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);


        //Set ingredients pending intent
        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list, appPendingIntent);

        //Set empty view
        views.setEmptyView(R.id.widget_list, R.id.widget_empty);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, context.getString(R.string.app_name), new ArrayList<String>(), appWidgetId);
        }
    }

    public static void updateIngredients(Context context, AppWidgetManager appWidgetManager,
                                         String recipeName, ArrayList<String> ingredients, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipeName, new ArrayList<>(ingredients), appWidgetId);
        }
    }
}

