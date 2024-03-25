package com.example.voxchoice.model;
public class Poll {
    public String title;
    public String question;
    public String[] options;
    public int[] votes;

    public Poll(){
    }
    public Poll(String title, String question) {
        this.title = title;
        this.question = question;
    }
    public Poll(String title, String question, String[] options, int[] votes){
        this.title = title;
        this.question = question;
        this.options = options;
        this.votes = votes;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }
    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getVotes() {
        return votes;
    }
    public void setVotes(int[] votes) {
        this.votes = votes;
    }
}
