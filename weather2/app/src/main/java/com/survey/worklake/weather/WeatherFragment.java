package com.survey.worklake.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class WeatherFragment extends Fragment {

    Typeface weatherFont;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView humidity;
    TextView presure;
    ImageView weatherIcon;
    private JSONObject json;
    Handler handler;
    View rootView;

    public WeatherFragment() {
        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        humidity=(TextView)rootView.findViewById(R.id.humidity);
        presure=(TextView)rootView.findViewById(R.id.presure);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (ImageView)rootView.findViewById(R.id.weather_icon);


        updateWeatherData(new CityPreference(getActivity()).getCity());



        return rootView;
    }



    private void updateWeatherData(final String city){
        new Thread(){
            public void run(){


                    String url = "http://api.openweathermap.org/data/2.5/weather?q=";

                             JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                            url+city, null,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(final JSONObject response) {
                                    json=response;
                                    if(json==null){
                                    handler.post(new Runnable(){
                                        public void run(){
                                            Toast.makeText(getActivity(),
                                                    getActivity().getString(R.string.place_not_found),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } else {
                                    handler.post(new Runnable(){
                                        public void run(){
                                            renderWeather(json);
                                     /*       String responseString = response.toString();
                                            Intent intent = new Intent(getActivity(),Widget.class);
                                            intent.setAction(Widget.ACTION_TEXT_CHANGED);
                                            intent.putExtra("weather", responseString);
                                            getActivity().sendBroadcast(intent);*/
                                        }
                                    });
                                }
                                }
                            }, new Response.ErrorListener() {

                                 @Override
                                 public void onErrorResponse(VolleyError error) {
                                 }
                             }) {


                                 @Override
                                 public Map<String, String> getHeaders() throws AuthFailureError {
                                     Map<String, String> headers = new HashMap<String, String>();
                                     headers.put("Content-Type", "application/json");
                                     headers.put("X-API-KEY", "45f36b952810ead017897e91785eb905");
                                     return headers;
                                 }

                             };

                    Volley.newRequestQueue(getActivity()).add(jsonObjReq);

            }
        }.start();
    }



   private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) );
                            humidity.setText("Humidity: " + main.getString("humidity") + "%");
                            presure.setText( "Pressure: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp")-273.15)+ " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: " + updatedOn);

            setWeatherIcon(json,weatherIcon,detailsField);

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    public  void setWeatherIcon(JSONObject details, ImageView weatherIcon, TextView description) {
        try {
            switch (Integer.parseInt(details.getString("id")) / 100) {
                case 3:
                    weatherIcon.setBackgroundResource(R.drawable.fewclouds);
                    break;
                case 5:
                    weatherIcon.setBackgroundResource(R.drawable.fewclouds);
                    break;
                case 2:
                    weatherIcon.setBackgroundResource(R.drawable.thunderstrom);
                    break;
                case 6:
                    weatherIcon.setBackgroundResource(R.drawable.mist);
                    break;
                case 7:
                    weatherIcon.setBackgroundResource(R.drawable.mist);
                    break;
                case 8:
                    if (description.getText().toString().toUpperCase(Locale.US).equals("CLEAR SKY")) {
                        weatherIcon.setBackgroundResource(R.drawable.sunny);
                        break;
                    } else {
                        weatherIcon.setBackgroundResource(R.drawable.clearsky);
                        break;
                    }
            }
        } catch (Exception e) {
        }
    }

    public void changeCity(String city){
        updateWeatherData(city);
    }



}








