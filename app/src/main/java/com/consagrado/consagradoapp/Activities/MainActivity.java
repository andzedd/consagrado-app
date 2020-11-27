package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.consagrado.consagradoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openInitial(View view){
        Intent it = new Intent(this, InitialActivity.class);
        startActivity(it);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}