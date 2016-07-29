package com.survey.worklake.weather;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    JSONArray jsonArray;
    ImageView weatherIcon;

    public CustomListAdapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView;
        if (convertView == null) {
            listView = new View(context);
            listView = inflater.inflate(R.layout.customlistadapter, null);
         /*   listView.setMinimumHeight(300);
            listView.setMinimumWidth(125);*/

            TextView humidity = (TextView) listView.findViewById(R.id.fhumidity);
            TextView pressure = (TextView) listView.findViewById(R.id.fpresure);
            TextView wind = (TextView) listView.findViewById(R.id.fwind);
            TextView description = (TextView) listView.findViewById(R.id.fdesc);
            TextView time = (TextView) listView.findViewById(R.id.fdate);
            TextView temperature = (TextView) listView.findViewById(R.id.ftemp);
             weatherIcon = (ImageView) listView.findViewById(R.id.ficon);
            try {
                JSONObject object = jsonArray.getJSONObject(position);
                JSONObject weather = object.getJSONArray("weather").getJSONObject(0);
                JSONObject maindata = object.getJSONObject("main");
                JSONObject winddes = object.getJSONObject("wind");


                pressure.setText(maindata.getString("pressure"));
                humidity.setText(maindata.getString("humidity"));


                String date = object.getString("dt_txt");
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = f.parse(date);
                long ms = d.getTime();
                SimpleDateFormat df2 = new SimpleDateFormat("dd MMM, HH:mm");
                String dateText = df2.format(ms);
                time.setText(dateText.toString());
                description.setText(weather.getString("description").toUpperCase(Locale.US));
                wind.setText(winddes.getString("speed"));
                temperature.setText(
                        String.format("%.2f", maindata.getDouble("temp") - 273.15) + " ℃");



                setWeatherIcon(weather,weatherIcon,description);
            } catch (Exception e) {

            }
        } else {
            listView = (View) convertView;
        }
        return listView;
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
}
