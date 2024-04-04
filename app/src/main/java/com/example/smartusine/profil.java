package com.example.smartusine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;


import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        btnsave=findViewById(R.id.btnsave);
        showUserData();




        btnsave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editProfile();
        }
    });
}

    private void showUserData() {
        // Récupérer l'utilisateur actuellement connecté
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // Récupérer l'ID de l'utilisateur
            String userId = currentUser.getUid();

            // Référence à la base de données Firebase
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

            // Ajouter un auditeur pour récupérer les données de l'utilisateur
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Vérifier si les données existent
                    if (dataSnapshot.exists()) {
                        // Récupérer les données de l'utilisateur
                        String fullName = dataSnapshot.child("fullName").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String cin = dataSnapshot.child("cin").getValue(String.class);
                        String tel = dataSnapshot.child("tel").getValue(String.class);
                        String password = dataSnapshot.child("password").getValue(String.class);

                        // Afficher les données dans les TextViews
                        profilename.setText(fullName);
                        profileemail.setText(email);
                        profilecin.setText(cin);
                        profilephone.setText(tel);
                        profilepassword.setText(password);
                        nameview.setText(fullName);
                        emailview.setText(email);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Gérer l'erreur
                }
            });
        }
    }

    private void editProfile() {
        Intent intent = new Intent(profil.this, edit_profile.class);
        intent.putExtra("fullName", profilename.getText().toString());
        intent.putExtra("email", profileemail.getText().toString());
        intent.putExtra("cin", profilecin.getText().toString());
        intent.putExtra("tel", profilephone.getText().toString());
        intent.putExtra("password", profilepassword.getText().toString());
        startActivity(intent);
    }
}