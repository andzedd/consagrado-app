package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.consagrado.consagradoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::openInitial, 5000);
    }

    public boolean verifyLoggeduser(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            return true;
        }
        return false;
    }

    public void openInitial(){
        if(verifyLoggeduser()){
            startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), InitialActivity.class));
        }
        finish();
    }
}