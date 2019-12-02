package com.example.scrumpoker.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.scrumpoker.Activitys.Owner_Start;
import com.example.scrumpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateFragment extends Fragment {
    View view;
    private DatabaseReference mDatabaseReference;
    private EditText sessionOwnerNameEditText, sessionNameEditText;
    private Button createButton;
    private int lastKey;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        view = inflater.inflate(R.layout.fragment_new_layout,container,false);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        inicialize();
        getSessionLastKey();
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sessionNameEditText.getText().toString().equals("") && !sessionOwnerNameEditText.getText().toString().equals(""))
                {
                createSession();
                Log.i("FBDB","Create "+getLastKey());
                Intent intent = new Intent(view.getContext(), Owner_Start.class);
                intent.putExtra("com.example.scrumpoker.ownerName",sessionOwnerNameEditText.getText().toString());
                intent.putExtra("com.example.scrumpoker.sessionId",getLastKey());
                startActivity(intent);
                }
                else
                {
                    Toast.makeText(view.getContext(), "Enter your nickname or session name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void inicialize()
    {
        sessionOwnerNameEditText = view.findViewById(R.id.sessionOwnerNameEditText);
        sessionNameEditText = view.findViewById(R.id.sessionNameEditText);
        createButton = view.findViewById(R.id.createButton);
    }

    private void createSession()
    {
        getSessionLastKey();
        Log.i("FBDB","session_last_ID: "+getLastKey());

        mDatabaseReference.child(String.valueOf(++lastKey)).child("ownerName").setValue(sessionOwnerNameEditText.getText().toString());
        mDatabaseReference.child(String.valueOf(lastKey)).child("sessionName").setValue(sessionNameEditText.getText().toString());
        mDatabaseReference.child(String.valueOf(lastKey)).child("sessionId").setValue(lastKey);
    }

    private void getSessionLastKey()
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
