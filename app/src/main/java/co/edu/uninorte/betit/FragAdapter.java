package co.edu.uninorte.betit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.reflect.Array;

import co.edu.uninorte.betit.Data.Bet;
import co.edu.uninorte.betit.Data.Match;
import co.edu.uninorte.betit.Data.User;

import static co.edu.uninorte.betit.ViewPagerActivity.pageTitles;

/**
 * Created by Gabriel on 13/03/2018.
 */



public class FragAdapter extends FragmentPagerAdapter {


    private Match[] matches;
    private Bet[] bets;
    private User user;


    //TODO: Constructors!!!!
    public FragAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ViewPagerItemFragment tab1 = ViewPagerItemFragment.getInstance(matches);
                return tab1;
            case 1:
                ViewPagerItemFragment tab2 = ViewPagerItemFragment.getInstance(bets);
                return tab2;
            case 2:
                ViewPagerItemFragment tab3 = ViewPagerItemFragment.getInstance(user);
                return tab3;
            default:
                throw new RuntimeException("Tab position invalid"+ position);
        }
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return pageTitles[position];
    }
}