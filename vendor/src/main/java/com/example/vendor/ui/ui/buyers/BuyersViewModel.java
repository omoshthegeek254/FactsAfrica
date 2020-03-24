package com.example.vendor.ui.ui.buyers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuyersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuyersViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is buyers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}