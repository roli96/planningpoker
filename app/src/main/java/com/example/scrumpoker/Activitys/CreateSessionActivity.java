package com.example.scrumpoker.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrumpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateSessionActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private EditText sessionOwnerNameEditText, sessionNameEditText;
    private Button createButton;
    private int lastKey;
    private String proba="123456";
    int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_session);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");


        inicialize();
        getSessionLastKey();




        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createSession();

                Query query = mDatabaseReference.orderByChild("ownerName").limitToFirst(1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot child:dataSnapshot.getChildren()){
                            Log.i("FBDB","KEY: "+child.getKey()+" OWNERNAME: "+child.child("ownerName").getValue());

                            if(sessionNameEditText.getText().toString()==child.child("ownerName").getValue()){
                                a=Integer.parseInt(child.getKey());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Log.i("FBDB","Create "+getLastKey());

                     if(sessionOwnerNameEditText.getText().toString().isEmpty()){   //Test if text view is empty
                               Toast.makeText(getApplicationContext(),"Nick name field is empty !!!", Toast.LENGTH_SHORT).show();
                     } else{
                         if(sessionNameEditText.getText().toString().equals("123456")) {    // Test if admin cod is correct
                             Intent intent = new Intent(CreateSessionActivity.this, Owner_Start.class);
                             intent.putExtra("com.example.scrumpoker.ownerName",sessionOwnerNameEditText.getText().toString());
                             if(a==0) {
                                 intent.putExtra("com.example.scrumpoker.sessionId", getLastKey());
                             }else {
                                 intent.putExtra("com.example.scrumpoker.sessionId", a);
                             }
                             startActivity(intent);
                         } else{
                             Toast.makeText(getApplicationContext(),"Incorrect Admin Cod. Please try it again", Toast.LENGTH_SHORT).show();
                         }
                     }


            }
        });

    }

    private void inicialize()  // variable declared
    {

        sessionOwnerNameEditText = findViewById(R.id.sessionOwnerNameEditText);
        sessionNameEditText = findViewById(R.id.sessionNameEditText);
        createButton = findViewById(R.id.createButton);

    }

    private void createSession()   //update to database
    {   if(a==0) {
        getSessionLastKey();
        Log.i("FBDB", "session_last_ID: " + getLastKey());

        mDatabaseReference.child(String.valueOf(++lastKey)).child("ownerName").setValue(sessionOwnerNameEditText.getText().toString());
        mDatabaseReference.child(String.valueOf(lastKey)).child("sessionName").setValue(sessionNameEditText.getText().toString());
        mDatabaseReference.child(String.valueOf(lastKey)).child("sessionId").setValue(lastKey);
    }
    }

    private void getSessionLastKey() //search last session id
    {
        Query query = mDatabaseReference.orderByChild("Session_ID").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key;
                    key = child.getKey();
                    Log.i("FBDB","session_last_ID: "+key);
                    try {
                        setLastKey(Integer.parseInt(key));
                    }catch (NumberFormatException e)
                    {
                        Log.i("FBDBERROR",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("FBDBERROR",databaseError.toString());
            }
        });
    }

    public int getLastKey() {
        return lastKey;
    }

    public void setLastKey(int lastKey) {
        Log.i("FBDB","session_last_ID: "+lastKey);
        this.lastKey = lastKey;
    }
}
