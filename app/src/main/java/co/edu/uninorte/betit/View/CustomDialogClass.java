package co.edu.uninorte.betit.View;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import co.edu.uninorte.betit.R;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

    public Activity act;
    public Dialog dialog;
    public AppCompatButton login,register;
    public TextView email,pass;
    public int code;
    private CustomDialogClass.MyDialogListener mDialogListener;

    public CustomDialogClass (Activity act){
        super(act);

        this.act = act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        login = findViewById(R.id.btn_register);
        register = findViewById(R.id.btn_login);

        email = findViewById(R.id.input_email);
        pass = findViewById(R.id.input_password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(mDialogListener !=null) {
            switch (v.getId()) {
                case R.id.btn_login:
                    Log.v("TAG", "Dentro del dialog Login");
                    mDialogListener.loginSelected(email.getText().toString(), pass.getText().toString());
                    break;
                case R.id.btn_register:
                    Log.v("TAG", "Dentro del dialog Register");
                    mDialogListener.registerSelected(email.getText().toString(), pass.getText().toString());
                    break;
                default:
                    break;
            }
        }
    }

    public interface MyDialogListener {
        void loginSelected(String email, String pass);
        void registerSelected(String email, String pass);
    }

    public void setListener(CustomDialogClass.MyDialogListener mDialogListener){
        this.mDialogListener = mDialogListener;
    }


}
