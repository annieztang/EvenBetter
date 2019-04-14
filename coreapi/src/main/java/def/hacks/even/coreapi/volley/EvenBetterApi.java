package def.hacks.even.coreapi.volley;

import com.google.gson.Gson;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import def.hacks.even.coreapi.EvenRequest;
import def.hacks.even.coreapi.LeadResponse;

public class EvenBetterApi {
    public static final String TAG = EvenBetterApi.class.getSimpleName();

    private static final String URL_LEAD = "https://api.evenfinancial.com/leads/rateTables";
    private static final String AUTH = "Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2";
    private static final String URL_RATE_TABLES = "https://api.evenfinancial.com/originator/rateTables/{UUID}";

    public interface ResponseManager extends Response.ErrorListener, Response.Listener<String> {

    }

    public static void postLeads(Context context, final Gson gson, final EvenRequest request, ResponseManager responseManager) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LEAD, responseManager, responseManager) {
            @Override
            public byte[] getBody() {
                String body = gson.toJson(request);
                Log.i("MainActivity", "request : " + body.toString());
                //todo replace this with our stuff
                return body.getBytes();
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

    public static void getRateTables(Context context, LeadResponse leadResponse, ResponseManager responseManager) {
        String uuid = leadResponse.uuid;

        Log.d(TAG, "Making request to url : " + getRatesUrl(uuid));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getRatesUrl(uuid), responseManager, responseManager) {
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

    private static String getRatesUrl(String uuid) {
        return URL_RATE_TABLES.replace("{UUID}", uuid);
    }
}
