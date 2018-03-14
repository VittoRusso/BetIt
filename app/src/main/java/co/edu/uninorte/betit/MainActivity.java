package co.edu.uninorte.betit;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;

public class MainActivity extends AppCompatActivity {

    private JsonDataViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Le doy follow y like al liveData
        model = ViewModelProviders.of(this).get(JsonDataViewModel.class);

        model.getLiveData().observe(this, liveData -> {
            //De ejemplo imprimo en la consola el tamaño de los equipos que son 32.
            //En la variable liveData esta toda la informacion del JSON. Como ejemplo getTeam, getTVChannel, getKnockout, getStadium etc.
            Log.d("Json: ","Observer in action"+liveData.getTeams().size());
        });

    }


    public void Test(View view) {
    }
}
