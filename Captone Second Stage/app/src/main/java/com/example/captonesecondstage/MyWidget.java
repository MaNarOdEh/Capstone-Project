package com.example.captonesecondstage;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


import com.example.captonesecondstage.ui.Activity.MainActivity;

import io.paperdb.Paper;


public class MyWidget extends AppWidgetProvider  {

    public static final String UPDATE_MEETING_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0);


        Paper.init(context);
        String user_name=Paper.book().read("NAME_WIDGET");
        String phone_txt=Paper.book().read("PHONE");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);

        views.setTextViewText(R.id.name_widget_txt,user_name);
        views.setTextViewText(R.id.phone_widget_txt,phone_txt);
        views.setOnClickPendingIntent(R.id.btn_call_widget,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0);
            Paper.init(context);
            String user_name=Paper.book().read("NAME_WIDGET");
            String phone_txt=Paper.book().read("PHONE");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);

            views.setTextViewText(R.id.name_widget_txt,user_name);
            views.setTextViewText(R.id.phone_widget_txt,phone_txt);
            views.setOnClickPendingIntent(R.id.btn_call_widget,pendingIntent);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
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

        }
        super.onReceive(context, intent);

    }

}