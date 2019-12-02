package com.example.scrumpoker.Objects;

import java.util.ArrayList;

public class Employee {
    private String employeeName;
    private ArrayList<QuestionResult> questionResults;

    public Employee() {
    }

    public Employee(String employeName) {
        questionResults = new ArrayList<>();
        this.employeeName = employeName;
    }

    public String getEmployeName() {
        return employeeName;
    }

    public void setEmployeName(String employeName) {
        this.employeeName = employeName;
    }

    public ArrayList<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(QuestionResult questionResults) {
        this.questionResults.add(questionResults);
    }

    @Override
    public String toString() {
        return employeeName  + '\n'+ questionResults +'\n'+'\n'+'\n'+'\n'; ///at irva
    }
}
