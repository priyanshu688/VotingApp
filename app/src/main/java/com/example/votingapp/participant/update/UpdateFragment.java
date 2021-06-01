package com.example.votingapp.participant.update;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.votingapp.LoginActivity;
import com.example.votingapp.R;
import com.example.votingapp.SingleTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class UpdateFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.participant_fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.logout);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SingleTask)getActivity().getApplication()).getGoogleSignInClient().signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()) {
                           ((SingleTask) getActivity().getApplication()).getFirebaseAuth().signOut();
                           startActivity(new Intent(getActivity(), LoginActivity.class));
                           getActivity().finish();
                       }
                    }
                });
            }
        });
    }
}