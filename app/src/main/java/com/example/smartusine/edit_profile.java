package com.example.smartusine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_profile extends AppCompatActivity {
EditText editnom ,editemail, editcin , editphone,editpassword ;
   Button btnedit ;
   String fullname ,emailuser, cin,phone,password;
   DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
   reference= FirebaseDatabase.getInstance().getReference("users");
   editnom=findViewById(R.id.editname);
        editemail=findViewById(R.id.editemail);
        editcin=findViewById(R.id.editcin);
        editphone=findViewById(R.id.editphone);
        editpassword=findViewById(R.id.editpassword);
        btnedit=findViewById(R.id.btnedit);
        showData();
        btnedit.setOnClickListener(v -> {
            if (isNameChanged() || isEmailChanged() || isPasswordChanged()) {
                Toast.makeText(edit_profile.this, "Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(edit_profile.this, "No Changes Found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean isNameChanged(){
        if(!fullname.equals(editnom.getText().toString())){
            reference.child(emailuser).child("name").setValue(editnom.getText().toString());
            fullname = editnom.getText().toString();
            return true;
        } else{
            return false;
        }
        }
    public boolean isEmailChanged(){
        if (!emailuser.equals(editnom.getText().toString())){
            reference.child(emailuser).child("email").setValue(editemail.getText().toString());
           emailuser = editemail.getText().toString();
            return true;
        } else{
            return false;
        }
    }
    public boolean isPasswordChanged(){
        if (!password.equals(editpassword.getText().toString())){
            reference.child(emailuser).child("password").setValue(editpassword.getText().toString());
            password = editpassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();

        fullname = intent.getStringExtra("name");

        emailuser = intent.getStringExtra("email");
        cin = intent.getStringExtra("cin");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");

        editnom.setText(fullname);
        editemail.setText(emailuser);
        editcin.setText(cin);
        editphone.setText(phone);
        editpassword.setText(password);
    }
}