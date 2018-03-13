package co.edu.uninorte.betit.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Visitante on 5/03/2018.
 */

public class Match  implements Serializable{
    Team[] teams;
    Date date;
    Boolean isOpen;
    String location;
    Integer[] result;
    String test;

    int matchId;

    public Match(Team[] teams, Date date, Boolean isOpen, String location, Integer[] result, int matchId) {
        this.teams = teams;
        this.date = date;
        this.isOpen = isOpen;
        this.location = location;
        this.result = result;
        this.matchId = matchId;
    }
    public Match (String test){
        this.test = test;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer[] getResult() {
        return result;
    }

    public void setResult(Integer[] result) {
        this.result = result;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public static Match[] matchesDef = new Match[] {
        new Match("Hello"), new Match ("It me"), new Match("And i work")
    };
}
