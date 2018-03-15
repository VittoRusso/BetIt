package co.edu.uninorte.betit.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Visitante on 5/03/2018.
 */

public class Match  implements Serializable{

    int matchId;

    //This are declared final as they are not to change
    private final Team[] teams;
    private final String date;
    private final String location;

    //This will change eventually
    Boolean isOpen;
    Integer[] result;


    public Match(Team[] teams, String date, String location, int matchId) {
        this.teams = teams;
        this.date = date;
        this.location = location;
        this.matchId = matchId;

        //When created, every match will allow bets
        this.isOpen = true;
    }

    public Team[] getTeams() {
        return teams;
    }

    public String getDate() {
        return date;
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


}
