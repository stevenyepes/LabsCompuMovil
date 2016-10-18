package co.edu.udea.compumovil.gr4.lab4fcm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.udea.compumovil.gr4.lab4fcm.entities.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {


    private FirebaseAuth auth;
    private DatabaseReference mUsers;
    private RecyclerView usersList;
    User user;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        auth = FirebaseAuth.getInstance();
        mUsers = FirebaseDatabase.getInstance().getReference().child("users");
        usersList = (RecyclerView) view.findViewById(R.id.users_list);
        usersList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        usersList.setLayoutManager(layoutManager);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<User, MessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<User, MessageViewHolder>(
                        User.class,
                        R.layout.user_item,
                        MessageViewHolder.class,
                        mUsers
                ) {
            @Override
            protected void populateViewHolder(final MessageViewHolder viewHolder, final User model, int position) {
                viewHolder.mUser.setText(model.getDisplayName());
                viewHolder.mEmail.setText(model.getEmail());

                viewHolder.mUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PrivateChatFragment chatFragment = new PrivateChatFragment();
                        Bundle args = new Bundle();
                        args.putString("receiver", viewHolder.mEmail.getText().toString());
                        chatFragment.setArguments(args);
                        getFragmentManager().beginTransaction().replace(R.id.content_main, chatFragment).commit();

                        Toast.makeText(getContext(), "chat opened with user: "+model.getDisplayName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        usersList.setAdapter(adapter);
    }

    public  static class MessageViewHolder
            extends RecyclerView.ViewHolder {

        TextView mEmail;
        TextView mUser;


        public MessageViewHolder(View itemView) {
            super(itemView);
            mEmail = (TextView) itemView.findViewById(R.id.email_user);
            mUser = (TextView) itemView.findViewById(R.id.id_user);

        }

    }
}
