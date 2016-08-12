package co.edu.udea.compumovil.gr4.lab1ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.List;

import models.User;


public class MainActivity extends AppCompatActivity {

    private User user;
    private static final String[] COUNTRIES = {"Argentina", "Bolivia", "Colombia", "Brazil", "Costa Rica", "Honduras",
            "Mexico", "Ecuador", "El Salvador"};


    private AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();

        initializeView();


    }

    public void initializeView() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoComplete_Country);
        textView.setAdapter(adapter1);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_hobbies);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hobbies_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)

                    break;
            case R.id.radio_female:
                if (checked)

                    break;
        }
    }
}

