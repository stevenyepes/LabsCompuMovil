package co.edu.udea.compumovil.gr4.lab3weather;

import android.content.Intent;
import android.os.Bundle;
import java.text.DateFormat;
import java.util.Date;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import co.edu.udea.compumovil.gr4.lab3weather.models.Weather;
import co.edu.udea.compumovil.gr4.lab3weather.models.WeatherFull;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "weatherApp";
    private TextView textV_name, textV_temp, textV_hum, textV_desc, textV_date;
    //private ImageView
    private FragmentMenu fmenu;
    private EditText choicedCity;
    private static final int REQUEST_CODE = 10;
    private static String city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textV_name = (TextView)findViewById(R.id.name);
        textV_temp = (TextView)findViewById(R.id.temperature);
        textV_hum = (TextView)findViewById(R.id.humidity);
        textV_desc = (TextView)findViewById(R.id.description);
        textV_date = (TextView)findViewById(R.id.date);

        /*if(savedInstanceState == null){
            fmenu = new FragmentMenu();
            FragmentTransaction fMenuTransaction = getSupportFragmentManager().beginTransaction();
            fMenuTransaction.add(R.id.main_layout, fmenu, "FMENU");
            fMenuTransaction.commit();


        }*/

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

            /*fmenu = new FragmentMenu();
            FragmentTransaction fMenuTransaction = getSupportFragmentManager().beginTransaction();
            fMenuTransaction.add(R.id.main_layout, fmenu);
            fMenuTransaction.commit();
            return true;*/
        }

        return super.onOptionsItemSelected(item);
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


                                    Util util =  new Util();
                                    Float tempCelsius = util.kelvinToCelsius(Float.parseFloat(weather.getDataWeather().getTemp()));

                                    textV_name.setText(weather.getName());
                                    textV_temp.setText(tempCelsius + "ºC");
                                    textV_hum.setText(weather.getDataWeather().getHumidity());
                                    textV_desc.setText(weather.getWeather()[0].getDescription());

                                    String dateTime = DateFormat.getDateTimeInstance().format(new Date());
                                    textV_date.setText(dateTime);

                                    // setear la vista con los datos del clima
                                }
                                else
                                    Log.d(TAG, "**earthQuakes is null " );
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("carryCity")){
                city = data.getExtras().getString("carryCity");
            }
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickButton(View view) {


        final String APPID = "6f0003d0842a175ea9003bfecf8121b7";


        final String PARAMS = "?q=" + city + "&appid=" + APPID;
        final String REQUEST = "/weather";
        final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        String URL = BASE_URL + REQUEST + PARAMS;
        sendRequest(URL);
    }



}


