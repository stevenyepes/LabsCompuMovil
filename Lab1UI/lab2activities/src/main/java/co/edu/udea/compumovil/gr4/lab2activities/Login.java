package co.edu.udea.compumovil.gr4.lab2activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr4.lab2activities.entities.User;

public class Login extends AppCompatActivity {

    private static final String TAG = ".intent.action.MAIN";
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);


    }

    public void onLoginClick(View view) {

        // Check if the user and password field are valid
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        // Check for empty data in the form
        if (email.trim().length() > 0 && password.trim().length() > 0) {
            // login user
            checkLogin(email, password);
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext(),
                    "Please enter the credentials!", Toast.LENGTH_LONG)
                    .show();
        }


    }

    public void onSignUpClick(View view) {

        // Check if the user and password field are valid


        // Check if the user exist

        // Check if use and password are correct

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void checkLogin(String email, String password) {
        // Check if the user exist
        try {
            User user = User.find(User.class,"email = ?", email).get(0);
            // Check if use and password are correct
            if(user.getPassword().equals(password)){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),
                        "User or password Incorrect", Toast.LENGTH_LONG)
                        .show();
            }
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),
                    "User not found!", Toast.LENGTH_LONG)
                    .show();
        }


    }

}
