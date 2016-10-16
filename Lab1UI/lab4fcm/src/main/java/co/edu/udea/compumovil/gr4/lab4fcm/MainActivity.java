package co.edu.udea.compumovil.gr4.lab4fcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mUsers;
    private FirebaseAuth auth;
    private RecyclerView chatList;
    private EditText newMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatList = (RecyclerView) findViewById(R.id.message_list);
        newMessage = (EditText) findViewById(R.id.new_message);
        chatList.setHasFixedSize(true);
        chatList.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("chats");
        mUsers = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabase.keepSynced(true);

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
                                viewHolder.mUser.setText(user.getDisplayName());

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Getting Post failed, log a message
                                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
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

    public void onSendClick(View view) {

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
}
