package com.example.captonesecondstage;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


import java.util.ArrayList;

import io.paperdb.Paper;


public class MyWidget extends AppWidgetProvider  {

    static  String CLICK_ACTION="CLIKED";
    public static final String UPDATE_MEETING_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";

    public static final String EXTRA_ITEM = "com.example.edockh.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Paper.init(context);
        ArrayList<String> ingredient=Paper.book().read("INGREDIENTS");
        String INGREDIENTS= Paper.book().read("INGREDIENTS");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(UPDATE_MEETING_ACTION)) {
            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context,MyWidget.class));
            Log.e("received", intent.getAction());

         //   mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_widget_items);
        }
        super.onReceive(context, intent);

    }


}