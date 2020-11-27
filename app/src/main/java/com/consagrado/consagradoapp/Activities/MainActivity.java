package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.consagrado.consagradoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::openInitial, 5000);
    }

    public void openInitial(){
        Intent it = new Intent(this, InitialActivity.class);
        startActivity(it);
        finish();
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}