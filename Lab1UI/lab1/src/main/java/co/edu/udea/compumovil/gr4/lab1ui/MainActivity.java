package co.edu.udea.compumovil.gr4.lab1ui;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

import models.User;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private User user;
    private static final String[] COUNTRIES = {"Argentina", "Bolivia", "Brazil", "Chile", "Colombia",
            "Costa Rica", "Cuba", "Dominican Republic", "Ecuador", "El Salvador", "Guatemala","Haiti",
            "Honduras", "Mexico", "Nicaragua", "Panama", "Paraguay", "Peru", "Uruguay", "Venezuela"};

    private String hobbie;


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
        spinner.setOnItemSelectedListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    user.setGender("male");
                    break;
            case R.id.radio_female:
                if (checked)
                    user.setGender("female");
                    break;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        hobbie = parent.getItemAtPosition(pos).toString();
        user.setHobbies(hobbie);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onCheckBoxSelect(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox is selected
        switch (view.getId()) {
            case R.id.checkbox_Favorite:
                if (checked)
                    user.setFavorite("true");
                else
                    user.setFavorite("false");
                break;

        }
    }

    public void onShowClick(View view) {

        // Get reference from the view
        EditText fn = (EditText) findViewById(R.id.editText_FirstName);
        EditText ln = (EditText) findViewById(R.id.editText_LastName);
        AutoCompleteTextView c = (AutoCompleteTextView) findViewById(R.id.autoComplete_Country);
        EditText p = (EditText) findViewById(R.id.editText_Phone);
        EditText a = (EditText) findViewById(R.id.editText_Address);
        EditText e = (EditText) findViewById(R.id.editText_Email);

        // Check if the required fields are filled
        if( "".equals(fn.getText().toString()) || "".equals(c.getText().toString())
        || "".equals( p.getText().toString()) || "".equals(e.getText().toString())) {
            Toast.makeText(this,getResources().getString(R.string.error_message),Toast.LENGTH_SHORT).show();
            return;

        }
        // Set the information on User Object
        user.setFirstName(fn.getText().toString());
        user.setLastName(ln.getText().toString());
        user.setCountry(c.getText().toString());
        user.setPhone(p.getText().toString());
        user.setAddress(a.getText().toString());
        user.setEmail(e.getText().toString());


        // Show the info into the view
        TextView tv = (TextView) findViewById(R.id.textView_Show);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(user.toString());

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        TextView d = (TextView) findViewById(R.id.textView_Birthdate);
        d.setText( getResources().getString(R.string.birthdate) + ": "+ day + "/" + month + "/" + year);

        // Build the birthday and set to the user
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        Date birthday = c.getTime();
        user.setBirthDate(birthday);
    }

    public void showDatePickerDialog(View v) {

        FragmentManager fm = getFragmentManager();
        DatePickerFragment dialogFragment = new DatePickerFragment ();
        dialogFragment.show(fm, getResources().getString(R.string.birthdate));
    }
}

