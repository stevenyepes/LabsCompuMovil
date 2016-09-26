package co.edu.udea.compumovil.gr4.lab3weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by steven on 23/09/16.
 */
public class StartServiceReciver extends BroadcastReceiver{
    @Override


    public void onReceive(Context context, Intent intent) {

        String city = intent.getExtras().getString("cityToService");

        Intent dailyUpdater = new Intent(context, WeatherPullService.class);
        dailyUpdater.putExtra("cityToService", city);
        context.startService(dailyUpdater);
        Log.d("AlarmReceiver", "Called context.startService from AlarmReceiver.onReceive");
    }
}
