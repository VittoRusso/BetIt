package co.edu.uninorte.betit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import co.edu.uninorte.betit.View.ViewPagerActivity;
import co.edu.uninorte.betit.model.A;
import co.edu.uninorte.betit.model.Groups;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import trikita.anvil.BaseDSL;
import trikita.anvil.DSL;
import trikita.anvil.DSL.*;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;

import java.lang.reflect.*;

public class MainActivity extends AppCompatActivity {

    private JsonDataViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RenderableView(this) {
            @Override
            public void view() {
                DSL.linearLayout(()->{
                    DSL.size(BaseDSL.MATCH,BaseDSL.MATCH);
                    DSL.padding(DSL.dip(8));
                    DSL.orientation(LinearLayout.VERTICAL);

                    textView(()->{
                       size(MATCH,WRAP);
                       text("Welcome");
                       textSize(dip(30));
                       gravity(CENTER);
                    });

                    button(()->{
                        size(MATCH,WRAP);
                        text("Take me away!");
                        onClick(v -> gotoViewPager());
                    });
                });
            }
        });
        //setContentView(R.layout.activity_main);
        //Le doy follow y like al liveData
        model = ViewModelProviders.of(this).get(JsonDataViewModel.class);

        model.getLiveData().observe(this, liveData -> {
            //De ejemplo imprimo en la consola el tamaño de los equipos que son 32.
            //En la variable liveData esta toda la informacion del JSON. Como ejemplo getTeam, getTVChannel, getKnockout, getStadium etc.
            Log.d("Json: ","Observer in action"+liveData.getTeams().size());
            Log.d("Json: ","Number of matches in A:"+liveData.getGroups().getA().getMatches().size());
            Log.d("Json: ","Number of matches in B:"+liveData.getGroups().getB().getMatches().size());
            Log.d("Json: ","Number of matches in C:"+liveData.getGroups().getC().getMatches().size());
            Log.d("Json: ","Number of matches in D:"+liveData.getGroups().getD().getMatches().size());
            Log.d("Json: ","Number of matches in E:"+liveData.getGroups().getE().getMatches().size());
            Log.d("Json: ","Number of matches in F:"+liveData.getGroups().getF().getMatches().size());
            Log.d("Json: ","Number of matches in G:"+liveData.getGroups().getG().getMatches().size());
            Log.d("Json: ","Number of matches in H:"+liveData.getGroups().getH().getMatches().size());



        });



    }



    private void gotoViewPager() {
        Intent i = new Intent(this, ViewPagerActivity.class);
        startActivity(i);
    }


    public void Test(View view) {
    }
}
