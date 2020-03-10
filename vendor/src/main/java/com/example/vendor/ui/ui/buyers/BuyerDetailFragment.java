package com.example.vendor.ui.ui.buyers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vendor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyerDetailFragment extends Fragment {


    public BuyerDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //receiving bundle
        Bundle bundle =getArguments();

        if(bundle!= null){
            String name =bundle.getString("name");
            Toast.makeText(getContext(),"name is "+name,Toast.LENGTH_SHORT);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buyer_detail, container, false);
    }

}
