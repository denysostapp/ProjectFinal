package com.example.projectfinal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Team {
    private String name;
    private String logoPath;
    private String stadium;
    private String coach;
  //  private int gamesPlayed


    public Team(String name, String logoPath, String stadium, String coach) {
        this.name = name;
        this.logoPath = logoPath;
        this.stadium = stadium;
        this.coach = coach;
    }

    public String getName() {
        return name;
    }
    public String getLogoPath() {
        return logoPath;
    }
    public String getStadium() {
        return stadium;
    }
    public String getCoach() {
        return coach;
    }
    @Override
    public String toString() {
        return name;
    }

    //Created One Player class to be able to add it to ArrayList<Person>. Info about one player

    private ObservableList<Match> matches = FXCollections.observableArrayList();
    public void addMatch(Match match) {
        matches.add(match);
    }
    public ObservableList<Match> getMatches() {
        return matches;
    }
}
