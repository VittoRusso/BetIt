package co.edu.uninorte.betit.View;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.JsonData;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.viewmodel.BetViewModel;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;


public class MatchDetailActivity extends AppCompatActivity {

    private static final String MATCH_ID = "MATCH_ID" ;
    private static final String MATCHES = "MATCHES" ;

    private JsonDataViewModel viewModel;
    private JsonData dataJson;

    private int match_id;
    private List<Match> matches;
    private String homeTeam;
    private String awayTeam;

    private RenderableView view;

    private int homeScore = 0;
    private int awayScore = 0;


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


        //------------------- CODIGO EJEMPLO PARA EL ROOMDATABASE DE LOS BETS ------------------------
        //El observer
        betmodel = ViewModelProviders.of(this).get(BetViewModel.class);
        betmodel.getBets().observe(this, bets ->{
            data = bets;
            // Lo que se te venga en ganas
            Log.d("BetDatabase","Increment");
        });
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

        viewModel = ViewModelProviders.of(this).get(JsonDataViewModel.class);
        viewModel.getLiveData().observe(this, liveData -> {

            dataJson = liveData;
            this.homeTeam = liveData.getTeams().get(matches.get(match_id).getHomeTeam()-1).getName();
            this.awayTeam = liveData.getTeams().get(matches.get(match_id).getAwayTeam()-1).getName();


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
                                    onClick(v-> homeScore = Decrease(homeScore));
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
                                    onClick(v-> awayScore = Decrease(awayScore));
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
                            text("Count of bets: "+ betmodel.getBets().getValue().size());
                        });


                    });
                }
            });


        });
    }

    private void Submit() {
        Match bet = new Match();
        bet.setHomeTeam(matches.get(match_id-1).getHomeTeam()-1);
        bet.setAwayTeam(matches.get(match_id-1).getAwayTeam()-1);
        bet.setHomeResult(homeScore);
        bet.setAwayResult(awayScore);
        bet.setUser("AquiVaElUser");
        bet.setDate("idk");//idk
        bet.setStadium(1);//idk
        bet.setBet(true);
        bet.setFinished(true);
        betmodel.addBet(bet);
        finish();
    }

    public int Decrease (int num){
        if (num>0){
            num--;
        }
        return num;
    }
}
