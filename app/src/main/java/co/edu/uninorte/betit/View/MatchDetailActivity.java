package co.edu.uninorte.betit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.R;

public class MatchDetailActivity extends AppCompatActivity {

    private static final String EXTRA_TEAMS = "EXTRA_TEAMS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    private TextView team1text;
    private TextView team2text;
    private TextView location;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        Intent i = getIntent();
        String date = i.getStringExtra(EXTRA_DATE);
        String location = i.getStringExtra(EXTRA_LOCATION);
        Team[] teams = (Team[]) i.getSerializableExtra(EXTRA_TEAMS);

        TextView dateText = findViewById(R.id.date_text);
        dateText.setText(date);

        TextView locationText = findViewById(R.id.location_text);
        locationText.setText(location);

        TextView team1text = findViewById(R.id.team1_text);
        team1text.setText(teams[0].toString());

        TextView team2text = findViewById(R.id.team2_text);
        team2text.setText(teams[1].toString());



    }

}
