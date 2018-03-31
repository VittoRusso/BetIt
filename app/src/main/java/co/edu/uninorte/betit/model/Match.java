
package co.edu.uninorte.betit.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Match implements Comparable<Match>, Serializable{

    @PrimaryKey(autoGenerate = true)
    private  int uid;

    @SerializedName("name")
    @Expose
    private int name;

    @SerializedName("user")
    @Expose
    @ColumnInfo(name = "user")
    private String user;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("home_team")
    @Expose
    @ColumnInfo(name = "homeTeam")
    private int homeTeam;

    @SerializedName("away_team")
    @Expose
    @ColumnInfo(name = "awayTeam")
    private int awayTeam;

    @SerializedName("home_result")
    @Expose
    @ColumnInfo(name = "homeResult")
    private int homeResult;

    @SerializedName("away_result")
    @Expose
    @ColumnInfo(name = "awayResult")
    private int awayResult;

    @SerializedName("date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("stadium")
    @Expose
    @ColumnInfo(name = "stadium")
    private int stadium;

    @SerializedName("channels")
    @Expose
    @Ignore
    private List<Object> channels = null;

    @SerializedName("finished")
    @Expose
    @ColumnInfo(name = "finished")
    private boolean finished;

    @SerializedName("isBet")
    @Expose
    @ColumnInfo(name = "isbet")
    private boolean isBet;


    public boolean isBet() {
        return isBet;
    }

    public void setBet(boolean bet) {
        isBet = bet;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public int getUid() { return uid; }

    public void setUid(int uid) { this.uid = uid; }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(int homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(int awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(int homeResult) {
        this.homeResult = homeResult;
    }

    public int getAwayResult() { return awayResult; }

    public void setAwayResult(int awayResult) {
        this.awayResult = awayResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStadium() {
        return stadium;
    }

    public void setStadium(int stadium) {
        this.stadium = stadium;
    }

    public List<Object> getChannels() {
        return channels;
    }

    public void setChannels(List<Object> channels) {
        this.channels = channels;
    }

    public boolean isFinished() { return finished; }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public int compareTo(@NonNull Match match) {
        return date2Str().compareTo(match.date2Str());
    }

    private Date date2Str() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date true_date = format.parse(this.date);
            return true_date;
        } catch (ParseException e){
            e.printStackTrace();
            Date true_date = null;
            return true_date;
        }
    }

    public String getSimpleDate() {
        Date true_date = date2Str();
        String simpledate = new SimpleDateFormat("dd/MM/yyyy").format(true_date);
        return simpledate;
    }
}
