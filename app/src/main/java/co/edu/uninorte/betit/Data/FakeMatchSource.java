package co.edu.uninorte.betit.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gabriel on 15/03/2018.
 */

public class FakeMatchSource implements MatchInterface {

    private static final int sizeOfCollection = 12;

    private final  String[] locations  = {
      "Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"
    };

    private final String[] dates = {
          "15/03/2018","16/03/2018","17/03/2018","18/03/2018"
    };

    private final Team[][] teams= {
        {Team.Colombia, Team.Argentina},
        {Team.Russia, Team.Brazil},
        {Team.Uruguay,Team.CostaRica},
        {Team.Belgium,Team.Egypt},
    };

    public FakeMatchSource(){}

    @Override
    public List<Match> getListOfMatches() {
        ArrayList<Match> matches = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < sizeOfCollection; i++) {

            int rand1 = random.nextInt(4);
            int rand2 = random.nextInt(4);
            int rand3 = random.nextInt(4);
            int rand4 = random.nextInt(1000);

            Match match = new Match(
                    teams[rand1],
                    dates[rand2],
                    locations[rand3],
                    rand4
            );
            matches.add(match);

        }
        return matches;
    }
}
