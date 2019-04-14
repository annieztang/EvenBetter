package def.hacks.even.better;

import com.google.gson.Gson;

import com.android.volley.VolleyError;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import def.hacks.even.api.EvenRequest;
import def.hacks.even.api.LeadResponse;

public class MainActivity extends AppCompatActivity  {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EvenRequest request = EvenRequest.temp(gson);

        EvenBetterApi.postLeads(getApplicationContext(), gson, request, leadsResponseManager);
    }


    private EvenBetterApi.ResponseManager leadsResponseManager = new EvenBetterApi.ResponseManager() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "lead.onErrorResponse : " + error);
        }

        @Override
        public void onResponse(String response) {
            LeadResponse leadResponse = gson.fromJson(response, LeadResponse.class);
            // call get...
            EvenBetterApi.getRateTables(getApplicationContext(), leadResponse, rateTablesResponseManager);
        }
    };

    private EvenBetterApi.ResponseManager rateTablesResponseManager = new EvenBetterApi.ResponseManager() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "rateTables.onErrorResponse : " + error);
        }

        @Override
        public void onResponse(String response) {
            Log.i(TAG, "rateTables response : " + response);
            // todo make offer object
        }
    };
}
