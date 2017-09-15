package com.example.brijesh.authsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private TextView user,contact;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user=(TextView)findViewById(R.id.name_of_user);
        contact=(TextView)findViewById(R.id.contact_of_user);

        Intent i=getIntent();
        username=i.getStringExtra("uname");

        user.setText("Welcome "+username+",");
        DatabaseHandler databaseHandler=new DatabaseHandler(DashboardActivity.this);
        String contacts=databaseHandler.getUserData(username);
        contact.setText("Your number "+contacts);

    }
}
