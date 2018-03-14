
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class C {

    @SerializedName("matches")
    @Expose
    private List<Match__> matches = null;

    public List<Match__> getMatches() {
        return matches;
    }

    public void setMatches(List<Match__> matches) {
        this.matches = matches;
    }

}
