package co.edu.uninorte.betit.View;

import java.util.List;

import co.edu.uninorte.betit.Data.Match;
import co.edu.uninorte.betit.Data.Team;

/**
 * Created by Gabriel on 15/03/2018.
 */

public interface MatchViewInterface {

    void startMatchDetailActivity(co.edu.uninorte.betit.model.Match match);

    void setUpAdapterAndView(List<co.edu.uninorte.betit.model.Match> matches);

}
