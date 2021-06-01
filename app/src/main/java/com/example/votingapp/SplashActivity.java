package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.votingapp.model.Participant;
import com.example.votingapp.model.Voter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = ((SingleTask)getApplication()).getFirebaseAuth().getCurrentUser();
        if(user != null) {
            ((SingleTask)getApplication()).getDatabaseReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(SplashActivity.this, dataSnapshot.getValue()+"" , Toast.LENGTH_SHORT).show();
                    Iterator<DataSnapshot> dd = dataSnapshot.getChildren().iterator();
                    while(dd.hasNext()) {
                        dd.next().getValue();
                    }

               /*   try{
                     Participant participant = dataSnapshot.getValue(Participant.class);
                      Log.e("error","participant");
                      Intent in = new Intent(SplashActivity.this, ParticipantHomePage.class);
                      startActivity(in);
                      finish();
                  } catch (ClassCastException e) {
                      Voter voter = dataSnapshot.getValue(Voter.class);
                      Log.e("error","voter");

                  }catch (Exception e) {
                      Log.e("error","please try after some time");
                  }  */

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void goToLoginPage(View view ) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}