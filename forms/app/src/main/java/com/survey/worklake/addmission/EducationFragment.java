package com.survey.worklake.addmission;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EducationFragment extends Fragment {
    EditText school_name;
private Button bt;
    EditText percentage10;
    EditText board;
    EditText school_name12;
    EditText percentage12;
    EditText board12;
    public EducationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_education, container, false);
      school_name = (EditText) v.findViewById(R.id.schoolname);
      percentage10 = (EditText) v.findViewById(R.id.percentage10);
      board = (EditText) v.findViewById(R.id.board);
      school_name12 = (EditText) v.findViewById(R.id.schoolname12);
      percentage12 = (EditText) v.findViewById(R.id.percentage12);
        board12 = (EditText) v.findViewById(R.id.board12);



        bt = (Button)v.findViewById(R.id.save1);

//    bt.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v){


//            if (school_name.getText().toString().length() == 0)
//                school_name.setError(" required!");
//
//
//            if (percentage10.getText().toString().length() == 0)
//                percentage10.setError("  required!");
//
//
//
//            if (board.getText().toString().length() == 0)
//                board.setError(" required!");
//
//
//
//            if (school_name12.getText().toString().length() == 0)
//                school_name12.setError("  required!");
//
//
//
//            if (percentage12.getText().toString().length() == 0)
//                percentage12.setError("  required!");
//
//
//
//            if (school_name.getText().toString().length() == 0)
//                school_name.setError("  required!");
//
//
//        }
//    });
        return v;
}


    public static EducationFragment newInstance() {
        EducationFragment edu=new EducationFragment();
        return edu;

    }


}

