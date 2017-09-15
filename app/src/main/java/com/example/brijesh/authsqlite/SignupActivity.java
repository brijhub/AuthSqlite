package com.example.brijesh.authsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText uName,pass,cPass,number;
    private Button register;
    String un,p,cp,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uName=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        cPass=(EditText)findViewById(R.id.c_password);
        number=(EditText)findViewById(R.id.number);
        register=(Button) findViewById(R.id.signup);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                un=uName.getText().toString();
                p=pass.getText().toString();
                cp=cPass.getText().toString();
                no=number.getText().toString();

                System.out.println("valuesPrint"+un);
                System.out.println("valuesPrint"+p);
                System.out.println("valuesPrint"+cp);
                System.out.println("valuesPrint"+no);

                if (un.trim().length()==0 || p.trim().length()==0 || cp.trim().length()==0 || no.trim().length()==0){
                    Toast.makeText(SignupActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!p.equals(cp))
                    {
                        Toast.makeText(SignupActivity.this, "Password & Confirm Password should match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        UserPojo userPojo=new UserPojo();
                        userPojo.setUser_name(un);
                        userPojo.setPassword(p);
                        userPojo.setNo(no);

                        DatabaseHandler databaseHandler=new DatabaseHandler(SignupActivity.this);
                        databaseHandler.insertValues(userPojo);
                        finish();
                    }

                }
            }
        });


    }
}
