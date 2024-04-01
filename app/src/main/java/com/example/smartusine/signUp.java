package com.example.smartusine;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartusine.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {
private EditText FullName , Cin , Pass , Phone ,Email ;
private TextView GoToSigin ;
private Button buttonSignUp ;
private String fullNameU, emailU, phoneU, cinU, passU;

private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

private FirebaseAuth firebaseAuth;
private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        GoToSigin = findViewById(R.id.goToSignIn);
        GoToSigin.setOnClickListener(v -> {
            startActivity(new Intent(signUp.this,signIn.class));
        });
FullName=findViewById(R.id.signup_name);
Email=findViewById(R.id.signup_email);
Cin=findViewById(R.id.signup_cin);
Phone=findViewById(R.id.signup_phone);
Pass=findViewById(R.id.signup_password);
buttonSignUp=findViewById(R.id.bouttonsignup);
firebaseAuth= FirebaseAuth.getInstance() ;
progressDialog=new ProgressDialog(this);
        buttonSignUp.setOnClickListener(v -> {
            if (validate()) {
                progressDialog.setMessage("Please wait...!!");
                progressDialog.show();
                String emailUser = Email.getText().toString().trim();
                String passwordUser = Pass.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SendEmailverification();


                        } else {
                            Toast.makeText(signUp.this, "Register Failed !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }

        });

    }
    private void SendEmailverification() {
        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if (currentUser != null){
            currentUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        //send user data
                        sendUserData();
                        Toast.makeText(signUp.this, "registration done please check your email", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        startActivity( new Intent(signUp.this,signIn.class));
                    } else{
                        Toast.makeText(signUp.this, "registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData() {
        // Storing user data in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullName", fullNameU);
        editor.putString("email", emailU);
        editor.putString("cin", cinU);
        editor.putString("phone", phoneU);
        editor.apply();

        // Saving user data to Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("users");
        user u1 = new user(fullNameU, emailU, cinU, phoneU, passU);
        myRef.child("" + firebaseAuth.getUid()).setValue(u1);
    }
    private boolean validate() {
        boolean result = false;
        fullNameU = FullName.getText().toString();
        emailU = Email.getText().toString();
        cinU = Cin.getText().toString();
        phoneU = Phone.getText().toString();
       passU = Pass.getText().toString();
        if (fullNameU.isEmpty() || FullName.length()<7){
            FullName.setError("Full Name is invalid!");
        } else if (!isValidEmail(emailU)) {
            Email.setError("Email is invalid!");

        } else if (cinU.isEmpty() || Cin.length() != 8 ) {
            Cin.setError("Cin is invalid!");

        } else if (phoneU.isEmpty() || phoneU.length() != 8 ) {
            Phone.setError("Phone is invalid!");
        } else if (passU.isEmpty() || passU.length()<7) {
            Pass.setError("Password is invalid");
        } else {
            result = true ;
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}







