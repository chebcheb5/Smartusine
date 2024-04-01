package com.example.smartusine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
    private EditText email;
    private Button btnReset  , btnBack ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btnReset=findViewById(R.id.btnReset);
        btnBack=findViewById(R.id.backToMain);
        email=findViewById(R.id.emailReset);
        firebaseAuth=FirebaseAuth.getInstance();

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(forgetPassword.this,signIn.class));
        });

        btnReset.setOnClickListener(v -> {
            firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(forgetPassword.this,signIn.class));
                        Toast.makeText(forgetPassword.this, "password reset email sent", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        Toast.makeText(forgetPassword.this, "erreur", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

}