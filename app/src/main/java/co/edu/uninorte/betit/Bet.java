package co.edu.uninorte.betit;

/**
 * Created by Visitante on 5/03/2018.
 */

public class Bet {
    Integer[] guess;
    int matchId;
    int userId;
    int points;

    public Bet(Integer[] guess, int matchId, int userId) {
        this.guess = guess;
        this.matchId = matchId;
        this.userId = userId;
    }

    public Integer[] getGuess() {
        return guess;
    }

    public void setGuess(Integer[] guess) {
        this.guess = guess;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
