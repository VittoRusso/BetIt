package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.annotation.Nullable;

import co.edu.uninorte.betit.BetItApplication;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.model.User;
import co.edu.uninorte.betit.viewmodel.UsersViewModel;



public class LoginFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";

    private Button btnLog;
    private UsersViewModel userModel;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private boolean isLogged;
    private CustomDialogClass dialogClass;



    public LoginFragment() { }


    public static LoginFragment getInstance(String pageTitle){
            LoginFragment fragment = new LoginFragment();
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
        sharedPref = getActivity().getSharedPreferences(getString(R.string.preferenceKey),Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        btnLog = v.findViewById(R.id.btn_log);
        isLogged = sharedPref.getBoolean(getString(R.string.isLoginKey),false);
        if (isLogged){
            btnLog.setText("Sign Out");
        }else{
            btnLog.setText("Sign In");
        }

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLogged) {
                    dialogClass = new CustomDialogClass(getActivity());
                    dialogClass.setListener(new CustomDialogClass.MyDialogListener() {
                        @Override
                        public void loginSelected(String email, String pass) {
                            loginCheck(email, pass);
                        }

                        @Override
                        public void registerSelected(String email, String pass) {
                            register(email, pass);
                        }
                    });
                    dialogClass.show();
                }else{
                    isLogged = false;
                    btnLog.setText("Sign In");
                    editor.putBoolean(getString(R.string.isLoginKey), isLogged);
                    editor.commit();
                }
            }
        });

        return v;
    }


    public void register(String email,String pass) {
        Log.v("TAG","Register");
        if(!email.matches("") && !pass.matches("")){
            Integer count = userModel.checkRegister(email);
            Log.v("TAG",count+"");
            if(count==0){
                User user = new User();
                user.setEmail(email);
                user.setPassword(pass);
                userModel.addUser(user);
                Toast.makeText(getContext(),"User has been registered",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getContext(),"User already exists",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(),"Please enter a valid email and password",Toast.LENGTH_LONG).show();
        }
    }

    private void loginCheck(String email,String pass){
        Log.v("TAG","LoginCheck");
        isLogged = sharedPref.getBoolean(getString(R.string.isLoginKey),false);
        if(!isLogged) {
            if (!pass.matches("") && !email.matches("")) {
                Integer count = userModel.checkUser(email, pass);
                if (count >= 1) {
                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_LONG).show();
                    isLogged = true;
                    editor.putBoolean(getString(R.string.isLoginKey), isLogged);
                    editor.putString(getString(R.string.emailKey),email);
                    editor.commit();
                    dialogClass.dismiss();
                    btnLog.setText("Sign Out");
                } else {
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getContext(), "Please enter an email and password", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(), "User already logged in", Toast.LENGTH_LONG).show();
        }
    }

    private void changeFragment() {

    }


    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

}
