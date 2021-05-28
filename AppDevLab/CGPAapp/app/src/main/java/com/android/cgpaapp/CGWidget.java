package com.android.cgpaapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class CGWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        DbHelper helper = new DbHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Students",null);
        String info="";
        while(cursor.moveToNext())
        {
            String name;
            int roll;
            double cg;
            roll = cursor.getInt(cursor.getColumnIndexOrThrow("ROLLNUMBER"));
            name = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
            cg = cursor.getDouble(cursor.getColumnIndexOrThrow("CGPA"));
            info += "RollNumber : "+roll+" CGPA : "+cg+"\n";
        }
        CharSequence widgetText = info;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.c_g_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
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