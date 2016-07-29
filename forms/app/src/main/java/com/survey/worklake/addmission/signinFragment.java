package com.survey.worklake.addmission;


import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;



/**
 * A simple {@link Fragment} subclass.
 */
public class signinFragment extends Fragment {


    TextView linkSignUp;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View v =  inflater.inflate(R.layout.fragment_signin, container, false);
            Button submit = (Button)v.findViewById(R.id.btn_login);

            linkSignUp=(TextView)v.findViewById(R.id.link_signup);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkDetails(v);
                }
            });
           /*
            linkSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity()!=null)
                    {
                        ((MainActivity)getActivity()).switchToFragment();
                    }
                }
            });*/

            return v;
        }
    public void checkDetails(View view) {

        EditText password = (EditText)view.findViewById(R.id.input_password);
        EditText email = (EditText)view.findViewById(R.id.input_email);
        //List<Personalin> records =SugarRecord.listAll(Personalin.class);
        List<Personalin> records = SugarRecord.find(Personalin.class,"EMAIL = ?",email.getText().toString());

        for(int i = 0 ; i < records.size() ; i++) {
            if(records.get(i).getPassword().equals(password.getText().toString())) {
                   ((MainActivity)getActivity()).Display(records.get(i).getId());
            }
        }
    }
    public static signinFragment newInstance() {
        signinFragment signin = new signinFragment();
        return signin;
    }
}










