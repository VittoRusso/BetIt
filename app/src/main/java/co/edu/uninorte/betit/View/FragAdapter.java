package co.edu.uninorte.betit.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gabriel on 13/03/2018.
 */



public class FragAdapter extends FragmentPagerAdapter {

    public FragAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            MatchesFragment matchesFragment = MatchesFragment.getInstance(ViewPagerActivity.pageTitles[position]);
            return matchesFragment;
        }else {
            ViewPagerItemFragment fragment = ViewPagerItemFragment.getInstance(ViewPagerActivity.pageTitles[position]);
            return fragment;
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