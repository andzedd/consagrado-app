package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.consagrado.consagradoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView sair;
    private ConstraintLayout cl;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
        mAuth = FirebaseAuth.getInstance();
        sair = findViewById(R.id.txtSair);
        cl = findViewById(R.id.btnCart);
        sair.setOnClickListener(this);
        cl.setOnClickListener(this);
    }

    private void logout(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(OptionsActivity.this, LoginActivity.class));
    }

    private void openCart(){
        Intent it = new Intent(this, CartActivity.class);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtSair:
                logout();
                break;
            case R.id.btnCart:
                openCart();
                break;
        }
    }
}