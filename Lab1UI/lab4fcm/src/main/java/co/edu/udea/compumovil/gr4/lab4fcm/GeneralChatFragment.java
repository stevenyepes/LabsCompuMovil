package co.edu.udea.compumovil.gr4.lab4fcm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import co.edu.udea.compumovil.gr4.lab4fcm.entities.ChatMessage;
import co.edu.udea.compumovil.gr4.lab4fcm.entities.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralChatFragment extends Fragment {

    private DatabaseReference mDatabase;
    private DatabaseReference mUsers;
    private FirebaseAuth auth;
    private RecyclerView chatList;
    private EditText newMessage;
    private Button sendButton;


    public GeneralChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_general_chat, container, false);

        chatList = (RecyclerView) view.findViewById(R.id.message_list);
        newMessage = (EditText) view.findViewById(R.id.new_message);
        sendButton = (Button) view.findViewById(R.id.send_message);
        chatList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        chatList.setLayoutManager(layoutManager);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = newMessage.getText().toString();

                if(!TextUtils.isEmpty(msg)){
                    ChatMessage chat = new ChatMessage();
                    chat.setmDate(new Date());
                    chat.setmSender(auth.getCurrentUser().getUid());
                    chat.setMtext(msg);
                    mDatabase.push().setValue(chat);
                    newMessage.setText("");

                }
            }
        });

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("chats");
        mUsers = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabase.keepSynced(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(

                        ChatMessage.class,
                        R.layout.item,
                        MessageViewHolder.class,
                        mDatabase
                ) {
                    @Override
                    protected void populateViewHolder(final MessageViewHolder viewHolder,
                                                      final ChatMessage model, final int position) {
                        viewHolder.mText.setText(model.getMtext());
                        ValueEventListener userListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Get Post object and use the values to update the UI
                                User user = dataSnapshot.getValue(User.class);
                                viewHolder.mUser.setText(user.getDisplayName()+":");

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Getting Post failed, log a message
                                Log.w("GeneralChatActivity", "loadPost:onCancelled", databaseError.toException());
                                // ...
                            }
                        };
                        mUsers.child(model.getmSender()).addListenerForSingleValueEvent(userListener);


                    }
                };

        chatList.setAdapter(adapter);
    }

    public  static class MessageViewHolder
            extends RecyclerView.ViewHolder {

        TextView mText;
        TextView mUser;


        public MessageViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.msg);
            mUser = (TextView) itemView.findViewById(R.id.user);

        }


    }



}
