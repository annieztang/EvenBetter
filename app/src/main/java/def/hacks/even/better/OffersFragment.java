package def.hacks.even.better;

import com.google.gson.Gson;

import com.android.volley.VolleyError;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import def.hacks.even.api.EvenRequest;
import def.hacks.even.api.LeadResponse;
import def.hacks.even.api.RateTableResponse;
import def.hacks.even.better.volley.EvenBetterApi;

/**
 * Created by William Zulueta on 4/13/19.
 */
public class OffersFragment extends Fragment {
    public static final String TAG = OffersFragment.class.getSimpleName();
    private static final Gson gson = new Gson();

    private ListView listView;
    private ListAdapter listAdapter;

    private RateTableResponse rateTableResponse;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, null);

        EvenRequest request = EvenRequest.temp(gson);

        EvenBetterApi.postLeads(getContext(), gson, request, leadsResponseManager);

        listView = view.findViewById(R.id.offersListView);

        return view;
    }

    public void initList(RateTableResponse response) {
        rateTableResponse = response;
        listAdapter = new ListAdapter(getContext(), rateTableResponse);
        listView.setAdapter(listAdapter);
    }

    private static class ListAdapter implements android.widget.ListAdapter  {
        private RateTableResponse response;
        private Context context;

        public ListAdapter(Context context, RateTableResponse response) {
            this.context = context;
            this.response = response;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return response.loanOffers.length;
        }

        @Override
        public Object getItem(int i) {
            return response.loanOffers[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            OfferView offerView;
            if (view == null) {
                offerView = new OfferView(context);
            } else {
                offerView = (OfferView) view;
            }
            offerView.bind(response.loanOffers[i]);
            return offerView;
        }

        @Override
        public int getItemViewType(int i) {
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return response.loanOffers.length == 0;
        }
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
            OffersFragment.this.rateTableResponse = gson.fromJson(response, RateTableResponse.class);
            OffersFragment.this.initList(rateTableResponse);
            Toast.makeText(getContext(), "Got " + rateTableResponse.loanOffers.length + " responses!", Toast.LENGTH_SHORT).show();
            // todo make offer object
        }
    };
}
