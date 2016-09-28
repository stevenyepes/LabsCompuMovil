package co.edu.udea.compumovil.gr4.lab3weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import co.edu.udea.compumovil.gr4.lab3weather.models.WeatherFull;

/**
 * Created by usuario on 25/09/2016.
 */

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        String namewid = MainActivity.weather.getName();
        String tempwid = MainActivity.weather.getDataWeather().getTemp();
        int inttemp = Integer.parseInt(tempwid);
        inttemp = inttemp-273;
        String dateTime = DateFormat.getDateTimeInstance().format(new Date());



        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds){

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            remoteViews.setTextViewText(R.id.update, namewid + ": " +inttemp+ "°C" + dateTime);
            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }





    }
}
