
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class D {

    @SerializedName("matches")
    @Expose
    private List<Match___> matches = null;

    public List<Match___> getMatches() {
        return matches;
    }

    public void setMatches(List<Match___> matches) {
        this.matches = matches;
    }

}
