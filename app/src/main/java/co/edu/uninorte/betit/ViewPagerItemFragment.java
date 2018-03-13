package co.edu.uninorte.betit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.uninorte.betit.Data.Bet;
import co.edu.uninorte.betit.Data.Match;
import co.edu.uninorte.betit.Data.User;

/**
 * Created by Gabriel on 11/03/2018.
 */

public class ViewPagerItemFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";
    private static final String MATCHES = "MATCHES";
    private static final String BETS = "BETS";
    private static final String USER = "USER";

    public static final String[] pageTitles = ViewPagerActivity.pageTitles;


    private String pageTitle;
    private FragmentPagerItemCallback callback;
    private Match[] matches;
    private Bet[] bets;
    private User[] user;
    int index;

    public ViewPagerItemFragment(){}

    public static ViewPagerItemFragment getInstance(String pageTitle){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putString(PAGE_TITLE, pageTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public static ViewPagerItemFragment getInstance(Match[] matches){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(MATCHES,matches);
        fragment.setArguments(args);
        return fragment;
    }

    public static ViewPagerItemFragment getInstance(Bet[] bets){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(BETS,bets);
        fragment.setArguments(args);
        return fragment;
    }

    public static ViewPagerItemFragment getInstance(User user){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER,user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pageTitle = getArguments().getString(PAGE_TITLE);

            for (int i=0; i<pageTitles.length;i++){
                if (pageTitles[index]== pageTitle){
                    this.index = i;
                    break;
                }
            }
            switch (index){
                case 0:
                    Toast.makeText(getContext(),"Henlo choq 1",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(),"Henlo choq 2",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(),"Henlo choq 3",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getContext(),"cmamut",Toast.LENGTH_SHORT).show();
                    break;
            }

        } else {
            Log.d("TAG", "Well... F***.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_pager_item, container, false);
        TextView content = ((TextView) v.findViewById(R.id.lbl_pager_item_content));
        content.setText(pageTitle);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onPagerItemClick(
                        ((TextView) v).getText().toString()
                );
            }
        });

        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentPagerItemCallback) {
            callback = (FragmentPagerItemCallback) context;
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


    public interface  FragmentPagerItemCallback {
        void onPagerItemClick(String message);
    }
}
