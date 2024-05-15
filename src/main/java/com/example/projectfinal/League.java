package com.example.projectfinal;

import java.util.ArrayList;
import java.util.List;

public class League {
    private String name;
    private List<Team> teams;

    public League(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
    @Override
    public String toString() {
        return name;
    }
}
