package com.consagrado.consagradoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consagrado.consagradoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btnEsqueceu;
    private EditText editTextEmail, editTextSenha;
    private ImageView visibilidadeSenha;
    private FirebaseAuth mAuth;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciaComponentes();
    }

    private void userLogin(){
        if(validaCampos()){
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        limpaCampos();
                        finish();
                        startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário não encontrado", Toast.LENGTH_LONG).show();
                        editTextSenha.setText("");
                        editTextEmail.requestFocus();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private boolean validaCampos(){
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Por favor preencha seu email!");
            editTextEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Por favor insira um email válido!");
            editTextEmail.requestFocus();
            return false;
        }
        if(senha.isEmpty()){
            editTextSenha.setError("Por favor preencha sua senha!");
            editTextSenha.requestFocus();
            return false;
        }
        if(senha.length() < 6){
            editTextSenha.setError("A senha precisa ter ao menos 6 caracteres!");
            editTextSenha.requestFocus();
            return false;
        }
        return true;
    }

    private void iniciaComponentes(){
        btnEsqueceu         = findViewById(R.id.txtEsqueceu);
        editTextEmail       = findViewById(R.id.inputLoginEmail);
        editTextSenha       = findViewById(R.id.inputLoginSenha);
        visibilidadeSenha   = findViewById(R.id.imgVisivel);
        btnLogin            = findViewById(R.id.btnLogin);
        progressBar         = findViewById(R.id.progressBarLogin);
        editTextEmail.requestFocus();

        progressBar.setVisibility(View.GONE);

        btnEsqueceu.setOnClickListener(this);
        visibilidadeSenha.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    public void limpaCampos(){
        editTextEmail.setText("");
        editTextSenha.setText("");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imgVisivel:
                if(visibilidadeSenha.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_visibility).getConstantState())){
                    editTextSenha.setTransformationMethod(null);
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility);
                    editTextSenha.setTransformationMethod(new PasswordTransformationMethod());

                }
                editTextSenha.setSelection(editTextSenha.getText().length());
                break;
            case R.id.btnLogin:
                progressBar.setVisibility(View.VISIBLE);
                userLogin();
                break;
            case R.id.txtEsqueceu:
                limpaCampos();
                startActivity(new Intent(this, RecoverActivity.class));
                break;
        }
    }
}