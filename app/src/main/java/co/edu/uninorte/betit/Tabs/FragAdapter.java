package co.edu.uninorte.betit.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import static co.edu.uninorte.betit.Tabs.ViewPagerActivity.pageTitles;

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
            MatchesFragment matchesFragment = MatchesFragment.getInstance(pageTitles[position],"hola");
            return matchesFragment;
        }else {
            ViewPagerItemFragment fragment = ViewPagerItemFragment.getInstance(pageTitles[position]);
            return fragment;
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