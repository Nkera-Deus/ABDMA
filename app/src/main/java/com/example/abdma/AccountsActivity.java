package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AccountsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText bName, location, contact, username, password, retypePassword;
    Spinner bType;
    Button signUp, signIn;
    DBHelper1 DB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);


        bName = findViewById(R.id.edt1);

        location = findViewById(R.id.edt3);
        contact = findViewById(R.id.edt4);
        username = findViewById(R.id.edt5);
        password = findViewById(R.id.edt6);
        retypePassword = findViewById(R.id.edt7);

        DB1 = new DBHelper1(this);

        signUp = findViewById(R.id.btn1);
        signIn = findViewById(R.id.btn2);


        //the dropdown menu
        bType = findViewById(R.id.spinnertype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.business_type, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bType.setAdapter(adapter);
        bType.setOnItemSelectedListener(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bNameTXT = bName.getText().toString();
                String bTypeTXT = bType.getSelectedItem().toString();
                String locationTXT = location.getText().toString();
                String contactTXT = contact.getText().toString();
                String userNameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                String retypePasswordTXT = retypePassword.getText().toString();
                if(bNameTXT.equals("")||bTypeTXT.equals("")||locationTXT.equals("")||contactTXT.equals("")||userNameTXT.equals("")||passwordTXT.equals("")||retypePasswordTXT.equals(""))
                    Toast.makeText(AccountsActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    if(passwordTXT.equals(retypePasswordTXT)){
                        Boolean checkuser =DB1.checkusername(userNameTXT);
                        if(checkuser==false){
                            Boolean insert = DB1.insertUserData(bNameTXT, bTypeTXT,locationTXT, contactTXT, userNameTXT,passwordTXT);
                         if(insert==true){
                             Toast.makeText(AccountsActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                             startActivity(intent);
                             bName.setText("");
                             location.setText("");
                             contact.setText("");
                             username.setText("");
                             password.setText("");
                             retypePassword.setText("");
                         }else{
                             Toast.makeText(AccountsActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                         }
                        }else{
                            Toast.makeText(AccountsActivity.this, "User already exists, Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AccountsActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        signIn.setOnClickListener(view -> {
            Intent intent = new Intent(AccountsActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}