package com.example.smartusine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_profile extends AppCompatActivity {
    EditText editnom, editemail, editcin, editphone, editpassword;
    Button btnedit;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");
        editnom = findViewById(R.id.editname);
        editemail = findViewById(R.id.editemail);
        editcin = findViewById(R.id.editcin);
        editphone = findViewById(R.id.editphone);
        editpassword = findViewById(R.id.editpassword);
        btnedit = findViewById(R.id.btnedit);

        showData();

        btnedit.setOnClickListener(v -> {
            updateProfile();
        });
    }

    private void updateProfile() {
        String email = editemail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(edit_profile.this, "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String fullName = editnom.getText().toString();
        String cin = editcin.getText().toString();
        String tel = editphone.getText().toString();
        String password = editpassword.getText().toString();

        // Mettre à jour les données dans la base de données Firebase
        DatabaseReference userRef = reference.child(email);
        userRef.child("fullName").setValue(fullName);
        userRef.child("cin").setValue(cin);
        userRef.child("tel").setValue(tel);
        userRef.child("password").setValue(password);
        // Afficher un message de succès
        Toast.makeText(edit_profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();

        // Revenir à l'activité de profil
        Intent intent = new Intent(edit_profile.this, profil.class);
        startActivity(intent);
        finish(); // Pour fermer cette activité et éviter de revenir en arrière avec le bouton de retour
    }

    private void showData() {
        Intent intent = getIntent();
        if (intent != null) {
            String fullName = intent.getStringExtra("fullName");
            String email = intent.getStringExtra("email");
            String cin = intent.getStringExtra("cin");
            String tel = intent.getStringExtra("tel");
            String password = intent.getStringExtra("password");

            editnom.setText(fullName);
            editemail.setText(email);
            editcin.setText(cin);
            editphone.setText(tel);
            editpassword.setText(password);
        }
    }
}
