package com.example.scrumpoker.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrumpoker.Objects.RecyclerViewAdapter;
import com.example.scrumpoker.Objects.SessionRecycler;
import com.example.scrumpoker.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private DatabaseReference mDatabaseReference;
    private RecyclerView mRecyclerView;
    private View mView;
    private RecyclerViewAdapter mRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_list_layout,container,false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText nameEditText = view.findViewById(R.id.nameEditText);
        super.onViewCreated(view, savedInstanceState);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        final ArrayList<SessionRecycler> arrayList = new ArrayList<>();
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("FBDB",dataSnapshot.getKey());
                String sessionName="";
                String sessionId="";
                String ownerName="";
                if (dataSnapshot.child("sessionName").getValue() != null)
                {
                    sessionName = dataSnapshot.child("sessionName").getValue().toString();
                }
                if (dataSnapshot.child("sessionId").getValue() != null)
                {
                    sessionId = dataSnapshot.child("sessionId").getValue().toString();
                }
                if (dataSnapshot.child("ownerName").getValue() != null)
                {
                    ownerName = dataSnapshot.child("ownerName").getValue().toString();
                }
                SessionRecycler sessionRecycler = new SessionRecycler(sessionName,sessionId,ownerName);
                arrayList.add(sessionRecycler);
                mRecyclerView = mView.findViewById(R.id.listRecyclerView);
                mRecyclerAdapter = new RecyclerViewAdapter(arrayList,nameEditText);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(mView.getContext());
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mRecyclerAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("FBDB",dataSnapshot.getKey());
                String sessionName="";
                String sessionId="";
                String ownerName="";
                if (dataSnapshot.child("sessionName").getValue() != null)
                {
                    sessionName = dataSnapshot.child("sessionName").getValue().toString();
                }
                if (dataSnapshot.child("sessionId").getValue() != null)
                {
                    sessionId = dataSnapshot.child("sessionId").getValue().toString();
                }
                if (dataSnapshot.child("ownerName").getValue() != null)
                {
                    ownerName = dataSnapshot.child("ownerName").getValue().toString();
                }
                SessionRecycler sessionRecycler = new SessionRecycler(sessionName,sessionId,ownerName);
                arrayList.add(sessionRecycler);
                mRecyclerView = mView.findViewById(R.id.listRecyclerView);
                mRecyclerAdapter = new RecyclerViewAdapter(arrayList,nameEditText);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(mView.getContext());
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mRecyclerAdapter);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.i("FBDB","ListFragment.java arrayList");



    }
}
