
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class H {

    @SerializedName("matches")
    @Expose
    private List<Match_______> matches = null;

    public List<Match_______> getMatches() {
        return matches;
    }

    public void setMatches(List<Match_______> matches) {
        this.matches = matches;
    }

}
