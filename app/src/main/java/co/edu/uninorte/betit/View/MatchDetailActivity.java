package co.edu.uninorte.betit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.R;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;


public class MatchDetailActivity extends AppCompatActivity {

    private static final String MATCH_ID = "MATCH_ID" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i = getIntent();
        int match_id = i.getIntExtra(MATCH_ID,1);

        setContentView(new RenderableView(this) {
            @Override
            public void view() {
                linearLayout(()->{
                   size(MATCH, MATCH);
                   padding(dip(32));
                   orientation(LinearLayout.VERTICAL);

                   textView(()-> {
                      size(WRAP, WRAP);
                      text(String.valueOf(match_id));
                      textSize(dip(40));
                   });

                });
            }
        });


    }

}
