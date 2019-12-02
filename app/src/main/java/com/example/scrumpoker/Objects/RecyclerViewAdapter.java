package com.example.scrumpoker.Objects;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrumpoker.Activitys.EmployeeActivity;
import com.example.scrumpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private DatabaseReference mDatabaseReference;
    ArrayList<SessionRecycler> mList;
    public static final String EXTRA_EMPLOYEE_NAME = "com.example.scrumpoker.Employee";
    public static final String EXTRA_SESSION_ID = "com.example.scrumpoker.ID";
    private View itemView;
    private EditText nameEditText;


    public RecyclerViewAdapter(ArrayList<SessionRecycler> list,EditText nickname) {
        this.mList = list;
        this.nameEditText = nickname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("FBDB","Adapter");
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_list_row_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Log.i("FBDB","Adapter");
        if (holder !=null)
        {
            holder.idTextView.setText(mList.get(position).getSessionId());
            holder.sessionNameTextView.setText(mList.get(position).getSessionName());
            holder.ownerNameTextView.setText(mList.get(position).getOwnerName());
            Log.i("FBDB",mList.get(position).getSessionId()+" "+mList.get(position).getSessionName()+" "+mList.get(position).getOwnerName());
        }else
        {
            Log.i("FBDB","Adapter problem HOlder empty");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameEditText.getText().toString().equals("")) {
                    mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
                    mDatabaseReference.child(mList.get(position).getSessionId()).child("Employees").child(nameEditText.getText().toString()).child("employeeName").setValue(nameEditText.getText().toString());
                    Intent intent = new Intent(holder.itemView.getContext(), EmployeeActivity.class);
                    intent.putExtra(EXTRA_EMPLOYEE_NAME, nameEditText.getText().toString());
                    intent.putExtra(EXTRA_SESSION_ID, mList.get(position).getSessionId());
                    holder.itemView.getContext().startActivity(intent);
                }else
                {
                    Toast.makeText(itemView.getContext(), "Enter your nickname", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView,sessionNameTextView,ownerNameTextView,nameEditText;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("FBDB","Adapter");
            idTextView = itemView.findViewById(R.id.idTextView);
            sessionNameTextView = itemView.findViewById(R.id.sessionNameTextView);
            ownerNameTextView = itemView.findViewById(R.id.ownerNameTextView);
        }
    }
}
