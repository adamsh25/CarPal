package com.BooYa.CarPal;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.HttpResponseException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beeri on 29/10/2014.
 */
public class UsersAuthenticator {
    private String _url;

    public UsersAuthenticator(String url) {

        _url = url;
    }

    public void Authenticate(String email, String password) throws IOException {
        Map<String, String> data = new HashMap<String, String>(2);
        data.put("email", email);
        data.put("password", password);

        ServerCommunicator communicator = new ServerCommunicator(_url);
        communicator.PostJson(data);
    }
}
