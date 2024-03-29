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

import com.example.scrumpoker.Fragment.EmployesRateFragment;
import com.example.scrumpoker.Objects.FirebaseRealtimeDatabaseHelper;
import com.example.scrumpoker.Objects.Question;
import com.example.scrumpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Owner_Start extends AppCompatActivity implements EmployesRateFragment.OnFragmentInteractionListener {

    private EditText newquestionEditText;
    private Button sendButton;
    private Button exitOwner;
    private TextView owner;
    private FrameLayout fragmentContainer,fragmentContainer2;
    private TextView questionView;
    private Button viewrate,QuestionButton;
    private EditText IdEditText,employeesnumber;
    private DatabaseReference mDatabaseReference;
    FirebaseRealtimeDatabaseHelper fbdb;
    int a=0,b=0;

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
            String text=IdEditText.getText().toString();
            String textnumbemployees=employeesnumber.getText().toString();

            int qid = fbdb.getSession().getQuestions().size();
            fbdb.addQuestion(String.valueOf(s2),new Question(String.valueOf(qid+1),"ddd",newquestionEditText.getText().toString()));
              // ures mezok tesztelese , szukseges almonyzok fel toltese adatbazisba.(aktiv keredes szama,kivant felhasznalok szama).
            if(!Text.isEmpty() && text.isEmpty() && !textnumbemployees.isEmpty()){
                mDatabaseReference.child(String.valueOf(s2)).child("activ").setValue(fbdb.getSession().getQuestions().size());///set activ
                mDatabaseReference.child(String.valueOf(s2)).child("aktivemployees").setValue(0);
                mDatabaseReference.child(String.valueOf(s2)).child("numberemployees").setValue(IdEditText.getText().toString());///set number
                newquestionEditText.setText("");
                questionView.setText(Text);
            }else if(Text.isEmpty() && !text.isEmpty() && !textnumbemployees.isEmpty()){
                mDatabaseReference.child(String.valueOf(s2)).child("activ").setValue(IdEditText.getText().toString());///set activ
                mDatabaseReference.child(String.valueOf(s2)).child("aktivemployees").setValue(0);
                mDatabaseReference.child(String.valueOf(s2)).child("numberemployees").setValue(IdEditText.getText().toString());///set number
                newquestionEditText.setText("");
                questionView.setText(Text);
            }else if(Text.isEmpty() && text.isEmpty() && textnumbemployees.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Field is empty",Toast.LENGTH_SHORT).show();
            }else if(!Text.isEmpty() && text.isEmpty() && textnumbemployees.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Set number of employees!",Toast.LENGTH_SHORT).show();
            }else if(Text.isEmpty() && !text.isEmpty() && textnumbemployees.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Set number of employees!",Toast.LENGTH_SHORT).show();
            }

            a++;
        }
    });

    viewrate.setOnClickListener(new View.OnClickListener() {// szavazasok meg jelenitese
        @Override
        public void onClick(View v) {
            if(a==0){
                Toast.makeText(getApplicationContext(),"First Send Question Pleas !!!",Toast.LENGTH_SHORT).show();
            }else{
                String employees=fbdb.getSession().getEmployees().toString();
                if(!fbdb.getSession().getnumberemployees().equals(fbdb.getSession().getaktivemployees().toString())){
                    Toast.makeText(getApplicationContext(),"\n" + "Not everyone voted please wait!!!",Toast.LENGTH_SHORT).show();
                }else {
                    openFragment(employees);
                }
            }

        }
    });


    QuestionButton.setOnClickListener(new View.OnClickListener() {  // kerdesek meg jelenitese
        @Override
        public void onClick(View v) {
            String employees=fbdb.getSession().getQuestions().toString();
            if(employees=="[]"){
                Toast.makeText(getApplicationContext(),"No have question,pleas send question !!!",Toast.LENGTH_SHORT).show();
            }else {
                openFragment(employees);
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
        EmployesRateFragment fragment= EmployesRateFragment.newInstance(employees);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.questionFragmant,fragment,"EmployesRateFragment").commit();

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
        QuestionButton=findViewById(R.id.QuestionButton);
        employeesnumber=findViewById(R.id.NumEmployes);

    }


}
