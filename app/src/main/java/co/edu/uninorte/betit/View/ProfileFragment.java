package co.edu.uninorte.betit.View;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.betit.BetItApplication;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.viewmodel.UsersViewModel;


public class ProfileFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";
    private UsersViewModel userModel;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private boolean isLogged;


    public ProfileFragment() {
    }


    public static ProfileFragment getInstance(String pageTitle){
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(PAGE_TITLE, pageTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String pageTitle = getArguments().getString(PAGE_TITLE);

        }
        else {
            Log.d("TAG", "Well... F***.");
        }
        ((BetItApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        userModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        sharedPref = getActivity().getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

}
