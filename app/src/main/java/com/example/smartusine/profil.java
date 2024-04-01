package com.example.smartusine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;



import android.widget.Button;


import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profil extends AppCompatActivity {
    private TextView nameview , emailview,profilename , profileemail,profilecin,profilephone,profilepassword;


    public Button btnsave ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nameview = findViewById(R.id.profilenom);
        emailview = findViewById(R.id.profilemail);
        profilename = findViewById(R.id.profilename);
        profileemail = findViewById(R.id.profileemail);
        profilecin = findViewById(R.id.profilecin);
        profilephone = findViewById(R.id.profilephone);
        profilepassword = findViewById(R.id.profilepassword);
        btnsave.findViewById(R.id.btnsave);
        showUserData();
        btnsave.setOnClickListener(v -> passUserData());
    }
    private void showUserData() {
        Intent intent= getIntent() ;
        String fullname =intent.getStringExtra("name");
        String emailuser =intent.getStringExtra("Email");
        String cin =intent.getStringExtra("Cin");
        String phone =intent.getStringExtra("telephone");
        String password =intent.getStringExtra("password");
        nameview.setText(fullname);
        emailview.setText(emailuser);
       profilename.setText(fullname);
        profileemail.setText(emailuser);
        profilecin.setText(cin);
       profilephone.setText(phone);
        profilepassword.setText(password);
    }
public void passUserData() {
    String emailuser = profilename.getText().toString().trim();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
    Query checkUserDatabase = reference.orderByChild("username").equalTo(emailuser);
    {
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nameFromDB = snapshot.child(emailuser).child("fullname").getValue(String.class);
                    String emailFromDB = snapshot.child(emailuser).child("email").getValue(String.class);
                    String cinFromDB = snapshot.child(emailuser).child("cin").getValue(String.class);
                    String phoneFromDB = snapshot.child(emailuser).child("phone").getValue(String.class);
                    String passwordFromDB = snapshot.child(emailuser).child("password").getValue(String.class);
                    Intent intent = new Intent(profil.this, edit_profile.class);
                    intent.putExtra("fullname", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("cin", cinFromDB);
                    intent.putExtra("phone", phoneFromDB);
                    intent.putExtra("password", passwordFromDB);
                    startActivity(intent);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }}}
