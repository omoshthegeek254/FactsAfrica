package com.example.factsafrica.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.factsafrica.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FurnitureFragment extends Fragment {


    public FurnitureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_furniture, container, false);
    }

}
