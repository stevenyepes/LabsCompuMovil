package co.edu.udea.compumovil.gr4.lab3weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SelectCity extends AppCompatActivity {

    private EditText choicedCity;
    private EditText timeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
    }

    public  void onCityEntered(View view){
        choicedCity = (EditText) findViewById(R.id.edittext_city);
        timeRefresh = (EditText)findViewById(R.id.edittext_time);

        String sendCity = choicedCity.getText().toString();
        sendCity = sendCity.replaceAll(" ","%20");
        String sendRefresh = timeRefresh.getText().toString();
        if (sendRefresh.isEmpty()) {

            sendRefresh = "60";
        }

        Intent intentCity = new Intent();

        intentCity.putExtra("carryCity",sendCity);
        intentCity.putExtra("refresh",sendRefresh);
        setResult(RESULT_OK, intentCity);

        super.finish();

    }

}
