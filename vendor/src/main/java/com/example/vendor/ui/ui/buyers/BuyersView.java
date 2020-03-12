package com.example.vendor.ui.ui.buyers;

import com.example.vendor.models.User;

import java.util.List;

public interface BuyersView {
    void onErrorLoading(String message);
    void setBuyers(List<User> allBuyers);

}

