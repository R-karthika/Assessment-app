package com.example.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
EditText username_edt,password_edt;
Button submit;
TextView user_txt;
String username_str;
String pass_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_edt = findViewById(R.id.username);
        password_edt = findViewById(R.id.password);
        submit = findViewById(R.id.button);
            TextView user_txt= (TextView) findViewById(R.id.text);
            user_txt.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent act = new Intent(MainActivity.this, SignUp.class);
                                          startActivity(act);
                                      }
                                  });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        username_str = username_edt.getText().toString().trim();
                        pass_str = password_edt.getText().toString().trim();
                        String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                        Pattern pattern = Pattern.compile(PATTERN);
                        Matcher matcher = pattern.matcher(pass_str);
                        if (matcher.matches()) {
                            StringRequest sq = new StringRequest(Request.Method.POST, Constants.BASE_URL + "connect.php",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String ServerResponse) {
                                           // Response.Listener<JSONObject> rl = new Response.Listener<JSONObject>() {
                                              //  @Override
                                              //  public void onResponse( response) {

                                                     if(ServerResponse.equalsIgnoreCase("data matched")) {
                                                         Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();
//                                                        JSONArray jsonArray = response.getJSONArray("output");
//                                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
//                                                        String uname = jsonObject.getString("name");
//                                                        Intent i = new Intent(MainActivity.this, Test.class);
//                                                        i.putExtra("name", uname);
//                                                        startActivity(i);
//                                                        Log.e("success", response.toString());
//                                                    } catch (JSONException e) {
//                                                        e.printStackTrace();
//                                                    }
                                                         finish();
                                                         Intent i = new Intent(MainActivity.this, Test.class);
                                                         startActivity(i);
                                                     } else {
                                                         Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();
                                                }

                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(MainActivity.this, "error",Toast.LENGTH_SHORT).show();
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("username", username_str);
                                    params.put("password", pass_str);
                                    return params;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                            requestQueue.add(sq);

                           // Intent i = new Intent(MainActivity.this, Test.class);
                            //startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

}
}
