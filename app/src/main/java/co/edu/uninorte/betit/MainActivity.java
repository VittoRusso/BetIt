package co.edu.uninorte.betit;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0f);

        content =findViewById(R.id.basic_content);
        tabLayout = findViewById(R.id.tbl_basic);

        //creating tabs:
        tabLayout.addTab(
                tabLayout.newTab().setText(R.string.matches)
        );
        tabLayout.addTab(
                tabLayout.newTab().setText(R.string.bets)
        );
        tabLayout.addTab(
                tabLayout.newTab().setText(R.string.profile)
        );

        tabLayout.setTabTextColors(ContextCompat.getColor(this,android.R.color.white),
                ContextCompat.getColor(this,R.color.accent));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.primary));


        tabLayout.addOnTabSelectedListener(this);
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        content.setText(tab.getText());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
