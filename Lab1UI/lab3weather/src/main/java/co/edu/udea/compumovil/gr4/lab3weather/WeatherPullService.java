package co.edu.udea.compumovil.gr4.lab3weather;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import co.edu.udea.compumovil.gr4.lab3weather.models.WeatherFull;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class WeatherPullService extends IntentService {

    private static final String TAG = "weatherApp";
    static final public String COPA_RESULT = "co.edu.udea.compumovil.gr4.lab3weather.REQUEST_PROCESSED";
    public static final String WEATHER_RESULT = "weather";

    public WeatherPullService() {
        super("WeatherPullService");
    }

    LocalBroadcastManager broadcaster;



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            broadcaster = LocalBroadcastManager.getInstance(this);

            String city = intent.getExtras().getString("cityToService");
            Log.d(TAG,city);

            final String APPID = "6f0003d0842a175ea9003bfecf8121b7";
            final String PARAMS = "?q=" + city+ "&appid=" + APPID;
            final String REQUEST = "/weather";
            final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
            String URL = BASE_URL + REQUEST + PARAMS;
            sendRequest(URL);


        }
    }


    private void sendRequest(String URL) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET,
                        URL,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                JsonParser jsonParser = new JsonParser();
                                JsonObject jo = (JsonObject) jsonParser.parse(response.toString());

                                Gson gson;
                                gson = new Gson();
                                WeatherFull weather = gson.fromJson(jo, WeatherFull.class);

                                if(weather != null) {

                                    Log.d(TAG,"Pull done");

                                    Intent intent = new Intent(COPA_RESULT);
                                    intent.putExtra(WEATHER_RESULT, weather);
                                    broadcaster.sendBroadcast(intent);

                                    Log.d(TAG, weather.getDataWeather().getHumidity());
                                }
                                else
                                    Log.d(TAG, "**Wheather is null " );

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("Error: " + error.toString());
                        Log.d(TAG, "Error: " + error.toString());

                    }
                }
                );
        queue.add(jsObjRequest);
    }



}
