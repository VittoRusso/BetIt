package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
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

    public ArrayList<Integer> finished_matches;


    private boolean isAdmin = false;
    private boolean isLogged = false;
    private String email;

    private List<Match> data;
    private BetViewModel betmodel;


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;


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
        this.homeTeam = teams.get(match.getHomeTeam()-1);
        this.awayTeam = teams.get(match.getAwayTeam()-1);

        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(context.getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        isLogged = sharedPref.getBoolean(context.getString(R.string.isLoginKey),false);
        email = sharedPref.getString(context.getString(R.string.emailKey),"");
        if (email.equals("admin")){
            this.isAdmin = true;
        }
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
                    textView(()-> {
                        size(MATCH,WRAP);
                        textSize(dip(24));
                        gravity(CENTER_HORIZONTAL);
                        text( match.getSimpleDate());
                    });

                    textView(()-> {
                        size(MATCH,WRAP);
                        textSize(dip(24));
                        gravity(CENTER_HORIZONTAL);
                        text(stadiums.get(match.getStadium()-1));
                    });
                    button(()->{
                        size(MATCH,WRAP);
                        textSize(dip(24));
                        gravity(CENTER_HORIZONTAL);
                        if (!isAdmin){
                        text("Submit bet");}
                        else{text("Submit result and close bets");}
                        textAlignment(TEXT_ALIGNMENT_CENTER);
                        onClick(v-> Submit());
                    });
                    button(()->{
                        size(MATCH,WRAP);
                        textSize(dip(24));
                        gravity(CENTER_HORIZONTAL);
                        text("Go back");
                        textAlignment(TEXT_ALIGNMENT_CENTER);
                        onClick(v-> finish());
                    });




                });
            }
        });
        });
    }

    private void Submit() {

        if (isLogged) {
            Match current_match = matches.get(match_id);
            if (isAdmin || !current_match.isFinished()){
            Match bet = new Match();
            bet.setHomeTeam(current_match.getHomeTeam());
            bet.setAwayTeam(current_match.getAwayTeam());
            bet.setHomeResult(homeScore);
            bet.setAwayResult(awayScore);
            bet.setUser(email);
            bet.setDate(current_match.getDate());
            bet.setStadium(current_match.getStadium());//idk
            bet.setBet(!this.isAdmin);
            bet.setFinished(this.isAdmin);
            betmodel.addBet(bet);

            finish();
            Toast.makeText(getApplicationContext(), "Submitted.", Toast.LENGTH_SHORT).show();}
            else{
                Toast.makeText(getApplicationContext(), "This match is finished, pick another one to bet on", Toast.LENGTH_SHORT).show();}
        }else{
            Toast.makeText(getApplicationContext(), "You need to log in to place a bet", Toast.LENGTH_SHORT).show();
        }
    }
}

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