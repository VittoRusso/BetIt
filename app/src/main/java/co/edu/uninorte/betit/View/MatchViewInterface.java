package co.edu.uninorte.betit.View;

import java.util.List;

/**
 * Created by Gabriel on 15/03/2018.
 */

public interface MatchViewInterface {

    void startMatchDetailActivity(int index);

    void setUpAdapterAndView(List<co.edu.uninorte.betit.model.Match> matches);

}
