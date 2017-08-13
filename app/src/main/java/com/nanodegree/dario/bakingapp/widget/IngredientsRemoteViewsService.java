package com.nanodegree.dario.bakingapp.widget;

import android.content.Intent;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nanodegree.dario.bakingapp.R;

import java.lang.ref.Reference;
import java.util.ArrayList;


/**
 * Created by dariomartin on 11/8/17.
 */

public class IngredientsRemoteViewsService extends RemoteViewsService {

    public static final String INGREDIENTS = "ingredients";

    @Override
    public RemoteViewsFactory onGetViewFactory(final Intent intent) {

        return new RemoteViewsFactory() {

            public ArrayList<String> ingredients;

            @Override
            public void onCreate() {
                ingredients = new ArrayList<>();
            }

            @Override
            public void onDataSetChanged() {
                final long identityToken = Binder.clearCallingIdentity();
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                ingredients.clear();
            }

            @Override
            public int getCount() {
                return ingredients == null ? 0 : ingredients.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_item);
                views.setTextViewText(R.id.ingredient, ingredients.get(position));
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }


            @Override
            public boolean hasStableIds() {
                return true;
            }

        };
    }
}
