package com.survey.worklake.music;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomepageFragment extends Fragment implements View.OnClickListener {
    Button forward;
    TextView textView;

    public HomepageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        ImageView logo=(ImageView)v.findViewById(R.id.image);
         forward=(Button)v.findViewById(R.id.forw);
        textView=(TextView)v.findViewById(R.id.textView);


        forward.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forw:

                ((MainActivity)getActivity()).replacehome();
        }

    }
}