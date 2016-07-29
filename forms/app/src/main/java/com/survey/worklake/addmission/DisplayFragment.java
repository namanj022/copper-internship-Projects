package com.survey.worklake.addmission;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.orm.SugarRecord;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    private static long i;
    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_display, container, false);
        Button back = (Button)v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setSignInForm();
            }
        });
        setProfileDisplay(v);
        return v;


    }
    public void setProfileDisplay(View view) {
        String id = String.valueOf(i);
        List<Personalin> recordsPersonnal = SugarRecord.find(Personalin.class, "ID = ?", id);
        for (int i = 0; i < recordsPersonnal.size(); i++) {
            EditText personal = (EditText) view.findViewById(R.id.personalEditText);
            personal.setText(recordsPersonnal.get(i).toString());
        }
        List<Parentsin> recordsAdditional = SugarRecord.find(Parentsin.class, "ID = ?", id);
        for (int i = 0; i < recordsAdditional.size(); i++) {
            EditText personal = (EditText) view.findViewById(R.id.parentsEditText);
            personal.setText(recordsAdditional.get(i).toString());
        }
        List<Edcationinfo> recordsEducation = SugarRecord.find(Edcationinfo.class, "ID = ?", id);
        for (int i = 0; i < recordsEducation.size(); i++) {
            EditText personal = (EditText) view.findViewById(R.id.educationEditText);
            personal.setText(recordsEducation.get(i).toString());
        }
        List<Scoreinfo> recordsJEE = SugarRecord.find(Scoreinfo.class, "ID = ?", id);
        for (int i = 0; i < recordsJEE.size(); i++) {
            EditText personal = (EditText) view.findViewById(R.id.scoreEditText);
            personal.setText(recordsJEE.get(i).toString());
        }
    }
    public static DisplayFragment newInstance(long id) {
        i = id;
        DisplayFragment DisplayFragment = new DisplayFragment();
        return DisplayFragment;
    }

    }





