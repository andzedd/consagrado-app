package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.consagrado.consagradoapp.Controller.UserController;
import com.consagrado.consagradoapp.R;

public class RecoverActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private Button btnEnviar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        iniciaComponentes();
    }

    public void iniciaComponentes(){
        editTextEmail = findViewById(R.id.inputRecuperarSenha);
        btnEnviar = findViewById(R.id.btnEnviarEmail);
        progressBar = findViewById(R.id.progressBarRecover);
        progressBar.setVisibility(View.GONE);
        btnEnviar.setOnClickListener(this);
        editTextEmail.requestFocus();
    }

    public void limpaCampos(){
        editTextEmail.setText("");
        editTextEmail.requestFocus();
        progressBar.setVisibility(View.GONE);
    }

    public boolean validaEmail(){
        String email = editTextEmail.getText().toString().trim();
        if(email.isEmpty()){
            editTextEmail.setError("Por favor preencha o campo de email!");
            editTextEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Por favor digite um email válido!");
            editTextEmail.requestFocus();
            return false;
        }

        UserController userController = new UserController();
        userController.recoverPassword(email, this);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEnviarEmail:
                progressBar.setVisibility(View.VISIBLE);
                if(validaEmail()){
                    (new Handler()).postDelayed(this::limpaCampos, 1500);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
        }
    }
}