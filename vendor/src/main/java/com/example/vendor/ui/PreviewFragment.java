package com.example.vendor.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vendor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {


    private String title;
    private int page;

    public PreviewFragment() {
        // Required empty public constructor
    }

    public static PreviewFragment newInstance(int page, String title){
        PreviewFragment previewFragment = new PreviewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        args.putString("someTitle", title);
        previewFragment.setArguments(args);
        return previewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        page = getArguments().getInt("someInt",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preview, container, false);
    }

}
