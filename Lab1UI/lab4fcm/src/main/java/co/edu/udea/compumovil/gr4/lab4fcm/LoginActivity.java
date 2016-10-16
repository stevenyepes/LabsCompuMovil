package co.edu.udea.compumovil.gr4.lab4fcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.gr4.lab4fcm.entities.User;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 674;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        if(auth.getCurrentUser() != null){

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);


        }else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                    .setTheme(R.style.AppTheme)
                    .build(), RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){



                user = auth.getCurrentUser();

                DatabaseReference userRef = mDatabase.child(user.getUid());

                ValueEventListener userListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get User object and use the values to update the UI
                        //User user = dataSnapshot.getValue(User.class);
                        // ...
                        if (!dataSnapshot.exists()) {
                            // User Does not Exists
                            Log.d("MyApp","Its alive");
                            writeNewUser(user.getUid(),user.getDisplayName(), user.getEmail());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w("LoginActivity", "loadPost:onCancelled", databaseError.toException());
                        // ...
                    }
                };

                userRef.addValueEventListener(userListener);

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child(userId).setValue(user);
    }
}
