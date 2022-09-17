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

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner bType;
    Button login, forgotPassword;
    EditText username, password, bName;
    DBHelper1 DB1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        forgotPassword = findViewById(R.id.btnForgotPassword);




        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        bName = findViewById(R.id.edtBName);


        DB1 = new DBHelper1(this);



        //DROPDOWN FOR LOGIN
        bType = findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.business_type, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bType.setAdapter(adapter);
        bType.setOnItemSelectedListener(this);




//....................................................................................
        // login process
        login.setOnClickListener(view -> {
            String bNameTXT = bName.getText().toString();
            String bTypeTXT = bType.getSelectedItem().toString();
            String usernameTXT = username.getText().toString();
            String passwordTXT = password.getText().toString();

            if(bNameTXT.equals("")||bTypeTXT.equals("")||usernameTXT.equals("")||passwordTXT.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB1.checkusernamepassword(usernameTXT, passwordTXT);
                Boolean checkbName =DB1.checkbusinessname(bNameTXT);
                //Boolean checkbType =DB1.checkbType(bTypeTXT);
                if(checkuserpass && bTypeTXT.equals("Factory") && checkbName){
                    // bTypeTXT.equals("Factory")
                    Toast.makeText(LoginActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Factory.class);
                    intent.putExtra("17.5",bName.getText().toString());
                    startActivity(intent);
                    bName.setText("");
                    username.setText("");
                    password.setText("");

                }else if(checkuserpass && bTypeTXT.equals("Distributor") && checkbName){

                        //bTypeTXT.equals("Distributor")
                        Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Distributor.class);
                        intent.putExtra("17.5",bName.getText().toString());
                        startActivity(intent);
                        bName.setText("");
                        username.setText("");
                        password.setText("");
                }else if(checkuserpass && bTypeTXT.equals("Local Supplier") && checkbName){

                        //bTypeTXT.equals("Local Supplier")
                        Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LocalSupplier.class);
                        intent.putExtra("17.5",bName.getText().toString());
                        startActivity(intent);
                        bName.setText("");
                        username.setText("");
                        password.setText("");

                }else if(checkuserpass && bTypeTXT.equals("Retailer") && checkbName){

                        //bTypeTXT.equals("Retailer")
                        Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Retailer.class);
                        intent.putExtra("17.5",bName.getText().toString());
                        startActivity(intent);
                        bName.setText("");
                        username.setText("");
                        password.setText("");
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
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