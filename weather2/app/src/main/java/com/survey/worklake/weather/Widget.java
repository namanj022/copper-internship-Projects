package com.survey.worklake.weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Widget extends AppWidgetProvider {
    public static final String ACTION_TEXT_CHANGED = "Weather.TEXT_CHANGED";
    RemoteViews remoteViews;
   @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
           super.onUpdate(context,appWidgetManager,appWidgetIds);
       remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widgetlayout);
       }

    public void onReceive(Context context, Intent intent){
        String response=intent.getStringExtra("weather");
        JSONObject json= null;
        try {
            json = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
           remoteViews.setTextViewText(R.id.cityfield,json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            remoteViews.setTextViewText(R.id.detailsfield,
                    (details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa"));

            remoteViews.setTextViewText(R.id.current_temperaturefield,
                    (String.format("%.2f", main.getDouble("temp")-273.15)+ " ℃"));

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            remoteViews.setTextViewText(R.id.updatedfield,("Last update: " + updatedOn));


        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
        int[] ids = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(new ComponentName(context.getApplicationContext(), Widget.class));
        Widget myWidget = new Widget();
        myWidget.onUpdate(context.getApplicationContext(), AppWidgetManager.getInstance(context.getApplicationContext()), ids);
    }
    }

