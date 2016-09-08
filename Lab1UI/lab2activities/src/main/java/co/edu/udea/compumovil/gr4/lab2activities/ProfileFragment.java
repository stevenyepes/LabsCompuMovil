package co.edu.udea.compumovil.gr4.lab2activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.udea.compumovil.gr4.lab2activities.entities.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView userProfileName;
    TextView userProfileEmail;
    TextView userProfileAge;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // get references form the view
        userProfileName = (TextView) view.findViewById(R.id.user_profile_name);
        userProfileEmail = (TextView) view.findViewById(R.id.textview_email);
        userProfileAge = (TextView) view.findViewById(R.id.textview_age);

        User user = (User) getArguments().getSerializable("profile");
        userProfileName.setText(user.getUsername());
        userProfileAge.setText("Edad: " + user.getAge());
        userProfileEmail.setText(user.getEmail());
        return view;

    }

}
