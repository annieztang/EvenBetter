package def.hacks.even.better;

import com.google.gson.Gson;

import com.android.volley.VolleyError;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import def.hacks.even.api.EvenRequest;
import def.hacks.even.api.LeadResponse;
import def.hacks.even.api.RateTableResponse;
import def.hacks.even.better.volley.EvenBetterApi;

/**
 * Created by William Zulueta on 4/13/19.
 */
public class OffersFragment extends Fragment {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final Gson gson = new Gson();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, null);

        EvenRequest request = EvenRequest.temp(gson);

        EvenBetterApi.postLeads(getContext(), gson, request, leadsResponseManager);
        return view;
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
            EvenBetterApi.getRateTables(getContext(), leadResponse, rateTablesResponseManager);
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
            RateTableResponse rateTableResponse = gson.fromJson(response, RateTableResponse.class);
            // todo make offer object
        }
    };
}
