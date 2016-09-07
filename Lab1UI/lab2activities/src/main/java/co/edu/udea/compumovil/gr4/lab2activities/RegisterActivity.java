package co.edu.udea.compumovil.gr4.lab2activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr4.lab2activities.entities.User;

public class RegisterActivity extends AppCompatActivity {


    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputAge = (EditText) findViewById(R.id.age);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

    }

    public void onButtonRegisterClick(View view) {

        String name = inputFullName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String age_s = inputAge.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !age_s.isEmpty()) {
            int age = Integer.parseInt(age_s);
            User user;
            try{
                user = User.find(User.class,"email=?", email).get(0);

                Toast.makeText(getApplicationContext(),
                        "Email already exist", Toast.LENGTH_SHORT)
                        .show();

            }catch (Exception e) {
                user = new User(name, email, password, age);
                user.save();
                Toast.makeText(getApplicationContext(),
                        "Congratulations! your account was created", Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }


        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter your details!", Toast.LENGTH_LONG)
                    .show();
        }
    }


}
