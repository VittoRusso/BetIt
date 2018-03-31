package co.edu.uninorte.betit.databases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import co.edu.uninorte.betit.dao.BetsDao;
import co.edu.uninorte.betit.dao.UserDao;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.model.User;

@Database(entities = {Match.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

   public abstract BetsDao betsDao();
   public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(final Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "demo").build();
        }
        return INSTANCE;
    }

}
