package co.edu.uninorte.betit.Logic;

import co.edu.uninorte.betit.Data.Match;
import co.edu.uninorte.betit.Data.MatchInterface;
import co.edu.uninorte.betit.View.MatchViewInterface;

/**
 * Created by Gabriel on 15/03/2018.
 */

public class MatchController {

    private MatchViewInterface matchView;
    private MatchInterface matchDataSource;

    public MatchController(MatchViewInterface matchViewInterface, MatchInterface matchInterface) {
        this.matchView = matchViewInterface;
        this.matchDataSource = matchInterface;
        getListFromMatchSource();
    }

    public void getListFromMatchSource() {
        matchView.setUpAdapterAndView(
                matchDataSource.getListOfMatches()
        );
    }

    public void onListItemClick(Match testMatch){
        matchView.startMatchDetailActivity(testMatch.getTeams(), testMatch.getDate(),testMatch.getLocation());
    }
}
