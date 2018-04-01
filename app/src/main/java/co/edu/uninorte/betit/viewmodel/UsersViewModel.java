package co.edu.uninorte.betit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import javax.xml.transform.Result;

import co.edu.uninorte.betit.databases.AppDatabase;
import co.edu.uninorte.betit.model.User;

public class UsersViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<User>> data;

    public UsersViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getINSTANCE(this.getApplication());

        data = appDatabase.userDao().getAllUsers();
    }

    public LiveData<List<User>> getUsers() {
        return data;
    }

    public void addUser (User user){
        new AddItemTask().execute(user);
    }

    private class AddItemTask extends AsyncTask <User, Void, Void>{
        @Override
        protected Void doInBackground(User... item){
            appDatabase.userDao().insertUser(item[0]);
            return null;
        }
    }

    public void deleteUser(User user){
        new DeleteItemTask().execute(user);
    }

    private class DeleteItemTask extends AsyncTask <User, Void, Void>{
        @Override
        protected Void doInBackground(User... item){
            appDatabase.userDao().deleteUser(item[0]);
            return null;
        }
    }

    public Integer checkUser(String email, String pass){
        try{
            Integer count = new CheckUserTask().execute(email,pass).get();
            return count;
        }catch (Exception e){
            return 99;
        }
    }

    private class CheckUserTask extends AsyncTask <String, Void, Integer>{
        @Override
        protected Integer doInBackground(String... item){
            return appDatabase.userDao().checkUser(item[0],item[1]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
        }
    }

    public Integer checkRegister(String email){
        try{
            Integer count = new CheckRegisterTask().execute(email).get();
            return count;
        }catch (Exception e){
            return 99;
        }
    }

    private class CheckRegisterTask extends AsyncTask <String, Void, Integer>{
        @Override
        protected Integer doInBackground(String... item){
            return appDatabase.userDao().checkRegister(item[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
        }
    }
}
