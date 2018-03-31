package co.edu.uninorte.betit.databases;

import android.app.Application;

public class BasicApp extends Application {

    public AppDatabase getDatabase() {return AppDatabase.getINSTANCE(this);}
}
