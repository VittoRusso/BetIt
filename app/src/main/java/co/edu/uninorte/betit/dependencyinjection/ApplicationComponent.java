package co.edu.uninorte.betit.dependencyinjection;

import javax.inject.Singleton;

import co.edu.uninorte.betit.View.BetsFragment;
import co.edu.uninorte.betit.View.LoginFragment;
import co.edu.uninorte.betit.View.MatchesFragment;
import co.edu.uninorte.betit.View.ProfileFragment;
import dagger.Component;

@Singleton
@Component(modules =  {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MatchesFragment matchesFragment);

    void inject(BetsFragment fragment);

    void inject(LoginFragment loginFragment);

    void inject(ProfileFragment profileFragment);
}
