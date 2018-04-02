package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.viewmodel.BetViewModel;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import co.edu.uninorte.betit.viewmodel.UsersViewModel;
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

    private int finAway;
    private int finHome;

    private int points3 = 0;
    private int points1 = 0;
    private int points0 = 0;

    private int homeWins = 0;
    private int awayWins = 0;
    private int tieWins = 0;

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
    private UsersViewModel userModel;

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


        userModel = ViewModelProviders.of(this).get(UsersViewModel.class);
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
                                    textView(() -> {
                                        size(MATCH, WRAP);
                                        textSize(dip(24));
                                        gravity(CENTER_HORIZONTAL);
                                        text(match.getSimpleDate());
                                    });

                                    textView(() -> {
                                        size(MATCH, WRAP);
                                        textSize(dip(24));
                                        gravity(CENTER_HORIZONTAL);
                                        text(stadiums.get(match.getStadium() - 1));
                                    });
                                        if(!matches.get(match_id).isFinished()) {
                                            button(() -> {
                                                size(MATCH, WRAP);
                                                textSize(dip(24));
                                                gravity(CENTER_HORIZONTAL);
                                                if (!isAdmin) {
                                                    text("Submit bet");
                                                } else {
                                                    text("Submit result and close bets");
                                                }
                                                textAlignment(TEXT_ALIGNMENT_CENTER);
                                                onClick(v -> Submit());
                                            });
                                        }
                                    button(() -> {
                                        size(MATCH, WRAP);
                                        textSize(dip(24));
                                        gravity(CENTER_HORIZONTAL);
                                        text("Go back");
                                        textAlignment(TEXT_ALIGNMENT_CENTER);
                                        onClick(v -> finish());
                                    });
                        if(matches.get(match_id).isFinished()) {
                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("3 Points: " + points3);
                            });

                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("1 Point: " + points1);
                            });

                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("0 Points: " + points0);
                            });
                        }else{
                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("Bets that home team wins: "+homeWins);
                            });

                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("Bets that away team wins: "+awayWins);
                            });

                            textView(() -> {
                                size(MATCH, WRAP);
                                textSize(dip(14));
                                gravity(CENTER_HORIZONTAL);
                                text("Bets that the game ties: "+tieWins);
                            });
                        }

                    });
                }
            });
            if(matches.get(match_id).isFinished()){
                for (int j = 0; j < betNo; j++) {
                    if(matches.get(match_id).getHomeTeam() == data.get(j).getHomeTeam() && matches.get(match_id).getAwayTeam() == data.get(j).getAwayTeam()){
                        if(data.get(j).isBet()){
                            Log.v("TAG","El marcador de la apuesta es: " + data.get(j).getHomeResult() + "-" + data.get(j).getAwayResult()) ;
                            if(matches.get(match_id).getHomeResult() == data.get(j).getHomeResult() && matches.get(match_id).getAwayResult() == data.get(j).getAwayResult()){
                                points3++;
                                userModel.addThree(data.get(j).getUser());
                            }else{
                                int a = matches.get(match_id).getHomeResult();
                                int b = matches.get(match_id).getAwayResult();
                                int a2 = data.get(j).getHomeResult();
                                int b2 = data.get(j).getAwayResult();
                                int win = a-b;
                                int win2 = a2-b2;
                                if(win>0){
                                    if(win2>0){
                                        points1++;
                                        userModel.addOne(data.get(j).getUser());
                                    }else{
                                        points0++;
                                    }
                                }
                                if(win==0){
                                    if(win2==0){
                                        points1++;
                                        userModel.addOne(data.get(j).getUser());
                                    }else{
                                        points0++;
                                    }
                                }
                                if(win<0){
                                    if(win2<0){
                                        points1++;
                                        userModel.addOne(data.get(j).getUser());
                                    }else{
                                        points0++;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                for (int j = 0; j < betNo; j++) {
                    if (matches.get(match_id).getHomeTeam() == data.get(j).getHomeTeam() && matches.get(match_id).getAwayTeam() == data.get(j).getAwayTeam()) {
                        int a2 = data.get(j).getHomeResult();
                        int b2 = data.get(j).getAwayResult();
                        int win2 = a2-b2;
                        if(win2>0){
                            homeWins++;
                        }
                        if(win2==0){
                            awayWins++;
                        }
                        if(win2<0){
                            tieWins++;
                        }

                    }
                }
            }


        });
        if(matches.get(match_id).isFinished()){
            homeScore = matches.get(match_id).getHomeResult();
            awayScore = matches.get(match_id).getAwayResult();
        }
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
            addOrReplace(bet);

            finish();
            Toast.makeText(getApplicationContext(), "Submitted.", Toast.LENGTH_SHORT).show();}
            else{
                Toast.makeText(getApplicationContext(), "This match is finished, pick another one to bet on", Toast.LENGTH_SHORT).show();}
        }else{
            Toast.makeText(getApplicationContext(), "You need to log in to place a bet", Toast.LENGTH_SHORT).show();
        }
    }

    private void addOrReplace(Match bet) {
        for (Match currentbet: this.data){
            if (currentbet.getHomeTeam() == bet.getHomeTeam()){
                if (currentbet.getAwayTeam() == bet.getAwayTeam()){
                    if(bet.isFinished() && currentbet.isFinished()){
                        bet.setUid(currentbet.getUid());
                        break;
                    }
                }
            }
        }
        betmodel.addBet(bet);
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