package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;

public class LoginActivity extends AppCompatActivity {


    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginbtn;
    private Button signUpBtn;
    private int loginAttemps = 5;
    private TextView loginAttempsTextView;
    private String TAG = NewListActivity.class.getSimpleName();
    SessionManager sessionManager;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginbtn = findViewById(R.id.loginbtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        loginAttempsTextView = findViewById(R.id.loginAttempsTextView);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(userNameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(i);
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("")) && (userPassword.equals(""))){

            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);

        }else{

            loginAttemps--;
            loginAttempsTextView.setText("Incorrect User Name or Password" + "\n" +
                    "Login Attemps Left: " + loginAttemps
                    + "\n" + userName + " " + userPassword);

            if (loginAttemps == 0){
                loginbtn.setEnabled(false);

            }
        }
    }

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Email", userNameEditText.getText().toString());
        params.put("Password", passwordEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) { //TODO Use this same method in lists to update correctly and in bulletin!
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Success");
                            //TODO use Jake's validate with success are argument. Change to one parameter.
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Email", userNameEditText.getText().toString());
                params.put("Password", passwordEditText.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}