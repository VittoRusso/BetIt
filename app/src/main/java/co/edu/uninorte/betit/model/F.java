
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class F {

    @SerializedName("matches")
    @Expose
    private List<Match_____> matches = null;

    public List<Match_____> getMatches() {
        return matches;
    }

    public void setMatches(List<Match_____> matches) {
        this.matches = matches;
    }

}
