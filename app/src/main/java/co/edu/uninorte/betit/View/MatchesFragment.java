package co.edu.uninorte.betit.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.uninorte.betit.Data.FakeMatchSource;
import co.edu.uninorte.betit.Data.Match;
import co.edu.uninorte.betit.Data.Team;
import co.edu.uninorte.betit.Logic.MatchController;
import co.edu.uninorte.betit.R;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class MatchesFragment extends Fragment implements MatchViewInterface{

    private static final String PAGE_TITLE = "PAGE_TITLE";
    private static final String NEW_CONTENT = "NEW_CONTENT";

    private String pageTitle;
    private MatchesFragmentCallback callback;
    private String newContent;

    private static final String EXTRA_TEAMS = "EXTRA_TEAMS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    private List<Match> matches;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;
    private MatchController matchController;




    public MatchesFragment(){}

    public static MatchesFragment getInstance(String pageTitle, String newContent){
                MatchesFragment fragment = new MatchesFragment();
                Bundle args = new Bundle();
                args.putString(PAGE_TITLE, pageTitle);
                args.putString(NEW_CONTENT, newContent);
                fragment.setArguments(args);
                return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pageTitle = getArguments().getString(PAGE_TITLE);
            this.newContent = getArguments().getString(NEW_CONTENT);
            }
        else {
            Log.d("TAG", "Well... F***.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match, container, false);
        recyclerView = v.findViewById(R.id.rec_matches);
        layoutInflater = inflater;

        //This is dependency injection
        matchController = new MatchController(this, new FakeMatchSource());

        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MatchesFragmentCallback) {
            callback = (MatchesFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentPagerItemCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
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
    public void setUpAdapterAndView(List<Match> matches) {
        this.matches = matches;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
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
            Match currentMatch = matches.get(position);

            holder.team1text.setText(
                    currentMatch.getTeams()[0].toString()
            );

            holder.team2text.setText(
                    currentMatch.getTeams()[1].toString()
            );
        }

        @Override
        public int getItemCount() {
            return matches.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView team1text;
            private TextView team2text;
            private ViewGroup container;


            public CustomViewHolder(View matchView){
                super(matchView);

                this.team1text = matchView.findViewById(R.id.team1_text);
                this.team2text = matchView.findViewById(R.id.team2_text);
                this.container = matchView.findViewById(R.id.root_list_match);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Match match = matches.get(
                        this.getAdapterPosition()
                );
                matchController.onListItemClick(match);
            }
        }
    }
}
