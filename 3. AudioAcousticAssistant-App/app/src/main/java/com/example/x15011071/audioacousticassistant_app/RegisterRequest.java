package com.example.x15011071.audioacousticassistant_app;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/*
* @filename RegisterRequest.java
* @author Colin Allen, Keith Feeney, Patrick Lawlor, Fearghal McMorrow, Cedric Vecchionacce
* @reference YouTube - TonikamiTV/Login Register 6 part series - https://www.youtube.com/watch?v=QxffHgiJ64M
* @date 11 April 2017

 */
//This allows us to send a register request to the php files on the server, and recieve a string.
public class RegisterRequest extends StringRequest {

    // This is where the php files are
    private static final String REGISTER_REQUEST_URL = "http://audiotronics.000webhostapp.com/Register.php";

    //This creates the map that we use to send data to the php file using the method below
    private Map<String, String> params;

    //This is the register request that is being sent to the php file using POST
    //Response.Listener is looking to receive the data from the RegisterActivity.java class
    //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
    public RegisterRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        // this is using the private map that was called up above to send the data to the php file.
        //@reference Yotube - TonikamiTv/Login Register 6 part series & @authors
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}