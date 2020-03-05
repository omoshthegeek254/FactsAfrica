package com.example.factsafrica.ui.models;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.factsafrica.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComputersFragment extends Fragment {


    public ComputersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_computers, container, false);
    }

}
