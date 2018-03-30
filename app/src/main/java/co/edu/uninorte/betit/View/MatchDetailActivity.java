package co.edu.uninorte.betit.View;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;


public class MatchDetailActivity extends AppCompatActivity {

    private static final String MATCH_ID = "MATCH_ID" ;
    private static final String MATCHES = "MATCHES" ;

    private JsonDataViewModel viewModel;


    private int match_id;
    private List<Match> matches;
    private String homeTeam;
    private String awayTeam;

    private RenderableView view;

    private int homeScore = 0;
    private int awayScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i = getIntent();
        match_id = i.getIntExtra(MATCH_ID,1);
        matches = (List<Match>) i.getSerializableExtra(MATCHES);


        viewModel = ViewModelProviders.of(this).get(JsonDataViewModel.class);
        viewModel.getLiveData().observe(this, liveData -> {

            this.homeTeam = liveData.getTeams().get(matches.get(match_id-1).getHomeTeam()-1).getName();
            this.awayTeam = liveData.getTeams().get(matches.get(match_id-1).getAwayTeam()-1).getName();

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
                                    textAlignment(TEXT_ALIGNMENT_TEXT_END);
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
                                    onClick(v-> homeScore--);
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
                                    textAlignment(TEXT_ALIGNMENT_TEXT_START);
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
                                    onClick(v-> awayScore--);
                                });
                            });

                        });

                        //Location, date

                        //Button to place bet and to go back


                    });
                }
            });


        });
    }
}
