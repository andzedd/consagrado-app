package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.consagrado.consagradoapp.Controller.UserRegistration;
import com.consagrado.consagradoapp.Helper.MaskEditUtil;
import com.consagrado.consagradoapp.Model.User;
import com.consagrado.consagradoapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtSenha, txtSenhaR, dataNasc, nome, email;
    private ImageView visibilidadeSenha, visibilidadeSenhaR;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniciaComponentes();
    }

    public void iniciaComponentes(){
        visibilidadeSenha = findViewById(R.id.imgVisivelR);
        visibilidadeSenhaR = findViewById(R.id.imgVisivelRC);
        txtSenha = findViewById(R.id.inputSenha);
        txtSenhaR = findViewById(R.id.inputRSenha);
        dataNasc = findViewById(R.id.inputBirth);
        register = findViewById(R.id.btnRegister);
        nome = findViewById(R.id.inputName);
        email = findViewById(R.id.inputEmail);

        dataNasc.addTextChangedListener(MaskEditUtil.insert(MaskEditUtil.DATA, dataNasc));
        visibilidadeSenha.setOnClickListener(this);
        visibilidadeSenhaR.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    public boolean validaCampos(){
        String sNome = nome.getText().toString().trim();
        String sEmail = email.getText().toString().trim();
        String sDataNasc = dataNasc.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();
        String cSenha = txtSenhaR.getText().toString().trim();

        if(sNome.isEmpty()){
            nome.setError("Por favor preencha seu nome!");
            nome.requestFocus();
            return false;
        }
        if(sEmail.isEmpty()){
            email.setError("Por favor preencha seu email!");
            email.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()){
            email.setError("Por favor insira um email válido!");
            email.requestFocus();
            return false;
        }
        if(sDataNasc.isEmpty()){
            dataNasc.setError("Por favor preencha sua data de nascimento!");
            dataNasc.requestFocus();
            return false;
        }
        if(!validaData()){
            return false;
        }
        if(senha.isEmpty()){
            txtSenha.setError("Por favor preencha sua senha!");
            txtSenha.requestFocus();
            return false;
        }
        if(cSenha.isEmpty()){
            txtSenhaR.setError("Por favor confirme sua senha!");
            txtSenhaR.requestFocus();
            return false;
        }

        if(!txtSenha.getText().toString().equals(txtSenhaR.getText().toString())){
            txtSenhaR.setError("Senhas diferem!");
            txtSenhaR.setText("");
            txtSenhaR.requestFocus();
            return false;
        }

        if(senha.length() < 6){
            txtSenha.setError("A senha precisa ter ao menos 6 caracteres!");
            txtSenha.requestFocus();
            return false;
        }

        User user = new User(sNome, sEmail, senha, sDataNasc);
        UserRegistration uR = new UserRegistration();
        uR.register(user, this);
        return true;
    }

    public boolean validaData(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat yf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mf = new SimpleDateFormat("MM");

        final Integer year = Integer.parseInt(yf.format(c));

        String nascimento           = dataNasc.getText().toString();
        Integer dia                 = Integer.parseInt(nascimento.substring(0,2));
        Integer mes                 = Integer.parseInt(nascimento.substring(3,5));
        Integer ano                 = Integer.parseInt(nascimento.substring(nascimento.length() - 4));

        if(dia <= 0 || dia > 31){
            dataNasc.setText("");
            dataNasc.setError("Dia inválido!");
            dataNasc.requestFocus();
            return false;
        } else if(mes <= 0 || mes > 12){
            dataNasc.setText("");
            dataNasc.setError("Mês inválido!");
            dataNasc.requestFocus();
            return false;
        } else if (year - ano < 18){
            dataNasc.setText("");
            dataNasc.setError("Você precisa ter pelo menos 18 anos para se cadastrar!");
            dataNasc.requestFocus();
            return false;
        }

        return true;
    }

    public void limpaCampos(){
        txtSenha.setText("");
        txtSenhaR.setText("");
        dataNasc.setText("");
        nome.setText("");
        email.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgVisivelR:
                if(visibilidadeSenha.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_visibility).getConstantState())){
                    txtSenha.setTransformationMethod(null);
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    visibilidadeSenha.setImageResource(R.drawable.ic_visibility);
                    txtSenha.setTransformationMethod(new PasswordTransformationMethod());
                }
                txtSenha.setSelection(txtSenha.getText().length());
                break;
            case R.id.imgVisivelRC:
                if(visibilidadeSenhaR.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_visibility).getConstantState())){
                    txtSenhaR.setTransformationMethod(null);
                    visibilidadeSenhaR.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    visibilidadeSenhaR.setImageResource(R.drawable.ic_visibility);
                    txtSenhaR.setTransformationMethod(new PasswordTransformationMethod());
                }
                txtSenhaR.setSelection(txtSenhaR.getText().length());
                break;
            case R.id.btnRegister:
                if(validaCampos()){
                    (new Handler()).postDelayed(this::limpaCampos, 1000);
                    nome.requestFocus();
                }
                break;
        }
    }
}