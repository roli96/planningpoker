package com.example.scrumpoker.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.scrumpoker.Fragment.Question_Fragmant;
import com.example.scrumpoker.Objects.FirebaseRealtimeDatabaseHelper;
import com.example.scrumpoker.Objects.Question;
import com.example.scrumpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Owner_Start extends AppCompatActivity implements Question_Fragmant.OnFragmentInteractionListener{

    private EditText newquestionEditText;
    private Button sendButton;
    private Button exitOwner;
    private TextView owner;
    private FrameLayout fragmentContainer;
    private TextView questionView;
    private Button viewrate;
    private EditText IdEditText;
    private DatabaseReference mDatabaseReference;
    FirebaseRealtimeDatabaseHelper fbdb;
    int a=0;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_owner__start);


    inicialize();

    Intent intent = getIntent();

    mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");

    String s1 = intent.getStringExtra("com.example.scrumpoker.ownerName");

    final int s2 = intent.getIntExtra("com.example.scrumpoker.sessionId",-1);

    Log.i("FBaaa","SessionId: "+s2);
    Log.i("FBDB","SessionOwner: "+s1);

    fbdb = new FirebaseRealtimeDatabaseHelper(String.valueOf(s2));

    Log.i("FBDB","BAJVAN");

    CountDownTimer cnt = new CountDownTimer(2000, 1000) {
        public void onFinish() {
            owner.setText(fbdb.getSession().getOwnerName());
            Log.i("FBDB","BAJVAN: "+fbdb.getSession().getOwnerName());
            Log.i("FBDB","BAJVAN: "+fbdb.getSession().getEmployees().toString());
        }

        public void onTick(long millisUntilFinished) {
            Log.i("FBDB","BAJVAN: "+fbdb.getSession().getEmployees().toString());
        }
    }.start();


    fragmentContainer = (FrameLayout) findViewById(R.id.questionFragmant);

    sendButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String Text=newquestionEditText.getText().toString();
            int qid = fbdb.getSession().getQuestions().size();
            fbdb.addQuestion(String.valueOf(s2),new Question(String.valueOf(qid+1),"ddd",newquestionEditText.getText().toString()));
            mDatabaseReference.child(String.valueOf(s2)).child("activ").setValue(IdEditText.getText().toString());///set activ
            if(Text.isEmpty()){
                Toast.makeText(getApplicationContext(),"Already Empty !!!",Toast.LENGTH_SHORT).show();
            }else{
                newquestionEditText.setText("");
            }
            questionView.setText(Text);


           a++;

        }
    });

    viewrate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(a==0){
                Toast.makeText(getApplicationContext(),"First Send Question Pleas !!!",Toast.LENGTH_SHORT).show();
            }else{
                String employees=fbdb.getSession().getEmployees().toString();
                if(employees=="[]"){
                    Toast.makeText(getApplicationContext(),"Wait for polls !!!",Toast.LENGTH_SHORT).show();
                }else {
                    openFragment(employees);
                }
            }

        }
    });


    exitOwner.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intent=new Intent(Owner_Start.this,MainActivity.class);
            startActivity(intent);
        }
    });


}


    public void openFragment(String employees)
    {
        Question_Fragmant fragment=Question_Fragmant.newInstance(employees);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.questionFragmant,fragment,"Question_Fragmant").commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @SuppressLint("MissingSuperCall")
    protected void onStart() {
        super.onStart();

    }

    private void inicialize()
    {

        newquestionEditText = findViewById(R.id.newquestion);
        sendButton = findViewById(R.id.SendQuestion);
        exitOwner=findViewById(R.id.exit);
        owner=findViewById(R.id.Owner);
        questionView=findViewById(R.id.questiondisplay);
        viewrate=findViewById(R.id.viewrateemployees);
        IdEditText=findViewById(R.id.IdEditText);

    }


}
