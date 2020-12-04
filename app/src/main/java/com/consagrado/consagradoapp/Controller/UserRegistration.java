package com.consagrado.consagradoapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.consagrado.consagradoapp.Activities.CartActivity;
import com.consagrado.consagradoapp.Activities.LoginActivity;
import com.consagrado.consagradoapp.Activities.OptionsActivity;
import com.consagrado.consagradoapp.Activities.RegisterActivity;
import com.consagrado.consagradoapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration {
    private FirebaseAuth mAuth;

    public void register(User user, Context context){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User newUser = new User(user.getNome(), user.getEmail(), user.getDataNasc());
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(context, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(context, OptionsActivity.class);
                                        ((Activity) context).finish();
                                        context.startActivity(it);
                                    } else {
                                        Toast.makeText(context, "Erro ao cadastrar usuário! Tente novamente.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            String erroExcecao = "";
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e ){
                                erroExcecao = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e ){
                                erroExcecao = "Email inválido!";
                            } catch (FirebaseAuthUserCollisionException e){
                                erroExcecao = "Este email já está cadastrado!";
                            } catch (Exception e){
                                erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(context,
                                    "Erro: " + erroExcecao,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
