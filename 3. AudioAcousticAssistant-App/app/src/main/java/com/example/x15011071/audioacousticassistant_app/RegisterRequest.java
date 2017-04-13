package com.example.x15011071.audioacousticassistant_app;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Colin on 06/04/2017.
 */

//This allows us to send a register request to the php files on the server, and recieve a string.
public class RegisterRequest extends StringRequest {

    // This is where the php files are
    private static final String REGISTER_REQUEST_URL = "http://audiotronics.000webhostapp.com/Register.php";

    //This creates the map that we use to send data to the php file using the method below
    private Map<String, String> params;

    //This is the register request that is being sent to the php file using POST
    //Respinse.Listener is looking to recieve the data from the RegisterActivity.java class
    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        // this is using the private map that was called up above to send the data to the php file.
        params = new HashMap<>();
        params.put("username", username);
        params.put("name", name);
        params.put("password", password);
        params.put("age", age+"");
    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
