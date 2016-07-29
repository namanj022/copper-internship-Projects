package com.survey.worklake.weather;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {
    ListView forecastList;
    private JSONObject json1;
    Handler handler1;

    public ForecastFragment() {
        handler1 = new Handler();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forecast, container, false);


        forecastList = (ListView) v.findViewById(R.id.forecastList);
        updateForecastData(new CityPreference(getActivity()).getCity());

        return v;
    }

    private void updateForecastData(final String city) {
        new Thread() {
            public void run() {


                String url = "http://api.openweathermap.org/data/2.5/forecast?q=";

                JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.GET,
                        url + city, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(final JSONObject response) {
                                json1 = response;
                                if (json1 == null) {
                                    handler1.post(new Runnable() {
                                        public void run() {
                                            Toast.makeText(getActivity(),
                                                    getActivity().getString(R.string.place_not_found),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } else {
                                    handler1.post(new Runnable() {
                                        public void run() {
                                            Log.e("ghf", response.toString());
                                            try {
                                                JSONArray jsonArray = null;
                                                jsonArray = json1.getJSONArray("list");
                                                CustomListAdapter details = new CustomListAdapter(getActivity(), jsonArray);
                                                forecastList.setAdapter(details);
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

                Volley.newRequestQueue(getActivity()).add(jsonObjReq1);

            }
        }.start();
    }

}
