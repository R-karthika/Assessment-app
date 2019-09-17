package com.example.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUp extends AppCompatActivity {
    EditText user_edt,password_edt,Email_edt;
    Button click;
    String user_str;
    String password_str;
    String Email_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        user_edt = findViewById(R.id.name_edt);
        password_edt = findViewById(R.id.password_edt);
        Email_edt = findViewById(R.id.Email_edt);
        click = findViewById(R.id.signup_bt);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_str = user_edt.getText().toString().trim();
                password_str = password_edt.getText().toString().trim();
                Email_str = Email_edt.getText().toString().trim();
                String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                Pattern pattern = Pattern.compile(PATTERN);
                Matcher matcher = pattern.matcher(password_str);
                if (Patterns.EMAIL_ADDRESS.matcher(Email_str).matches() && matcher.matches()) {
                    StringRequest req = new StringRequest(Request.Method.POST, Constants.BASE_URL + "signup.php",
                            new Response.Listener<String>() {


                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(SignUp.this, "signup successful", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SignUp.this, "signup failed", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("name",user_str);
                            params.put("password",password_str);
                            params.put("Email",Email_str);
                            return params;
                        }
                    };
                    RequestQueue rq = Volley.newRequestQueue(SignUp.this);
                    rq.add(req);
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                    }
                else{
                    Toast.makeText(SignUp.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                }
                }
                });
    }
}
