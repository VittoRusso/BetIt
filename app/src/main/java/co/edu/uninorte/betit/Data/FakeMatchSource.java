package co.edu.uninorte.betit.Data;

import java.util.List;

/**
 * Created by Gabriel on 15/03/2018.
 */

public class FakeMatchSource implements MatchInterface {

    private static final int sizeOfCollection = 12;

    private final  String[] locations  = {
      "Cartagena","Magangue","Chibolo","El Dificil"

    };


    @Override
    public List<Match> getListOfMatches() {
        return null;
    }
}
