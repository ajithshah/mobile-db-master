package com.example.mobiledb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirm_password;
    Button btnreg;
    DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DB=new DBhelper(this);

        //declaration
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm_password);
        btnreg=findViewById(R.id.btnreg);
        //declaration



        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass=password.getText().toString();
                String repass=confirm_password.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity2.this, "Enter details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity2.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity2.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity2.this, "User name already exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity2.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
