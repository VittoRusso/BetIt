package co.edu.uninorte.betit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.uninorte.betit.databases.AppDatabase;
import co.edu.uninorte.betit.model.Match;

public class BetViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<Match>> data;

    public BetViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getINSTANCE(this.getApplication());

        data = appDatabase.betsDao().getAll();
    }

    public LiveData<List<Match>> getBets() {return data;}

    public void addBet(Match match){
        new AddItemTask().execute(match);
    }

    public void deleteBet(Match match){
        new DeleteItemTask().execute(match);
    }

    private class AddItemTask extends AsyncTask<Match, Void, Void> {
        @Override
        protected Void doInBackground(Match... item){
            appDatabase.betsDao().insert(item[0]);
            return null;
        }
    }

    private class DeleteItemTask extends AsyncTask<Match, Void, Void> {
        @Override
        protected Void doInBackground(Match... item){
            appDatabase.betsDao().delete(item[0]);
            return null;
        }
    }


}
