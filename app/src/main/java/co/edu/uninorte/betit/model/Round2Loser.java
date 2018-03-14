
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Round2Loser {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("matches")
    @Expose
    private List<Match___________> matches = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match___________> getMatches() {
        return matches;
    }

    public void setMatches(List<Match___________> matches) {
        this.matches = matches;
    }

}
