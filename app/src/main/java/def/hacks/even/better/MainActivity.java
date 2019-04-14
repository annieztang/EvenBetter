package def.hacks.even.better;

import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import def.hacks.even.api.EvenRequest;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        EvenRequest request = EvenRequest.temp(gson);

        EvenBetterApi.postLeads(getApplicationContext(), gson, request, this, this);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("MainActivity", "fuck : " + error + ", response : " + error.networkResponse.statusCode);
    }

    @Override
    public void onResponse(String response) {
        Log.i("MainActivity", "We got a response! : " + response);
    }
}
