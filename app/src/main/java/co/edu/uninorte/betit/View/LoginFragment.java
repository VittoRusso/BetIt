package co.edu.uninorte.betit.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.edu.uninorte.betit.BetItApplication;
import co.edu.uninorte.betit.R;
import co.edu.uninorte.betit.viewmodel.UsersViewModel;


public class LoginFragment extends Fragment {

    private static final String PAGE_TITLE = "PAGE_TITLE";

    private EditText edtEmail,edtPass;
    private AppCompatButton btnLogin,btnRegister;
    private UsersViewModel userModel;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = v.findViewById(R.id.input_email);
        edtPass = v.findViewById(R.id.input_password);
        btnLogin = v.findViewById(R.id.btn_login);
        btnRegister = v.findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        return v;
    }

    private void register() {
        Log.v("TAG","Register");
    }

    private void loginCheck(){
        Log.v("TAG","LoginCheck");
        if(!edtPass.getText().toString().matches("") && !edtEmail.getText().toString().matches("")){
            String email = edtEmail.getText().toString();
            String pass = edtPass.getText().toString();
            Integer count = userModel.checkUser(email,pass);
            Log.v("TAG",count+"");
        }
    }


    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

}
