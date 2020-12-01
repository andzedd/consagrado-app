package com.consagrado.consagradoapp.DAO;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.consagrado.consagradoapp.Activities.RegisterActivity;
import com.consagrado.consagradoapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Collection;

public class UserDAO implements DAO<User> {
    private User user;
    private int result = 0;
    private FirebaseAuth mAuth;

    @Override
    public boolean insert(User data) throws ClassNotFoundException {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(data.getEmail(), data.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        result = 1;
                                    } else {
                                        result = 0;
                                    }
                                }
                            });
                        } else {
                            result = 0;
                        }
                    }
                });
        return (result == 1);
    }

    @Override
    public boolean alter(User data) throws ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(User data) throws ClassNotFoundException {
        return false;
    }

    @Override
    public boolean get(User data) throws ClassNotFoundException {
        return false;
    }

    @Override
    public Collection<User> list(String rule) throws ClassNotFoundException {
        return null;
    }
}
