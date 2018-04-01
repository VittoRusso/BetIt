package co.edu.uninorte.betit.dependencyinjection;


import android.app.Fragment;

import javax.inject.Singleton;

import co.edu.uninorte.betit.View.BetsFragment;
import co.edu.uninorte.betit.View.LoginFragment;
import co.edu.uninorte.betit.View.MatchesFragment;
import dagger.Component;

@Singleton
@Component(modules =  {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MatchesFragment matchesFragment);

    void inject(BetsFragment fragment);

    void inject(LoginFragment loginFragment);
}
