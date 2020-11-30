package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.consagrado.consagradoapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText senha;
    private ImageView visibilidadeSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciaComponentes();
    }

    public void openOptions(View view){
        Intent it = new Intent(this, OptionsActivity.class);
        startActivity(it);
    }

    public void iniciaComponentes(){
        senha               = findViewById(R.id.inputLoginSenha);
        visibilidadeSenha   = findViewById(R.id.imgVisivel);

        visibilidadeSenha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imgVisivel:
                if(visibilidadeSenha.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_visibility).getConstantState())){
                    senha.setTransformationMethod(null);
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility);
                    senha.setTransformationMethod(new PasswordTransformationMethod());

                }
                senha.setSelection(senha.getText().length());
                break;
        }
    }
}