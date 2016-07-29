package com.survey.worklake.weather;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

public Button btn;
    EditText editText;
    Button btn2;
    Button btn3;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container1, new WeatherFragment())
                    .commit();


            editText = (EditText) findViewById(R.id.editText1);
             btn = (Button) findViewById(R.id.button1);
             btn2 = (Button) findViewById(R.id.button2);
            btn3=(Button)findViewById(R.id.button3);

        }

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        }




        public void switchToFragment(Fragment fragment)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }







    public void changeCity(String city){
        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container1);

        wf.changeCity(city);
        new CityPreference(this).setCity(city);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                changeCity(editText.getText().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new WeatherFragment()).commit();
                break;

            case R.id.button2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new ForecastFragment()).commit();
                break;
            case R.id.button3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new graphFragment()).commit();
                break;


        }
    }
}
