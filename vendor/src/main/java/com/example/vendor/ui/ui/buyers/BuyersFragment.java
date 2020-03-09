package com.example.vendor.ui.ui.buyers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.adapters.BuyersTestClass;

import java.util.ArrayList;
import java.util.List;


public class BuyersFragment extends Fragment {

    private BuyersViewModel buyersViewModel;
    private List<BuyersTestClass> lstBuyer;
    private RecyclerView rvBuyers;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //
        buyersViewModel =
                ViewModelProviders.of(this).get(BuyersViewModel.class);


        //dummy data
        lstBuyer=new ArrayList<>();
        lstBuyer.add(new BuyersTestClass("Nakumatt",1,"Prestige",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Naivas",2,"Moi Avenue",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Jumia",3,"The Junction",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Kamala",4,"Kayole1",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Loise",5,"Langata",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Kebaso",6,"Kericho2",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Uchumi",7,"Kayole",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Ukwala2",8,"Kayole",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Uchumi2",9,"Kayole",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Phone Express",10,"Kayole",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Uchumi4",11,"Kayole",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("Safaricom",12,"Kayole",R.drawable.naivas_logo));




        View root = inflater.inflate(R.layout.fragment_buyers, container, false);
        final TextView textView = root.findViewById(R.id.text_buyers);
        final  RecyclerView rvBuyers =root.findViewById(R.id.rvBuyers);
        BuyersAdapter myAdapter = new BuyersAdapter(this.getContext(),lstBuyer);
        rvBuyers.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        rvBuyers.setAdapter(myAdapter);




        buyersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}