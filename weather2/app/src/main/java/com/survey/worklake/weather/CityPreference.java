package com.survey.worklake.weather;

/**
 * Created by copperadmin on 7/14/2016.
 */
import android.app.Activity;
import android.content.SharedPreferences;
public class CityPreference {






        SharedPreferences prefs;

        public CityPreference(Activity activity){
            prefs = activity.getPreferences(Activity.MODE_PRIVATE);
        }

        // If the user has not chosen a city yet, return
        // Sydney as the default city
        String getCity(){
            return prefs.getString("city", "London");
        }

        void setCity(String city){
            prefs.edit().putString("city", city).commit();
        }

    }
