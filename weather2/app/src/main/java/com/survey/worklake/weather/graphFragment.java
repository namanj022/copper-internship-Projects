package com.survey.worklake.weather;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class graphFragment extends Fragment {
    private JSONObject json2;
    Handler handler2;

    LineChart chart;
    LineChart chart1;
    LineChart chart2;


    public graphFragment() { handler2 = new Handler();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_graph, container, false);
        chart = (LineChart)v.findViewById(R.id.chart);
        chart1 = (LineChart)v.findViewById(R.id.chart1);
        chart2 = (LineChart)v.findViewById(R.id.chart2);
        updategraphData(new CityPreference(getActivity()).getCity());
        return v;
    }


    private void updategraphData(final String city){
        new Thread(){
            public void run(){


                String url = "http://api.openweathermap.org/data/2.5/forecast?q=";

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url+city, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                json2=response;
                                if(json2==null){
                                    handler2.post(new Runnable(){
                                        public void run(){
                                            Toast.makeText(getActivity(),
                                                    getActivity().getString(R.string.place_not_found),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } else {
                                    handler2.post(new Runnable(){
                                        public void run(){
                                            try {
                                                updatetemp(json2.getJSONArray("list"));
                                                updatepressure(json2.getJSONArray("list"));
                                                updatehumidity(json2.getJSONArray("list"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
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


    public void updatetemp(JSONArray jsonObject) {

        List<Integer> temp = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for(int i = 0 ; i < jsonObject.length() ; i++) {
            try{
                temp.add(i,jsonObject.getJSONObject(i).getJSONObject("main").getInt("temp") - 273);
                yVals.add(new Entry(i, temp.get(i)));
            }
            catch(Exception e)  {

            }
        }
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);

        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);
        LineData data = new LineData(set1);


        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        chart.animateX(2500);
        chart.setDescription("Temperature Data");
        chart.setData(data);

    }
    public void updatepressure(JSONArray jsonObject) {

        List<Integer> temp = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for(int i = 0 ; i < jsonObject.length() ; i++) {
            try{
                temp.add(i,jsonObject.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                yVals.add(new Entry(i, temp.get(i)));
            }
            catch(Exception e)  {

            }
        }
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 3");
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);

        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);
        LineData data = new LineData(set1);


        chart1.setDrawGridBackground(false);
        chart1.setTouchEnabled(true);

        chart1.setDragEnabled(true);
        chart1.setScaleEnabled(true);
        chart1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        chart1.animateX(2500);
        chart1.setDescription("Pressure Data");
        chart1.setData(data);

    }
    public void updatehumidity(JSONArray jsonObject) {

        List<Integer> temp = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for(int i = 0 ; i < jsonObject.length() ; i++) {
            try{
                temp.add(i,jsonObject.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                yVals.add(new Entry(i, temp.get(i)));
            }
            catch(Exception e)  {

            }
        }
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 3");
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);

        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);
        LineData data = new LineData(set1);


        chart2.setDrawGridBackground(false);
        chart2.setTouchEnabled(true);

        chart2.setDragEnabled(true);
        chart2.setScaleEnabled(true);
        chart2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        chart2.animateX(2500);
        chart2.setDescription("Humidity Data");
        chart2.setData(data);

    }

}

