package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uninorte.betit.BetItApplication;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.JsonData;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.model.Stadium;
import co.edu.uninorte.betit.viewmodel.BetViewModel;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class BetsFragment extends Fragment implements MatchViewInterface{

    private static final String PAGE_TITLE = "PAGE_TITLE";

    private static final String MATCH_ID = "MATCH_ID" ;
    private static final String MATCHES = "MATCHES" ;
    private static final String MATCH_ = "MATCH";
    private static final String TEAMS = "TEAMS";
    private static final String STADIUMS = "STADIUMS";


    private HashMap<Integer, Integer>  flags;

    private List<Match> matches;
    private List<String> teams;
    private List<String> stadiums;


    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    JsonDataViewModel viewModel;
    private BetViewModel betmodel;
    private String currentUser ="1";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public BetsFragment(){}



    public static BetsFragment getInstance(String pageTitle){
        BetsFragment fragment = new BetsFragment();
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

        flags = createFlagMap();
    }



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this.getActivity()).get(JsonDataViewModel.class);
        betmodel = ViewModelProviders.of(this).get(BetViewModel.class);
        viewModel.getLiveData().observe(this, liveData -> {
            betmodel.getBets().observe(this, bets -> {

            sharedPref = getContext().getSharedPreferences(getContext().getString(R.string.preferenceKey), Context.MODE_PRIVATE);
            editor = sharedPref.edit();
            this.currentUser = sharedPref.getString(getContext().getString(R.string.emailKey),"");


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

            Collections.sort(bets);
            setUpAdapterAndView(bets);


        });});
    }



    private List<Match> removeBets(List<Match> betsnResults) {
        List<Match> results = new ArrayList();
        for (Match match : betsnResults){
            if (!match.isBet()){
                results.add(match);
            }
        }
        return results;
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

    @Override
    public void startMatchDetailActivity(int index) {
        Intent i = new Intent (this.getContext(), MatchDetailActivity.class);

        i.putExtra(MATCH_ID, index);
        i.putExtra(MATCHES, (Serializable) this.matches);

        i.putExtra(MATCH_, this.matches.get(index));
        i.putExtra(TEAMS, (Serializable) this.teams);
        i.putExtra(STADIUMS, (Serializable)this.stadiums);


        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<Match> matches) {
        this.matches = matches;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        matchAdapter = new MatchAdapter();
        recyclerView.setAdapter(matchAdapter);

    }


    public static HashMap<Integer,Integer> createFlagMap() {
        HashMap<Integer, Integer> flagmap = new HashMap();
        flagmap.put(1,R.mipmap.russia_round_icon_640);
        flagmap.put(2,R.mipmap.saudi_arabia_round_icon_640);
        flagmap.put(3,R.mipmap.egypt_640);
        flagmap.put(4,R.mipmap.uruguay_640);
        flagmap.put(5,R.mipmap.portugal_640);
        flagmap.put(6,R.mipmap.spain_640);
        flagmap.put(7,R.mipmap.morocco_640);
        flagmap.put(8,R.mipmap.iran_round_icon_640);
        flagmap.put(9,R.mipmap.france_640);
        flagmap.put(10,R.mipmap.australia_640);
        flagmap.put(11,R.mipmap.peru_640);
        flagmap.put(12,R.mipmap.denmark_640);
        flagmap.put(13,R.mipmap.argentina_640);
        flagmap.put(14,R.mipmap.iceland_640);
        flagmap.put(15,R.mipmap.croatia_640);
        flagmap.put(16,R.mipmap.nigeria_640);
        flagmap.put(17,R.mipmap.brazil_640);
        flagmap.put(18,R.mipmap.switzerland_640);
        flagmap.put(19,R.mipmap.costa_rica_640);
        flagmap.put(20,R.mipmap.serbia_640);
        flagmap.put(21,R.mipmap.germany_640);
        flagmap.put(22,R.mipmap.mexico_640);
        flagmap.put(23,R.mipmap.sweden_640);
        flagmap.put(24,R.mipmap.korea_south_640);
        flagmap.put(25,R.mipmap.belgium_640);
        flagmap.put(26,R.mipmap.panama_640);
        flagmap.put(27,R.mipmap.tunisia_640);
        flagmap.put(28,R.mipmap.england_640);
        flagmap.put(29,R.mipmap.poland_640);
        flagmap.put(30,R.mipmap.senegal_640);
        flagmap.put(31,R.mipmap.colombia_640);
        flagmap.put(32,R.mipmap.japan_640);

        return flagmap;
    }



    private class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.CustomViewHolder>{


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.recycler_item_match, parent, false);

            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            Match currentMatch = matches.get(position);

            holder.team1text.setText(
                    teams.get(currentMatch.getHomeTeam()-1)
            );

            holder.team2text.setText(
                    teams.get(currentMatch.getAwayTeam()-1)
            );

            holder.dateText.setText(
                    currentMatch.getSimpleDate()
            );

            holder.team1Flag.setBackground(
                    getResources().getDrawable(flags.get(currentMatch.getHomeTeam()))
            );
            holder.team2Flag.setBackground(
                    getResources().getDrawable(flags.get(currentMatch.getAwayTeam()))
            );

            holder.team1score.setText(String.valueOf(currentMatch.getHomeResult()));
            holder.team2score.setText(String.valueOf(currentMatch.getAwayResult()));

            if (currentMatch.isBet()){
                //Bets are gray
                holder.container.setBackgroundColor(getResources().getColor(R.color.gray_200));

            }
            else {
                //Results are yellow
                holder.container.setBackgroundColor(getResources().getColor(R.color.yellow_200));
            }
            if (currentMatch.getUser().equals(currentUser)){
                holder.container.setBackgroundColor(getResources().getColor(R.color.blue_100));
            }

        }

        @Override
        public int getItemCount() {
            return matches.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView team1text;
            private TextView team2text;
            private ViewGroup container;

            private TextView dateText;

            private ImageView team1Flag;
            private ImageView team2Flag;

            private TextView team1score;
            private TextView team2score;




            public CustomViewHolder(View matchView){
                super(matchView);

                this.team1text = matchView.findViewById(R.id.team1_text);
                this.team2text = matchView.findViewById(R.id.team2_text);
                this.container = matchView.findViewById(R.id.root_list_match);
                this.team1Flag = matchView.findViewById(R.id.team1_flag);
                this.team2Flag = matchView.findViewById(R.id.team2_flag);
                this.team1score = matchView.findViewById(R.id.team1_score);
                this.team2score = matchView.findViewById(R.id.team2_score);

                this.dateText  = matchView.findViewById(R.id.dateText);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                startMatchDetailActivity(this.getAdapterPosition());

            }
        }
    }
}
