package com.example.voxchoice.model;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class Poll {
    public String title;
    public String question;
    public List<String> options;
    public List<Integer> votes;

    public Poll(){
    }
    public Poll(String title, String question) {
        this.title = title;
        this.question = question;
    }
    public Poll(String title, String question, List<String> options){
        this.title = title;
        this.question = question;
        this.options = options;
        this.votes = votes;
    }
    public Poll(String title, String question, List<String> options, List<Integer> votes){
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

    public List<String> getOptions() {
        return options;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getVotes() {
        return votes;
    }
    public void setVotes(List<Integer> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return this.title;
//                "Poll{" +
//                "title='" + title + '\'' +
//                ", question='" + question + '\'' +
//                ", options=" + Arrays.toString(options) +
//                ", votes=" + Arrays.toString(votes) +
//                '}';
    }
}
