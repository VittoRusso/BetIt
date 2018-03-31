package co.edu.uninorte.betit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Match_ {


    @SerializedName("name")
    @Expose
    private Integer name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("home_team")
    @Expose
    private String homeTeam;
    @SerializedName("away_team")
    @Expose
    private String awayTeam;
    @SerializedName("home_result")
    @Expose
    private Object homeResult;
    @SerializedName("away_result")
    @Expose
    private Object awayResult;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stadium")
    @Expose
    private Integer stadium;
    @SerializedName("channels")
    @Expose
    private List<Object> channels = null;
    @SerializedName("finished")
    @Expose
    private Boolean finished;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Object getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(Object homeResult) {
        this.homeResult = homeResult;
    }

    public Object getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(Object awayResult) {
        this.awayResult = awayResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStadium() {
        return stadium;
    }

    public void setStadium(Integer stadium) {
        this.stadium = stadium;
    }

    public List<Object> getChannels() {
        return channels;
    }

    public void setChannels(List<Object> channels) {
        this.channels = channels;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

}
