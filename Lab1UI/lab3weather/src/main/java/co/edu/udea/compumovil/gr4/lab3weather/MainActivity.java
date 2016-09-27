package co.edu.udea.compumovil.gr4.lab3weather;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;


import java.io.InputStream;
import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import co.edu.udea.compumovil.gr4.lab3weather.models.WeatherFull;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "weatherApp";
    private TextView textV_name, textV_temp, textV_hum, textV_desc, textV_date;
    private ImageView imgWeather;
    private EditText choicedCity;
    private static final int REQUEST_CODE = 10;
    private static String city = "";

    private static Long refresh = 1L;
    WeatherFull weather;
    private BroadcastReceiver receiver;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                weather = (WeatherFull) intent.getSerializableExtra(WeatherPullService.WEATHER_RESULT);
                textV_name = (TextView)findViewById(R.id.name);
                textV_temp = (TextView)findViewById(R.id.temperature);
                textV_hum = (TextView)findViewById(R.id.humidity);
                textV_desc = (TextView)findViewById(R.id.description);
                textV_date = (TextView)findViewById(R.id.date);


                Util util =  new Util();
                Float tempCelsius = util.kelvinToCelsius(Float.parseFloat(weather.getDataWeather().getTemp()));

                textV_name.setText(weather.getName());
                textV_temp.setText(tempCelsius + "ºC");
                textV_hum.setText(weather.getDataWeather().getHumidity());
                textV_desc.setText(weather.getWeather()[0].getDescription());

                String dateTime = DateFormat.getDateTimeInstance().format(new Date());
                textV_date.setText(dateTime);


                String imageUrl = "http://openweathermap.org/img/w/" + weather.getWeather()[0].getIcon()
                        + ".png";

                new DownloadImageTask((ImageView) findViewById(R.id.weather_img)) .execute(imageUrl);


            }
        };


        textV_name = (TextView)findViewById(R.id.name);
        textV_temp = (TextView)findViewById(R.id.temperature);
        textV_hum = (TextView)findViewById(R.id.humidity);
        textV_desc = (TextView)findViewById(R.id.description);
        textV_date = (TextView)findViewById(R.id.date);
        imgWeather = (ImageView) findViewById(R.id.weather_img);



        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                weather = (WeatherFull) intent.getSerializableExtra(WeatherPullService.WEATHER_RESULT);
                textV_name = (TextView) findViewById(R.id.name);
                textV_temp = (TextView) findViewById(R.id.temperature);
                textV_hum = (TextView) findViewById(R.id.humidity);
                textV_desc = (TextView) findViewById(R.id.description);
                textV_date = (TextView) findViewById(R.id.date);


                Util util = new Util();
                Float tempCelsius = util.kelvinToCelsius(Float.parseFloat(weather.getDataWeather().getTemp()));

                textV_name.setText(weather.getName());
                textV_temp.setText(tempCelsius + "ºC");
                textV_hum.setText(weather.getDataWeather().getHumidity());
                textV_desc.setText(weather.getWeather()[0].getDescription());

                String dateTime = DateFormat.getDateTimeInstance().format(new Date());
                textV_date.setText(dateTime);


                String imageUrl = "http://openweathermap.org/img/w/" + weather.getWeather()[0].getIcon()
                        + ".png";
                new DownloadImageTask((ImageView) findViewById(R.id.weather_img)) .execute(imageUrl);
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intentCity = new Intent(getApplicationContext(), SelectCity.class);
            startActivityForResult(intentCity, REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("carryCity")){
                city = data.getExtras().getString("carryCity");

            }

            if(data.hasExtra("refresh")){
                refresh = Long.parseLong(data.getExtras().getString("refresh"));

            }


        }

    }

    public void onClickButton(View view) {

        Calendar updateTime = Calendar.getInstance();
        updateTime.setTimeZone(TimeZone.getDefault());
        updateTime.set(Calendar.HOUR_OF_DAY, 11);
        updateTime.set(Calendar.MINUTE, 17);
        Intent downloader = new Intent(getApplicationContext(), StartServiceReciever.class);
        downloader.putExtra("cityToService", city);
        Log.d(TAG, city);
        downloader.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, downloader,       PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(), refresh*1000*60, pendingIntent);


    }


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(WeatherPullService.COPA_RESULT)
        );

    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}




