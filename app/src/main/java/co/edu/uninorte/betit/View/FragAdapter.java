package co.edu.uninorte.betit.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.Match;

/**
 * Created by Gabriel on 13/03/2018.
 */



public class FragAdapter extends FragmentPagerAdapter {


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private boolean isLogged;
    MatchesFragment matchesFragment;
    private Context context;
    private final FragmentManager mFragmentManager;

    public FragAdapter(FragmentManager fm, Context c) {
        super(fm);
        mFragmentManager = fm;
        context = c;
    }

    @Override
    public Fragment getItem(int position) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.preferenceKey),Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        isLogged = sharedPref.getBoolean(context.getString(R.string.isLoginKey),false);
        Log.v("TAG",isLogged+"");
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