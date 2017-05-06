package com.example.teacher.db050602;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class ShowTimeWidget extends AppWidgetProvider {
    Context context;
    Handler handler = new Handler();
    AppWidgetManager appWidgetManager;
    static int appWidgetId;
    Runnable MyUpdateTime = new Runnable() {
        @Override
        public void run() {
            updateThisTime();
        }
    };
    private void updateThisTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        CharSequence widgetText = sdf.format(new Date());
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.show_time_widget);
        remoteViews.setTextViewText(R.id.appwidget_text, widgetText);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        handler.postDelayed(MyUpdateTime, 1000);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        ShowTimeWidget.appWidgetId = appWidgetId;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        for (int appWidgetId : appWidgetIds) {
            updateThisTime();
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

