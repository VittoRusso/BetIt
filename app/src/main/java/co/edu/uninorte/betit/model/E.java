
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class E {

    @SerializedName("matches")
    @Expose
    private List<Match____> matches = null;

    public List<Match____> getMatches() {
        return matches;
    }

    public void setMatches(List<Match____> matches) {
        this.matches = matches;
    }

}
