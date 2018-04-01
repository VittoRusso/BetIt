package co.edu.uninorte.betit.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import co.edu.uninorte.betit.model.Match;

/**
 * Created by Gabriel on 13/03/2018.
 */



public class FragAdapter extends FragmentPagerAdapter {

    public FragAdapter(FragmentManager manager){
        super(manager);
    }
    MatchesFragment matchesFragment;


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MatchesFragment matchesFragment = MatchesFragment.getInstance(ViewPagerActivity.pageTitles[position]);
                return matchesFragment;
            case 1:
                BetsFragment betsFragment = BetsFragment.getInstance(ViewPagerActivity.pageTitles[position]);
                return betsFragment;
            case 2:
                LoginFragment loginFragment = LoginFragment.getInstance(ViewPagerActivity.pageTitles[position]);
                return loginFragment;
            default:
                return ViewPagerItemFragment.getInstance(ViewPagerActivity.pageTitles[position]);
    }
    }

    @Override
    public int getCount() {
        return ViewPagerActivity.pageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return ViewPagerActivity.pageTitles[position];
    }
}