package com.example.vendor.ui.ui.buyers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.adapters.BuyersTestClass;

import java.util.ArrayList;
import java.util.List;


public class BuyersFragment extends Fragment   {

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
        lstBuyer.add(new BuyersTestClass(1,"Tuskys","tuskys@co.ke",0722000000,"Kalobot Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(2,"Nakumatt","info@nakumatt.co.ke",0722000000,"Ngong  Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(3,"Tumaini","info@tumaini.co.ke",0722000000,"Kitengela  Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(4,"Kemsa","procurement@kemsa.go.ke",0722000220,"Indusrial area Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(5,"MRM","support@mrm.co.ke",0722000000,"Mombasa Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(6,"Twiga Foods","sales@twigafoods@co.ke",0722100000,"View Park Towers",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(7,"Safaricom Plc","procurement@safaricom@co.ke",0722000000,"Kalobot Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(8,"Airtel","business.airtel@co.ke",0722000000,"Kalobot Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass(9,"Moringa","tprocurement@moringa.edu.ke",0722000000,"Ngong Rd,Lane3 unit 4",R.drawable.naivas_logo));





        View root = inflater.inflate(R.layout.fragment_buyers, container, false);
        //final TextView textView = root.findViewById(R.id.text_buyers);
        final  RecyclerView rvBuyers =root.findViewById(R.id.rvBuyers);
        CardView cardView =root.findViewById(R.id.cardViewId);
        BuyersAdapter myAdapter = new BuyersAdapter(this.getContext(),lstBuyer);
        rvBuyers.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        rvBuyers.setAdapter(myAdapter);

        // cardview click listner

//      cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BuyerDetailFragment buyersFragment =new BuyerDetailFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("name","elvin");
//                buyersFragment.setArguments(bundle);
//                FragmentManager manager = getFragmentManager();
//                manager.beginTransaction().replace(R.id.nav_host_fragment,buyersFragment).commit();
//            }
//        });




        buyersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }


}