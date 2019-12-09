package com.example.scrumpoker.Objects;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseRealtimeDatabaseHelper {
    private DatabaseReference mDatabaseReference;
    private Query query;
    private Session session;


    public FirebaseRealtimeDatabaseHelper(String sessionId) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        session = new Session();
        getDatabaseSessionData(sessionId);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void getDatabaseSessionData(String sessionID){ /// az adatbazishoz valo hoza feres le egyszerusitese fugvenyekre
        query = mDatabaseReference.child(sessionID);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                Log.i("FBDB","onChildAdded "+dataSnapshot.getKey());
                if (key.equals("ownerName"))
                {
                    session.setOwnerName(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/ownerName: "+dataSnapshot.getValue());
                }
                if (key.equals("activ"))
                {
                    session.setActiv(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/activ: "+dataSnapshot.getValue());
                }
                if (key.equals("aktivemployees"))
                {
                    session.setActiv(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/aktivemployees: "+dataSnapshot.getValue());
                }
                if (key.equals("numberemployees"))
                {
                    session.setActiv(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/numberemployees: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionId"))
                {
                    session.setSessionId(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/sessionId: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionName"))
                {
                    session.setSessionName(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/sessionName: "+dataSnapshot.getValue());
                }
                if (key.equals("Questions"))
                {
                    for (DataSnapshot child: dataSnapshot.getChildren())
                    {
                        session.setQuestions(new Question(child.child("questionId").getValue().toString(),child.child("questionDescription").getValue().toString(),child.child("question").getValue().toString()));
                        Log.i("FBDB","onChildAdded/Questions/QID/question: "+child.child("question").getValue().toString());
                        Log.i("FBDB","onChildAdded/Questions/QID/questionDescription: "+child.child("questionDescription").getValue().toString());
                        Log.i("FBDB","onChildAdded/Questions/QID/questionId: "+child.child("questionId").getValue().toString());
                    }
                }
                if (key.equals("Employees"))
                {
                    for (DataSnapshot child: dataSnapshot.getChildren())
                    {
                        session.setEmployees(new Employee(child.child("employeeName").getValue().toString()));
                        Log.i("FBDB","onChildAdded/Employee/ENAME/employeeName: "+child.child("employeeName").getValue().toString());
                        int x =-1;
                        for (int i=0;i<session.getEmployees().size();i++)
                        {
                            if (session.getEmployees().get(i).getEmployeName().equals(child.child("employeeName").getValue().toString()))
                            {
                                x = i;
                                break;
                            }
                        }
                        for (DataSnapshot child1: child.getChildren())
                        {
                            if (!child1.getKey().equals("employeeName")) {
                                Log.i("FBDB", "onChildAdded/Employee/ENAME/questionId: " + child1.getKey().toString());
                                session.getEmployees().get(x).setQuestionResults(new QuestionResult(child1.getKey().toString(), child1.getValue().toString()));
                            }
                        }

                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String key = dataSnapshot.getKey();
                Log.i("FBDB","onChildAdded "+dataSnapshot.getKey());
                if (key.equals("ownerName"))
                {
                    session.setOwnerName(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/ownerName: "+dataSnapshot.getValue());
                }
                if (key.equals("activ"))
                {
                    session.setActiv(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/activ: "+dataSnapshot.getValue());
                }
                if (key.equals("numberemployees"))
                {
                    session.setnumberemployees(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/numberemployees: "+dataSnapshot.getValue());
                }
                if (key.equals("aktivemployees"))
                {
                    session.setaktivemployees(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/aktivemployees: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionId"))
                {
                    session.setSessionId(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/sessionId: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionName"))
                {
                    session.setSessionName(dataSnapshot.getValue().toString());
                    Log.i("FBDB","onChildAdded/sessionName: "+dataSnapshot.getValue());
                }
                if (key.equals("Questions"))
                {
                    session.getQuestions().clear();
                    for (DataSnapshot child: dataSnapshot.getChildren())
                    {
                        session.setQuestions(new Question(child.child("questionId").getValue().toString(),child.child("questionDescription").getValue().toString(),child.child("question").getValue().toString()));
                        Log.i("FBDB","onChildAdded/Questions/QID/question: "+child.child("question").getValue().toString());
                        Log.i("FBDB","onChildAdded/Questions/QID/questionDescription: "+child.child("questionDescription").getValue().toString());
                        Log.i("FBDB","onChildAdded/Questions/QID/questionId: "+child.child("questionId").getValue().toString());
                    }
                }
                if (key.equals("Employees"))
                {
                    session.getEmployees().clear();
                    for (DataSnapshot child: dataSnapshot.getChildren())
                    {
                        session.setEmployees(new Employee(child.child("employeeName").getValue().toString()));
                        Log.i("FBDB","onChildAdded/Employee/ENAME/employeeName: "+child.child("employeeName").getValue().toString());
                        int x =-1;
                        for (int i=0;i<session.getEmployees().size();i++)
                        {
                            if (session.getEmployees().get(i).getEmployeName().equals(child.child("employeeName").getValue().toString()))
                            {
                                x = i;
                                break;
                            }
                        }
                        for (DataSnapshot child1: child.getChildren())
                        {
                            if (!child1.getKey().equals("employeeName")) {
                                Log.i("FBDB", "onChildAdded/Employee/ENAME/questionId: " + child1.getKey().toString());
                                session.getEmployees().get(x).setQuestionResults(new QuestionResult(child1.getKey().toString(), child1.getValue().toString()));
                            }
                        }

                    }
                }
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
    }

    public void addQuestion(String sessionId, Question question)
    {
        mDatabaseReference.child(sessionId).child("Questions").child(question.getQuestionId()).setValue(question);
    }

    public void addQuestionRating(String sessionId, String employeeName, String questionId, String questionRate)
    {
        mDatabaseReference.child(sessionId).child("Employees").child(employeeName).child(questionId).setValue(questionRate);
    }
}
