package com.example.vendor.ui.ui.buyers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import static com.example.vendor.ui.BottomNavigation.EXTRA_ADDRESS;

import static com.example.vendor.ui.BottomNavigation.EXTRA_EMAIL;
import static com.example.vendor.ui.BottomNavigation.EXTRA_ID;
import static com.example.vendor.ui.BottomNavigation.EXTRA_LOGO;
import static com.example.vendor.ui.BottomNavigation.EXTRA_NAME;
import static com.example.vendor.ui.BottomNavigation.EXTRA_PHONE;


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
        lstBuyer.add(new BuyersTestClass("1","Tuskys","tuskys@co.ke","0722000000","Kalobot Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("2","Nakumatt","info@nakumatt.co.ke","0722000000","Ngong  Rd,Lane3 unit 4",R.drawable.brookside));
        lstBuyer.add(new BuyersTestClass("3","Tumaini","info@tumaini.co.ke","0722000000","Kitengela  Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("4","Kemsa","procurement@kemsa.go.ke","0722000220","Indusrial area Rd,Lane3 unit 4",R.drawable.brookside));
        lstBuyer.add(new BuyersTestClass("5","MRM","support@mrm.co.ke","0722000000","Mombasa Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("6","Twiga Foods","sales@twigafoods@co.ke","0722100000","View Park Towers",R.drawable.brookside));
        lstBuyer.add(new BuyersTestClass("7","Safaricom Plc","procurement@safaricom@co.ke","0722000000","Kalobot Rd,Lane3 unit 4",R.drawable.naivas_logo));
        lstBuyer.add(new BuyersTestClass("8","Airtel","business.airtel@co.ke","0722000000","Kalobot Rd,Lane3 unit 4",R.drawable.brookside));
        lstBuyer.add(new BuyersTestClass("9","Moringa","tprocurement@moringa.edu.ke","0722000000","Ngong Rd,Lane3 unit 4",R.drawable.naivas_logo));





        View root = inflater.inflate(R.layout.fragment_buyers, container, false);
        //final TextView textView = root.findViewById(R.id.text_buyers);
        final  RecyclerView rvBuyers =root.findViewById(R.id.rvBuyers);
        BuyersAdapter myAdapter = new BuyersAdapter(this.getContext(),lstBuyer);
        rvBuyers.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        rvBuyers.setAdapter(myAdapter);

        myAdapter.setOnClickListener((view, position) -> {
            TextView mSupplierName = view.findViewById(R.id.buyerName);
            ImageView mLogo = view.findViewById(R.id.buyerLogo);
            Intent intent = new Intent(getActivity(), BuyerActivity.class);

           // intent.putExtra(EXTRA_LOGO,lstBuyer.get(position).getLogo());
            intent.putExtra(EXTRA_LOGO,lstBuyer.get(position).getLogo());
            intent.putExtra(EXTRA_ID,lstBuyer.get(position).getId());

            intent.putExtra(EXTRA_NAME, mSupplierName.getText().toString());

            intent.putExtra(EXTRA_EMAIL,lstBuyer.get(position).getEmail());
            intent.putExtra(EXTRA_PHONE,lstBuyer.get(position).getPhone());
            intent.putExtra(EXTRA_ADDRESS,lstBuyer.get(position).getAddress());


            startActivity(intent);

//            Intent intent = new Intent(getActivity().getBaseContext(),
//                    BuyerActivity.class);
//            intent.putExtra("name",  lstBuyer.get(position).getName() );
//            intent.putExtra("id",  lstBuyer.get(position).getId() );
//
//            startActivity(intent);

        });

//        buyersViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}