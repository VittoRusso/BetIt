
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B {

    @SerializedName("matches")
    @Expose
    private List<Match_> matches = null;

    public List<Match_> getMatches() {
        return matches;
    }

    public void setMatches(List<Match_> matches) {
        this.matches = matches;
    }

}
