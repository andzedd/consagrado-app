package com.consagrado.consagradoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.consagrado.consagradoapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtSenha, txtSenhaR;
    private ImageView visibilidadeSenha, visibilidadeSenhaR;

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

        visibilidadeSenha.setOnClickListener(this);
        visibilidadeSenhaR.setOnClickListener(this);
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

        }
    }
}