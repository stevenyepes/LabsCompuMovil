package co.edu.udea.compumovil.gr4.lab2activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    private static final String TAG = ".intent.action.MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onLoginClick(View view) {

        // Check if the user and password field are valid


        // Check if the user exist

        // Check if use and password are correct

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onSignUpClick(View view) {

        // Check if the user and password field are valid


        // Check if the user exist

        // Check if use and password are correct

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
