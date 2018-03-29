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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.JsonData;
import co.edu.uninorte.betit.model.Match;
import co.edu.uninorte.betit.viewmodel.JsonDataViewModel;
import co.edu.uninorte.betit.BetItApplication;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class MatchesFragment extends Fragment implements MatchViewInterface{

    private static final String PAGE_TITLE = "PAGE_TITLE";

    private static final String EXTRA_TEAMS = "EXTRA_TEAMS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    private List<co.edu.uninorte.betit.model.Match> matches;
    private List<String> teams;


    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;



    JsonDataViewModel viewModel;

    public MatchesFragment(){}

    public static MatchesFragment getInstance(String pageTitle){
                MatchesFragment fragment = new MatchesFragment();
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

            List<co.edu.uninorte.betit.model.Match> matches = createListOfMatches(liveData);

            setUpAdapterAndView(matches);



        });
    }

    private List<Match> createListOfMatches(JsonData liveData) {
        List<co.edu.uninorte.betit.model.Match> matches = new ArrayList();


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
        if (context instanceof MatchesFragmentCallback) {
            //callback = (MatchesFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentPagerItemCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // callback = null;
    }

    @Override
    public void startMatchDetailActivity(Team[] teams, String date, String location) {
        Intent i = new Intent (this.getContext(), MatchDetailActivity.class);

        //Es mejor pasar el id unico
        i.putExtra(EXTRA_TEAMS,teams);
        i.putExtra(EXTRA_DATE,date);
        i.putExtra(EXTRA_LOCATION,location);
        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<co.edu.uninorte.betit.model.Match> matches) {
        this.matches = matches;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        matchAdapter = new MatchAdapter();
        recyclerView.setAdapter(matchAdapter);

    }


    public interface  MatchesFragmentCallback {
        void onPagerItemClick(String message);
    }

    private class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.CustomViewHolder>{


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.recycler_item_match, parent, false);

            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            co.edu.uninorte.betit.model.Match currentMatch = matches.get(position);

            holder.team1text.setText(
                    teams.get(currentMatch.getHomeTeam()-1)
            );

            holder.team2text.setText(
                    teams.get(currentMatch.getAwayTeam()-1)
            );

            holder.dateText.setText(
                    currentMatch.getSimpleDate()
            );

//            if (position> 0 && holder.dateText.getText() == matches.get(position-1).getDate()){
//                holder.dateText.setVisibility(View.INVISIBLE);
//                ViewGroup.LayoutParams params = holder.container.getLayoutParams();
//                params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getResources().getDisplayMetrics());
//                holder.container.setLayoutParams(params);
//            } else if (position == 0){
//                holder.dateText.setVisibility(View.VISIBLE);
//                ViewGroup.LayoutParams params = holder.container.getLayoutParams();
//                params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
//                holder.container.setLayoutParams(params);
//            }
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


            public CustomViewHolder(View matchView){
                super(matchView);

                this.team1text = matchView.findViewById(R.id.team1_text);
                this.team2text = matchView.findViewById(R.id.team2_text);
                this.container = matchView.findViewById(R.id.root_list_match);

                this.dateText  = matchView.findViewById(R.id.dateText);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                co.edu.uninorte.betit.model.Match match = matches.get(
                        this.getAdapterPosition()
                );
                Toast.makeText(getContext(), match.getDate(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
