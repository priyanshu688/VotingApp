package com.example.votingapp.participant.enroll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.votingapp.R;


public class EnrollFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
               Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_enroll, container, false);
    }
}