package com.example.vendor.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.vendor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    private static final String TAG = "AddressFragment";


    //private AddressFragmentListener addressFragmentListener;

    private EditText personName;
    private EditText personEmail;
    private EditText personAddress;
    private FloatingActionButton mFloatingActionButton;

    private String title;
    private int page;


    public AddressFragment() {
        // Required empty public constructor
    }

//    public interface AddressFragmentListener{
//        void onPersonNameSent(CharSequence bName);
//        void onPersonEmailSent(CharSequence bEmail);
//        void onPersonAddressSent(CharSequence bAddress);
//    }



    public static AddressFragment newInstance(int page, String title){
        AddressFragment addressFragment = new AddressFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        args.putString("someTitle", title);
        addressFragment.setArguments(args);
        return addressFragment;
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
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        personName = view.findViewById(R.id.editTextPersonName);
        personEmail = view.findViewById(R.id.editTextPersonEmail);
        personAddress = view.findViewById(R.id.editTextPersonAddress);
        mFloatingActionButton = view.findViewById(R.id.fab_submit_address);

        mFloatingActionButton.setOnClickListener(v -> {
            String foundPersonName = personName.getText().toString().trim();
            String foundPersonEmail = personEmail.getText().toString().trim();
            String foundPersonAddress = personAddress.getText().toString();

            Log.d(TAG, "onCreateView: Address Fragment: " + foundPersonName);
            Log.d(TAG, "onCreateView: Address Fragment: " + foundPersonEmail);
            Log.d(TAG, "onCreateView: Address Fragment: " + foundPersonAddress);

//            addressFragmentListener.onPersonNameSent(foundPersonName);
//            addressFragmentListener.onPersonEmailSent(foundPersonEmail);
//            addressFragmentListener.onPersonAddressSent(foundPersonAddress);

            InvoiceFragment invoiceFragment = new InvoiceFragment();
            Bundle args = new Bundle();
            args.putString("foundPersonName", foundPersonName );
            args.putString("foundPersonEmail", foundPersonEmail);
            args.putString("foundPersonAddress", foundPersonAddress);
            invoiceFragment.setArguments(args);
        });

        return view;

    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if(context instanceof AddressFragmentListener){
//            addressFragmentListener = (AddressFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + "Must implement AddressFragmentListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        addressFragmentListener = null;
//    }
}
