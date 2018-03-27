package co.edu.uninorte.betit;

import android.app.Application;

import co.edu.uninorte.betit.dependencyinjection.ApplicationComponent;
import co.edu.uninorte.betit.dependencyinjection.ApplicationModule;
import co.edu.uninorte.betit.dependencyinjection.DaggerApplicationComponent;

public class BetItApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
