package com.example.vendor.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.vendor.R;
import com.example.vendor.ui.ui.buyers.BuyersFragment;

import butterknife.ButterKnife;

public class MakeInvoiceActivity extends AppCompatActivity {

    private FragmentPagerAdapter adapterViewPager;



    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AddressFragment.newInstance(0,"Enter Address");
                case 1: // Fragment # 0 - This will show FirstFragment
                    return AddItemsFragment.newInstance(1, "Add Items");
                case 2: // Fragment # 0 - This will show FirstFragment different title
                    return InvoiceFragment.newInstance(2, "Create Invoice");
                case 3:
                    return PreviewFragment.newInstance(3,"Preview Invoice");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String heading = "";

            if(position==0){
                heading = "Enter Address";
            } else if (position ==1){
                heading = "Add Items";
            } else if(position==2){
                heading = "Create Invoice";
            } else if(position==3){
                heading = "Preview Invoice";
            }

            return heading;


            //return "Page " + position;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_invoice);
        ButterKnife.bind(this);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
