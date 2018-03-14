
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("name")
    @Expose
    private int name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("home_team")
    @Expose
    private int homeTeam;
    @SerializedName("away_team")
    @Expose
    private int awayTeam;
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
    private int stadium;
    @SerializedName("channels")
    @Expose
    private List<Object> channels = null;
    @SerializedName("finished")
    @Expose
    private boolean finished;

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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
