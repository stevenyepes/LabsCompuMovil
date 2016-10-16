package co.edu.udea.compumovil.gr4.lab4fcm;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by steven on 16/10/16.
 */

public class Lab4fcm  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
