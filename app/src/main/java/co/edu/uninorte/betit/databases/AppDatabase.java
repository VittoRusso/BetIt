package co.edu.uninorte.betit.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.edu.uninorte.betit.dao.BetsDao;
import co.edu.uninorte.betit.model.Match;

@Database(entities = {Match.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract BetsDao betsDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(final Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "demo").build();
        }
        return INSTANCE;
    }
}
