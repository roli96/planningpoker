package com.example.scrumpoker.Objects;

import java.util.ArrayList;

public class Session {
    private String ownerName;
    private String activ;
    private String numberemployees,aktivemployees;
    private String sessionName;
    private String sessionId;
    private ArrayList<Question> questions;
    private ArrayList<Employee> employees;

    public Session() {
        questions = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getActiv() {
        return activ;
    }

    public String getnumberemployees() {
        return numberemployees;
    }

    public String getaktivemployees() {
        return aktivemployees;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setActiv(String activ) {
        this.activ = activ;
    }

    public void setnumberemployees(String activ) {
        this.numberemployees = numberemployees;
    }

    public void setaktivemployees(String activ) {
        this.aktivemployees = aktivemployees;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Question question) {
        this.questions.add(question);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employee) {
        this.employees.add(employee);
    }

    public void setEmployeeQuestions(int i,String key) {
        this.employees.get(i).setQuestionResults(new QuestionResult(key,key));
    }

    @Override
    public String toString() {
        return "Session{" +
                "ownerName='" + ownerName + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", activ='" + activ + '\'' +
                ", questions=" + questions +
                ", employees=" + employees +
                '}';
    }
}
