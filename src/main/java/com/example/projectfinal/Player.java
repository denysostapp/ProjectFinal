package com.example.projectfinal;

public class Player {
    private String namePlayer;
    private int age;
    private String position;
    private double price;
    private Team team;
    public Player(String namePlayer, int age, String position, double price, Team team) {
        this.namePlayer = namePlayer;
        this.age = age;
        this.position = position;
        this.price = price;
        this.team = team;
    }
    public String getName() {
        return namePlayer;
    }
    public int getAge() {
        return age;
    }
    public String getPosition() {
        return position;
    }
    public double getPrice() {
        return price;
    }
    public Team getTeam() {
        return team;
    }public void setTeam(Team team) {
        this.team = team;
    }

    public String toString(){
        return String.format("%s, Age: %d, Price: €%.2fM", namePlayer, age, price);
    }

}
