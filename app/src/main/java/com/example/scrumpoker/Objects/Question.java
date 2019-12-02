package com.example.scrumpoker.Objects;

public class Question{
    private String questionId,questionDescription,question;

    public Question() {
    }

    public Question(String questionId, String questionDescription, String question) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.question = question;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return ///"Question{" +
                ///"questionId='"
                 "  Nr."+
                questionId + " Question: " +
                //", questionDescription='" + questionDescription + '\'' +
                //", question='" +
                         question + '\n';//+ '\'' +
               // '}';
    }
}
