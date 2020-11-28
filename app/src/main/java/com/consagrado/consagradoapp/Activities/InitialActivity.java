package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import com.consagrado.consagradoapp.R;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
    }

    public void openRegister(View view){
        Intent it = new Intent(this, RegisterActivity.class);
        startActivity(it);
    }

    public void openLogin(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}