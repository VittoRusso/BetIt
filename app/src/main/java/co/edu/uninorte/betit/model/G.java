
package co.edu.uninorte.betit.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class G {

    @SerializedName("matches")
    @Expose
    private List<Match______> matches = null;

    public List<Match______> getMatches() {
        return matches;
    }

    public void setMatches(List<Match______> matches) {
        this.matches = matches;
    }

}
