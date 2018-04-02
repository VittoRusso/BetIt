package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import co.edu.uninorte.betit.BetItApplication;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.JsonData;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.model.Stadium;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class UsersFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";

    private static final String MATCH_ID = "MATCH_ID" ;
    private static final String MATCHES = "MATCHES" ;
    private static final String MATCH_ = "MATCH";
    private static final String TEAMS = "TEAMS";
    private static final String STADIUMS = "STADIUMS";




    private List<Match> matches;
    private List<String> teams;
    private List<String> stadiums;


    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    JsonDataViewModel viewModel;

    public UsersFragment(){}



    public static UsersFragment getInstance(String pageTitle){
        UsersFragment fragment = new UsersFragment();
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


    }



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this.getActivity()).get(JsonDataViewModel.class);
        viewModel.getLiveData().observe(this, liveData -> {

            List<String> teams = new ArrayList();
            for (co.edu.uninorte.betit.model.Team team : liveData.getTeams()){
                teams.add(team.getName());
            }
            this.teams = teams;
            List<String> stadiums = new ArrayList();
            for (Stadium stadium: liveData.getStadiums()){
                stadiums.add(stadium.getName());
            }
            this.stadiums = stadiums;

            List<Match> matches = createListOfMatches(liveData);
            setMatchesOnFragment(matches);
            setUpAdapterAndView(matches);


        });
    }



    private void setMatchesOnFragment(List<Match> matches) {




    }

    private List<Match> createListOfMatches(JsonData liveData) {
        List<Match> matches = new ArrayList();

        matches.addAll(liveData.getGroups().getA().getMatches());
        matches.addAll(liveData.getGroups().getB().getMatches());
        matches.addAll(liveData.getGroups().getC().getMatches());
        matches.addAll(liveData.getGroups().getD().getMatches());
        matches.addAll(liveData.getGroups().getE().getMatches());
        matches.addAll(liveData.getGroups().getF().getMatches());
        matches.addAll(liveData.getGroups().getG().getMatches());
        matches.addAll(liveData.getGroups().getH().getMatches());

        Collections.sort(matches);
        return matches;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match, container, false);
        recyclerView = v.findViewById(R.id.rec_matches);
        layoutInflater = inflater;

        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void startMatchDetailActivity(int index) {

    }


    public void setUpAdapterAndView(List<Match> matches) {
        this.matches = matches;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        matchAdapter = new MatchAdapter();
        recyclerView.setAdapter(matchAdapter);

    }






    private class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.CustomViewHolder>{


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.recycler_item_user, parent, false);

            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            Match currentMatch = matches.get(position);

            holder.email.setText(
                    teams.get(currentMatch.getHomeTeam()-1)
            );
            holder.position.setText(
                    String.valueOf(position+1)
            );
            holder.score.setText(
                    String.valueOf(position)
            );

        }

        @Override
        public int getItemCount() {
            return matches.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

           private TextView position;
           private TextView email;
           private TextView score;
           private ViewGroup container;




            public CustomViewHolder(View matchView){
                super(matchView);

                this.score = matchView.findViewById(R.id.score_text);
                this.email = matchView.findViewById(R.id.email_text);
                this.position = matchView.findViewById(R.id.position_text);
                this.container = matchView.findViewById(R.id.root_list_user);

            }

            @Override
            public void onClick(View view) {


            }
        }
    }
}
