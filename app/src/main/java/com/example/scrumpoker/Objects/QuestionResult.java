package com.example.scrumpoker.Objects;

public class QuestionResult {
    private String questionId;
    private String questionRate;

    public QuestionResult() {
    }

    public QuestionResult(String questionId, String questionRate) {
        this.questionId = questionId;
        this.questionRate = questionRate;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionRate() {
        return questionRate;
    }

    public void setQuestionRate(String questionRate) {
        this.questionRate = questionRate;
    }

    @Override
    public String toString() {
        return " The : " +
                 questionId + "question you rate is " + questionRate + '\n';
    }
}
