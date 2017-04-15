package com.example.x15011071.audioacousticassistant_app;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Colin on 11/04/2017.
 */

public class LoginRequest extends StringRequest {

    // This is where the php files are
    private static final String LOGIN_REQUEST_URL = "http://audiotronics.000webhostapp.com/Login.php";

    //This creates the map that we use to send data to the php file using the method below
    private Map<String, String> params;

    //This is the login request that is being sent to the php file using POST
    //Respinse.Listener is looking to recieve the data from the LoginActivity.java class
    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);

        // this is using the private map that was called up above to send the data to the php file.
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
