package com.survey.worklake.addmission;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager pager;
    Pagerlist array;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           setSignInForm();

    }



    public void checkPersonal(View view) {
        if (array.getSize() > 1) {
            return;
        }
        view = array.mfragments.get(0).getView();
        EditText name = (EditText) view.findViewById(R.id.name);
        EditText dob = (EditText) view.findViewById(R.id.dob);
        EditText mobile = (EditText) view.findViewById(R.id.mobile);
        EditText email = (EditText) view.findViewById(R.id.email);
        EditText password = (EditText) view.findViewById(R.id.pass);
        EditText category = (EditText) view.findViewById(R.id.category);


        if (name.getText().length() != 0 &&
                mobile.getText().length() != 0 &&
                password.getText().length() != 0 &&
                email.getText().length() != 0 &&
                category.getText().length() != 0 &&
                dob.getText().length() != 0) {

            Personalin personal = new Personalin(name.getText().toString() , dob.getText().toString(),
                    mobile.getText().toString(), email.getText().toString(), password.getText().toString(), category.getText().toString());
            personal.save();

            array.addTab(ParentsinfoFragment.newInstance());
            pager.setCurrentItem(array.getSize() - 1);
        }
    }

    public void checkParents(View view) {
        if (array.getSize() > 2) {
            return;
        }
        view = array.mfragments.get(1).getView();
        EditText fathername = (EditText) view.findViewById(R.id.fathername);
        EditText mothername = (EditText) view.findViewById(R.id.mothername);

        EditText phone = (EditText) view.findViewById(R.id.phone);
        EditText email = (EditText) view.findViewById(R.id.input_email);
        EditText fatheroccupation = (EditText) view.findViewById(R.id.fatheroccupation);
        EditText motheroccupation = (EditText) view.findViewById(R.id.motheroccupation);


        if (fathername.getText().length() != 0 &&
                mothername.getText().length() != 0 &&
                phone.getText().length() != 0 &&
                email.getText().length() != 0 &&
                fatheroccupation.getText().length() != 0 &&
                motheroccupation.getText().length() != 0) {

            Parentsin parent = new Parentsin(fathername.getText().toString() ,mothername.getText().toString(),
                    phone.getText().toString(), email.getText().toString(), fatheroccupation.getText().toString(), motheroccupation.getText().toString());
            parent.save();
            array.addTab(EducationFragment.newInstance());
            pager.setCurrentItem(array.getSize() - 1);

        }

    }

    public void checkEducation(View view) {
        if (array.getSize() > 3) {
            return;
        }
        view = array.mfragments.get(2).getView();
        EditText schoolname = (EditText) view.findViewById(R.id.schoolname);
        EditText schoolname12 = (EditText) view.findViewById(R.id.schoolname12);

        EditText percentage = (EditText) view.findViewById(R.id.percentage10);
        EditText percentage12 = (EditText) view.findViewById(R.id.percentage12);
        EditText board = (EditText) view.findViewById(R.id.board);
        EditText board12 = (EditText) view.findViewById(R.id.board12);


        if (schoolname.getText().length() != 0 &&
                schoolname12.getText().length() != 0 &&
                board.getText().length() != 0 &&
                board12.getText().length() != 0 &&
                percentage.getText().length() != 0 &&
                percentage12.getText().length() != 0) {


            Edcationinfo education = new Edcationinfo(schoolname.getText().toString() , percentage.getText().toString(),
                    board.getText().toString(),schoolname12.getText().toString(),percentage12.getText().toString(),board12.getText().toString());
            education.save();
            array.addTab(ScoreFragment.newInstance());
            pager.setCurrentItem(array.getSize() - 1);

        }

    }

    public void checkscore(View view) {
        view = array.mfragments.get(3).getView();
        EditText scoremain = (EditText) view.findViewById(R.id.main);
        EditText mainRank = (EditText) view.findViewById(R.id.rankmain);
        EditText Scoreadvance = (EditText) view.findViewById(R.id.advanced);
        EditText advanceRank = (EditText) view.findViewById(R.id.rankadvanced);

        if (scoremain.getText().length() != 0 &&
                mainRank.getText().length() != 0 &&
                Scoreadvance.getText().length() != 0 &&
                advanceRank.getText().length() != 0) {

            Scoreinfo score = new Scoreinfo(scoremain.getText().toString(),
                    mainRank.getText().toString(),
                    Scoreadvance.getText().toString(),
                    advanceRank.getText().toString());
            score.save();
      setSignInForm();


        }
    }

    public void setSignInForm() {
        signinFragment signIn = new signinFragment().newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, signIn).commit();
    }
    public  void signinform(){
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.scorefragment)).commit();
        signinFragment signIn = new signinFragment().newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, signIn).commit();

    }




    public void SignUp(View v) {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container)).commit();
        pager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        array = new Pagerlist(getSupportFragmentManager(), pager, tabLayout);
        pager.setAdapter(array);
        tabLayout.setupWithViewPager(pager);
        for(int i = 0 ; i < array.getSize()  ; i++) {
            switch(i) {
                case 0:tabLayout.getTabAt(i).setIcon(R.drawable.user);
                    break;
                case 1:tabLayout.getTabAt(i).setIcon(R.drawable.f);
                    break;
                case 2:tabLayout.getTabAt(i).setIcon(R.drawable.books);
                    break;
                case 3:tabLayout.getTabAt(i).setIcon(R.drawable.dow);
                    break;}}


    }























      /* getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container)).commit();
          pager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        array = new Pagerlist(getSupportFragmentManager(),pager,tabLayout);
        pager.setAdapter(array);
        tabLayout.setupWithViewPager(pager);
        for(int i = 0 ; i < array.getSize()  ; i++) {
            switch(i) {
                case 0:tabLayout.getTabAt(i).setIcon(R.drawable.user);
                    break;
                case 1:tabLayout.getTabAt(i).setIcon(R.drawable.f);
                    break;
                case 2:tabLayout.getTabAt(i).setIcon(R.drawable.books);
                    break;
                case 3:tabLayout.getTabAt(i).setIcon(R.drawable.dow);
                    break;}}
*/


    public void Display(long id) {
        DisplayFragment displayFragment = DisplayFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,displayFragment).commit();
    }


}











