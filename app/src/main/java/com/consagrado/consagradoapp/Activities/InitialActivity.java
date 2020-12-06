package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.consagrado.consagradoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnInitialLogin);
        btnRegister = findViewById(R.id.btnInitialRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void openRegister(){
        Intent it = new Intent(this, RegisterActivity.class);
        startActivity(it);
    }

    private void openLogin(){
        if(verifyLoggeduser()){
            startActivity(new Intent(this, ChoiceActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    public boolean verifyLoggeduser(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInitialLogin:
                openLogin();
                break;
            case R.id.btnInitialRegister:
                openRegister();
                break;
        }
    }
}