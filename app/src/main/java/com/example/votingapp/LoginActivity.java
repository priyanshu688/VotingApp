package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.votingapp.model.Participant;
import com.example.votingapp.model.Voter;
import com.example.votingapp.utility.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
  private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        radioGroup = findViewById(R.id.rg);
        com.google.android.gms.common.SignInButton signInButton = findViewById(R.id.sign_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = ((SingleTask) getApplication()).getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Util.showError(LoginActivity.this,e.toString());
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        ((SingleTask) getApplication()).getFirebaseAuth().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // real time database object store

                    // get role from radio button
                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String role = radioButton.getText().toString();
                    if (role.equalsIgnoreCase("voter")) {

                        Voter voter = new Voter();
                        voter.setRole(role);
                        voter.setUid(((SingleTask) getApplication()).getFirebaseAuth().getUid());

                        Log.e("error", voter.getUid());

                        ((SingleTask) getApplication()).getDatabaseReference().child(voter.getUid()).setValue(voter).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent in = new Intent(LoginActivity.this, VoterHomePage.class);
                                    startActivity(in);
                                    finish();
                                }
                            }
                        });

                    } else {

                        Participant participant = new Participant();
                        participant.setRole(role);
                        participant.setUid(((SingleTask) getApplication()).getFirebaseAuth().getUid());

                        Log.e("error", participant.getUid());

                        ((SingleTask) getApplication()).getDatabaseReference().child(participant.getUid()).setValue(participant).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent in = new Intent(LoginActivity.this, ParticipantHomePage.class);
                                    startActivity(in);
                                    finish();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}