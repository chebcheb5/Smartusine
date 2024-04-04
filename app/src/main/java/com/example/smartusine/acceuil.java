package com.example.smartusine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class acceuil extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CardView profil, charts, notifications, logout;
    private Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);
        profil = findViewById(R.id.Cprofile);
        charts = findViewById(R.id.Ccharts);
        notifications = findViewById(R.id.Cnotifications);
        logout = findViewById(R.id.Clogout);
        btnlogout = findViewById(R.id.btnlogout);
        profil.setOnClickListener(v -> {
            startActivity(new Intent(acceuil.this, profil.class));
        });
        charts.setOnClickListener(v -> {
            startActivity(new Intent(acceuil.this, charts.class));
        });
        notifications.setOnClickListener(v -> {
            startActivity(new Intent(acceuil.this, NOTIFICATIONS.class));
        });
        btnlogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(acceuil.this, signIn.class));
            finish(); // Termine l'activité actuelle pour empêcher l'utilisateur de revenir en arrière
        });
    }
}