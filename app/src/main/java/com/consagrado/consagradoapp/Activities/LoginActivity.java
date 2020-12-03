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
import android.widget.Toast;

import com.consagrado.consagradoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextSenha;
    private ImageView visibilidadeSenha;
    private FirebaseAuth mAuth;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciaComponentes();
        verifyLoggeduser();
    }

    private void verifyLoggeduser(){
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
            finish();
        }
    }

    private void userLogin(){
        if(validaCampos()){
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário não encontrado", Toast.LENGTH_LONG).show();
                        editTextSenha.setText("");
                        editTextEmail.requestFocus();
                    }
                }
            });
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
        editTextEmail       = findViewById(R.id.inputLoginEmail);
        editTextSenha       = findViewById(R.id.inputLoginSenha);
        visibilidadeSenha   = findViewById(R.id.imgVisivel);
        btnLogin            = findViewById(R.id.btnLogin);

        visibilidadeSenha.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
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
                userLogin();
        }
    }
}