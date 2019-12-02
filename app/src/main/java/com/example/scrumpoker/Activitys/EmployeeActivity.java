package com.example.scrumpoker.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrumpoker.Objects.FirebaseRealtimeDatabaseHelper;
import com.example.scrumpoker.R;

public class EmployeeActivity extends AppCompatActivity {



    private FirebaseRealtimeDatabaseHelper fb;

    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        final Button customButton1 = findViewById(R.id.custom_button1);
        final TextView textView=findViewById(R.id.sendText);
        final EditText questionText = findViewById(R.id.questionText);

        Intent intent= getIntent();
        final String employeeName = intent.getStringExtra(JoinSessionActivity.EXTRA_EMPLOYEE_NAME);
        final String sessionID = intent.getStringExtra(JoinSessionActivity.EXTRA_SESSION_ID);
        fb =new FirebaseRealtimeDatabaseHelper(sessionID);

        String IdQuestion=getIntent().getStringExtra("IdQuestion");
        counter=0;

        new CountDownTimer(2000, 1000) {  //polling slow-down
            public void onFinish() {
                questionText.setText(fb.getSession().getQuestions().get(Integer.parseInt(fb.getSession().getActiv())-1).getQuestion());
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();


        customButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmployeeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("1");

            }
        });

        final Button customButton2 = findViewById(R.id.custom_button2);


        customButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmployeeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("2");
            }
        });


        final Button customButton3 = findViewById(R.id.custom_button3);


        customButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmployeeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("3");
            }
        });

        final Button customButton4=findViewById(R.id.custom_button4);

        customButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmployeeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("4");
            }
        });

        final Button customButton5=findViewById(R.id.custom_button5);

        customButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmployeeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("5");
            }
        });

        final Button sendButton=findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //update rate
                if(counter==0){
                fb.addQuestionRating(sessionID,employeeName,fb.getSession().getQuestions().get(counter).getQuestionId(),textView.getText().toString());
                counter++;
                Toast.makeText(EmployeeActivity.this, "Valasz elkuldve", Toast.LENGTH_SHORT).show();
                textView.setText("");
                counter++;}
                else {
                    Toast.makeText(EmployeeActivity.this, "Mar Valszolt", Toast.LENGTH_SHORT).show();
                }

               /* if (counter<fb.getSession().getQuestions().size()){
                    questionText.setText(fb.getSession().getQuestions().get(counter).getQuestion());
                }
                else{

                    Toast.makeText(EmployeeActivity.this, "Nincs tobb kerdes", Toast.LENGTH_SHORT).show();

                }*/


            }
        });

        final Button leaveButton = findViewById(R.id.leaveButton);

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EmployeeActivity.this,JoinSessionActivity.class);
                startActivity(intent1);
            }
        });

    }


}