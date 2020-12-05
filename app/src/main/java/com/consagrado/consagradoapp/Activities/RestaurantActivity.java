package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.consagrado.consagradoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout cl;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
        mAuth = FirebaseAuth.getInstance();
        cl = findViewById(R.id.btnCart);
        cl.setOnClickListener(this);
    }

    private void openCart(){
        Intent it = new Intent(this, CartActivity.class);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCart:
                openCart();
                break;
        }
    }
}