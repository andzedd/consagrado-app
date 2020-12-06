package com.consagrado.consagradoapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.consagrado.consagradoapp.Activities.ChoiceActivity;
import com.consagrado.consagradoapp.Activities.RestaurantActivity;
import com.consagrado.consagradoapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

public class UserController {
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
                                        Intent it = new Intent(context, ChoiceActivity.class);
                                        ((Activity) context).finish();
                                        context.startActivity(it);
                                    } else {
                                        Toast.makeText(context, "Erro ao cadastrar usuário! Tente novamente.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            String erro = "";
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e ){
                                erro = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e ){
                                erro = "Email inválido!";
                            } catch (FirebaseAuthUserCollisionException e){
                                erro = "Este email já está cadastrado!";
                            } catch (Exception e){
                                erro = "ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(context,
                                    "Erro: " + erro,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void recoverPassword(String email, Context context){
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Um link de recuperação foi enviado para o email informado!", Toast.LENGTH_LONG).show();
                } else {
                    String erro = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Email inválido!";
                    } catch (FirebaseAuthInvalidUserException e){
                        erro = "Email não registrado!";
                    } catch (Exception e){
                        erro = e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Erro: " + erro, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
