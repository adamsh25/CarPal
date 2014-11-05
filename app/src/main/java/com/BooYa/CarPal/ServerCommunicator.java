package com.BooYa.CarPal;

import android.util.Log;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.*;

public class ServerCommunicator {
    private String _url;
    private final String TAG = ServerCommunicator.class.getName();

    public ServerCommunicator(String url) {
        _url = url;
    }

    public String PostJson(JSONObject json) throws IOException {
        return PostJson(json.toString());
    }

    public String PostJson(String jsonString) throws IOException {
        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(_url);

        // 3. set json to StringEntity
        StringEntity se = new StringEntity(jsonString);

        // 4. set httpPost Entity
        httpPost.setEntity(se);

        // 5. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        // 6. Execute POST request to the given URL and parse result
        String responseBody = httpclient.execute(httpPost, responseHandler);

        return responseBody;
    }

    public String PostJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return PostJson(json);
    }
}
