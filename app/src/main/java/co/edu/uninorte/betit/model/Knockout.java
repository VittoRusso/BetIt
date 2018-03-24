
package co.edu.uninorte.betit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Knockout {

    @SerializedName("round_16")
    @Expose
    private Round16 round16;
    @SerializedName("round_8")
    @Expose
    private Round8 round8;
    @SerializedName("round_4")
    @Expose
    private Round4 round4;
    @SerializedName("round_2_loser")
    @Expose
    private Round2Loser round2Loser;
    @SerializedName("round_2")
    @Expose
    private Round2 round2;

    public Round16 getRound16() {
        return round16;
    }

    public void setRound16(Round16 round16) {
        this.round16 = round16;
    }

    public Round8 getRound8() {
        return round8;
    }

    public void setRound8(Round8 round8) {
        this.round8 = round8;
    }

    public Round4 getRound4() {
        return round4;
    }

    public void setRound4(Round4 round4) {
        this.round4 = round4;
    }

    public Round2Loser getRound2Loser() {
        return round2Loser;
    }

    public void setRound2Loser(Round2Loser round2Loser) {
        this.round2Loser = round2Loser;
    }

    public Round2 getRound2() {
        return round2;
    }

    public void setRound2(Round2 round2) {
        this.round2 = round2;
    }

}
