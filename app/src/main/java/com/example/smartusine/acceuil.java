package com.example.smartusine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class acceuil extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CardView profil , charts , notifications , logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);
        profil=findViewById(R.id.Cprofile);
        charts=findViewById(R.id.Ccharts);
        notifications=findViewById(R.id.Cnotifications);
        logout=findViewById(R.id.Clogout);
       profil.setOnClickListener(v->{ startActivity(new Intent(acceuil.this,profil.class));});
       charts.setOnClickListener(v->{startActivity(new Intent(acceuil.this,charts.class));});
       notifications.setOnClickListener(v -> {startActivity(new Intent(acceuil.this,NOTIFICATIONS.class));});
        logout.setOnClickListener(v -> {
            startActivity(new Intent(acceuil.this, signIn.class));});

    }
}
