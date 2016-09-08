package co.edu.udea.compumovil.gr4.lab2activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr4.lab2activities.entities.Place;
import co.edu.udea.compumovil.gr4.lab2activities.entities.User;

public class AddPlace extends AppCompatActivity {

    private Button btnAddPlace;
    private EditText inputPlaceName;
    private EditText inputDescription;
    private EditText inputPunctuation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        inputPlaceName = (EditText) findViewById(R.id.place_name);
        inputDescription = (EditText) findViewById(R.id.place_description);
        inputPunctuation = (EditText) findViewById(R.id.place_punctuation);
        btnAddPlace= (Button) findViewById(R.id.btnAddPlace);
    }

    public void onButtonAddPlaceClick(View v){

        String placeName = inputPlaceName.getText().toString();
        String description = inputDescription.getText().toString();
        String punctuation_s = inputPunctuation.getText().toString();

        if (!placeName.isEmpty() && !description.isEmpty() && !punctuation_s.isEmpty()) {
            int punctuation = Integer.parseInt(punctuation_s);
            Place place;
            try{
                place = Place.find(Place.class, "place_name=?", placeName).get(0);
                Toast.makeText(getApplicationContext(),
                        "Place already added", Toast.LENGTH_SHORT)
                        .show();

            }catch (Exception e) {
                place = new Place(placeName, description, punctuation);
                place.save();
                Toast.makeText(getApplicationContext(),
                        "New place has been added", Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("place_name",placeName);
                startActivity(intent);
            }


        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter the information", Toast.LENGTH_LONG)
                    .show();
        }

    }
}
