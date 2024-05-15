package com.example.projectfinal;

public class Match {
    private String date;
    private String opponent;
    private String score;
    private String result;
    public Match(String date, String opponent, String score, String result) {
        this.date = date;
        this.opponent = opponent;
        this.score = score;
        this.result = result;
    }
    public String getDate() {
        return date;
    }public void setDate(String date) {
        this.date = date;
    }
    public String getOpponent() {
        return opponent;
    }public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getScore() {
        return score;
    }public void setScore(String score) {
        this.score = score;
    }
    public String getResult() {
        return result;
    }public void setResult(String result) {
        this.result = result;
    }
}
