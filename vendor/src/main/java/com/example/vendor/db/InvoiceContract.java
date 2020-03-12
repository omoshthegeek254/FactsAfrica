package com.example.vendor.db;

import android.provider.BaseColumns;

public final class InvoiceContract {

    public InvoiceContract() { }

    public static abstract class InvoiceEntry implements BaseColumns {

    public static final String TABLE_NAME = "invoice";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_INVOICE_DATE = "invoice_date";
    public static final String COLUMN_PAYMENT_DUE = "payment_due";
    public static final String COLUMN_CUSTOMER_CONTACT = "customer_contact";
    public static final String COLUMN_ITEM = "item";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_SUB_TOTAL = "sub_total";
    public static final String COLUMN_TAX_PAYABLE = "tax_payable";
    public static final String COLUMN_AMOUNT_DUE = "amount_due";


    }
}
