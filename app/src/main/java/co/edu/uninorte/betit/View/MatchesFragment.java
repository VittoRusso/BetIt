package co.edu.uninorte.betit.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.betit.R;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class MatchesFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";
    private static final String NEW_CONTENT = "NEW_CONTENT";

    private String pageTitle;
    private MatchesFragmentCallback callback;
    private String newContent;

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


    public interface  MatchesFragmentCallback {
        void onPagerItemClick(String message);
    }
}
