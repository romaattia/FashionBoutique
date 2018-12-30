package com.example.romisaa.fashionboutique.data.model;

public class FeedbackModel {
    String feedback;
    String number;

    public FeedbackModel() {
    }

    public FeedbackModel(String feedback, String number) {
        this.feedback = feedback;
        this.number = number;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
