package co.edu.uninorte.betit.Data;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


public class Match  implements Serializable, Comparable<Match> {

    int matchId;

    //This are declared final as they are not to change
    private final Team[] teams;
    private final String date;
    private final String location;

    //This will change eventually
    Boolean isOpen;
    Integer[] result;

    //This is to sort matches by date
    public final Date true_date;


    public Match(Team[] teams, String date, String location, int matchId) {
        this.teams = teams;
        this.date = date;
        this.location = location;
        this.matchId = matchId;

        //When created, every match will allow bets
        this.isOpen = true;
        this.true_date = str2date(date);
    }


    private Date str2date(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date true_date = format.parse(date);
            return true_date;
        }
        catch (ParseException e){
            e.printStackTrace();
            Date true_date = null;
            return true_date;
        }
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

    public Date getTrue_date() {
        return true_date;
    }

    @Override
    public int compareTo(@NonNull Match match) {
        return getTrue_date().compareTo(match.getTrue_date());
    }
}
