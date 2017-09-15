package com.example.brijesh.authsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login,signup;
    String login_uName,login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.l_username);
        password=(EditText)findViewById(R.id.l_password);
        login=(Button) findViewById(R.id.login);
        signup=(Button) findViewById(R.id.proceed_to_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_uName=username.getText().toString();
                login_password=password.getText().toString();

                if (login_uName.trim().length()==0 || login_password.trim().length()==0)
                {
                    Toast.makeText(MainActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    DatabaseHandler databaseHandler=new DatabaseHandler(MainActivity.this);
                    String returen_password=databaseHandler.searchPasswordFromDatabase(login_uName);
                    if (returen_password.equals(login_password))
                    {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent dashboard=new Intent(MainActivity.this,DashboardActivity.class);
                        dashboard.putExtra("uname",login_uName);
                        startActivity(dashboard);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(signup);
                //finish();
            }
        });
    }
}
