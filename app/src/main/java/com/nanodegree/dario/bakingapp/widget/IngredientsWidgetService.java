package com.nanodegree.dario.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.nanodegree.dario.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dariomartin on 9/8/17.
 */

public class IngredientsWidgetService extends IntentService {


    private static final String ACTION_UPDATE_INGREDIENTS_WIDGETS = "android.appwidget.action.update_ingredients";
    public static final String ACTION_UPDATE_WIDGET = "android.appwidget.action.APPWIDGET_UPDATE_INGREDIENTS";


    private static final String INGREDIENT_LIST = "ingredient_list";

    public IngredientsWidgetService() {
        super("IngredientsWidgetService");
    }

    public static void startUpdateIngredientsWidgets(Context context, List<String> ingredients) {
        Intent intent = new Intent(context, IngredientsWidgetService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENTS_WIDGETS.equals(action)) {
                handleActionUpdateIngredientsWidgets(intent.getStringArrayListExtra(INGREDIENT_LIST));
            }
        }
    }

    private void handleActionUpdateIngredientsWidgets(ArrayList<String> ingredientDescriptions) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakeryAppWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
        BakeryAppWidgetProvider.updateIngredientDescriptions(this, appWidgetManager, ingredientDescriptions, appWidgetIds);

        /*Intent dataUpdatedIntent = new Intent(ACTION_UPDATE_WIDGET);
        getApplicationContext().sendBroadcast(dataUpdatedIntent);*/

    }
}
