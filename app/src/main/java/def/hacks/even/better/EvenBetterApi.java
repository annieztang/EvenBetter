package def.hacks.even.better;

import com.google.gson.Gson;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import def.hacks.even.api.EvenRequest;

public class EvenBetterApi {
    private static final String URL_LEAD = "https://api.evenfinancial.com/leads/rateTables";
    private static final String AUTH = "Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2";

    public static void postLeads(Context context, final Gson gson, final EvenRequest request, Response.Listener<String> listener, Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LEAD, listener, error) {
            @Override
            public byte[] getBody() {
                String body = gson.toJson(request);
                Log.i("MainActivity", "request : " + body.toString());
                //todo replace this with our stuff
                return EvenRequest.TEMP.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/vnd.evenfinancial.v1+json");
                params.put("Authorization", AUTH);
                return params;
            }
        };
        VolleyLoader.getInstance(context).addToRequestQueue(stringRequest);
    }
}
