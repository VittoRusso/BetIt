package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.viewmodel.BetViewModel;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;


public class MatchDetailActivity extends AppCompatActivity {

    private static final String MATCH_ID = "MATCH_ID" ;
    private static final String MATCHES = "MATCHES" ;

    private JsonDataViewModel viewModel;

    private int match_id;
    private List<Match> matches;
    private List<String> teams;
    private List<String> stadiums;

    private String homeTeam;
    private String awayTeam;


    private int homeScore = 0;
    private int awayScore = 0;

    private static final String MATCH_ = "MATCH";
    private static final String TEAMS = "TEAMS";
    private static final String STADIUMS = "STADIUMS";

    private Match match;
    private int betNo;


    //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
    //Las variables
    private List<Match> data;
    private BetViewModel betmodel;
    //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i = getIntent();
        match_id = i.getIntExtra(MATCH_ID,1);
        matches = (List<Match>) i.getSerializableExtra(MATCHES);
        teams = (List<String>) i.getSerializableExtra(TEAMS);
        stadiums = (List<String>) i.getSerializableExtra(STADIUMS);

        match = (Match) i.getSerializableExtra(MATCH_);



        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
        //El observer
        betmodel = ViewModelProviders.of(this).get(BetViewModel.class);
        betmodel.getBets().observe(this, bets ->{
            data = bets;
            betNo = bets.size();
            // Lo que se te venga en ganas
            Log.d("BetDatabase","Increment");

        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
        //Para Agregar a la base de datos
        //betmodel.addBet(new Match());
        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
        //Para Borrar a la base de datos
        //betmodel.deleteBet( *SI ES UN SWIPE EN EL RECYCLEVIEW ENTONCES SERIA data.get(position)* );
        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
        //Para Editar a la base de datos
        //Es lo mismo que agregar nada mas coges el objeto y lo agregas con el mismo primary key
        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------




        this.homeTeam = teams.get(match.getHomeTeam()-1);
        this.awayTeam = teams.get(match.getAwayTeam()-1);


            setContentView(new RenderableView(this) {
                @Override
                public void view() {
                    linearLayout(()-> {
                        size(MATCH,MATCH);
                        padding(dip(8));
                        orientation(LinearLayout.VERTICAL);

                        //Teams and scores
                        linearLayout(()->{
                            size(MATCH,WRAP);
                            weightSum(1f);
                            orientation(LinearLayout.HORIZONTAL);

                            linearLayout(()->{
                                padding(dip(8));
                                size(0,MATCH);
                                weight(0.5f);
                                orientation(LinearLayout.VERTICAL);
                                gravity(LEFT);

                                textView(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(24));
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    text(homeTeam);
                                });

                                button(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(24));
                                    text("+");
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    onClick(v-> homeScore++);
                                });

                                textView(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(72));
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    text(""+homeScore);
                                    textColor(R.color.accent);
                                });
                                button(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(24));
                                    text("-");
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    onClick(v-> homeScore = (homeScore>0) ? homeScore-1 : homeScore);
                                });
                            });

                            linearLayout(()->{
                                padding(dip(8));
                                size(0,MATCH);
                                weight(0.5f);
                                orientation(LinearLayout.VERTICAL);
                                gravity(RIGHT);

                                textView(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(24));
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    text(awayTeam);
                                });

                                button(()->{
                                    size(MATCH,WRAP);
                                    text("+");
                                    textSize(dip(24));
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    onClick(v-> awayScore++);
                                });

                                textView(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(72));
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    text(""+awayScore);
                                    textColor(R.color.accent);
                                });
                                button(()->{
                                    size(MATCH,WRAP);
                                    textSize(dip(24));
                                    text("-");
                                    textAlignment(TEXT_ALIGNMENT_CENTER);
                                    onClick(v-> awayScore = (awayScore> 0)? awayScore-1 : awayScore);
                                });
                            });

                        });

                        //Location, date

                        button(()->{
                            size(MATCH,WRAP);
                            textSize(dip(24));
                            gravity(CENTER_HORIZONTAL);
                            text("Submit");
                            textAlignment(TEXT_ALIGNMENT_CENTER);
                            onClick(v-> Submit());
                        });

                        textView(()-> {
                            size(MATCH,WRAP);
                            textSize(dip(24));
                            gravity(CENTER_HORIZONTAL);
                            text("Number of bets: " + betNo);
                        });
                    });
                }
            });
        });
    }

    private void Submit() {
        Match current_match = matches.get(match_id);

        Match bet = new Match();
        bet.setHomeTeam(current_match.getHomeTeam()-1);
        bet.setAwayTeam(current_match.getAwayTeam()-1);
        bet.setHomeResult(homeScore);
        bet.setAwayResult(awayScore);
        bet.setUser("1");
        bet.setDate(current_match.getDate());
        bet.setStadium(current_match.getStadium());//idk
        bet.setBet(true);
        bet.setFinished(true);
        betmodel.addBet(bet);
        finish();
        Toast.makeText(getApplicationContext(), "Bet placed.",Toast.LENGTH_SHORT).show();
    }

}
