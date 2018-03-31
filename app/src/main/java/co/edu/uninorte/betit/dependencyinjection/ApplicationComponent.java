package co.edu.uninorte.betit.dependencyinjection;


import javax.inject.Singleton;

import co.edu.uninorte.betit.View.LoginFragment;
import co.edu.uninorte.betit.View.MatchesFragment;
import dagger.Component;

@Singleton
@Component(modules =  {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MatchesFragment matchesFragment);

    void inject(LoginFragment loginFragment);
}
