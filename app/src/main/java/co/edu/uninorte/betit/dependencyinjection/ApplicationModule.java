package co.edu.uninorte.betit.dependencyinjection;


import android.app.Application;

import co.edu.uninorte.betit.BetItApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final BetItApplication betItApplication;
    public ApplicationModule(BetItApplication betItApplication){
        this.betItApplication = betItApplication;
    }

    @Provides
    BetItApplication provideBetItApplication(){
        return betItApplication;
    }

    @Provides
    Application provideApplication(){
        return betItApplication;
    }
}
