package com.example.vendor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InvoiceDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "invoices";
    public static final int DATABASE_VERSION = 1;

    public InvoiceDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String DATABASE_ALTER_INVOICE_1 = "ALTER TABLE "
            + InvoiceContract.InvoiceEntry.TABLE_NAME + " ADD COLUMN " + InvoiceContract.InvoiceEntry.COLUMN_STATUS + " TEXT;";

    private static final String DATABASE_ALTER_INVOICE_2 = "ALTER TABLE "
            + InvoiceContract.InvoiceEntry.TABLE_NAME + " ADD COLUMN " + InvoiceContract.InvoiceEntry.COLUMN_STATUS + " TEXT;";

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVOICE_TABLE = " CREATE TABLE "+ InvoiceContract.InvoiceEntry.TABLE_NAME + "("
                + InvoiceContract.InvoiceEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + InvoiceContract.InvoiceEntry.COLUMN_INVOICE_DATE + " INTEGER NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + InvoiceContract.InvoiceEntry.COLUMN_PAYMENT_DUE + " INTEGER NOT NULL DEFAULT 90, "
                + InvoiceContract.InvoiceEntry.COLUMN_CUSTOMER_CONTACT + " TEXT NOT NULL, "
                + InvoiceContract.InvoiceEntry.COLUMN_STATUS + " INTEGER NOT NULL DEFAULT 1, "
                + InvoiceContract.InvoiceEntry.COLUMN_ITEM + " TEXT NOT NULL, "
                + InvoiceContract.InvoiceEntry.COLUMN_SUB_TOTAL + " INTEGER NOT NULL, "
                + InvoiceContract.InvoiceEntry.COLUMN_TAX_PAYABLE + " REAL NOT NULL DEFAULT 0.16, "
                + InvoiceContract.InvoiceEntry.COLUMN_AMOUNT_DUE + " REAL NOT NULL)";

        db.execSQL(SQL_CREATE_INVOICE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + InvoiceContract.InvoiceEntry.TABLE_NAME);
        onCreate(db);

    }
}


//    CREATE TABLE IF NOT EXISTS `invoice` (
//        `invoice_ref`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
//        `invoice_date`	INTEGER NOT NULL DEFAULT CURRENT_TIMESTAMP,
//        `payment_due`	INTEGER NOT NULL DEFAULT 90,
//        `customer_contact`	TEXT NOT NULL,
//        `item`	TEXT NOT NULL,
//        `sub_total`	INTEGER NOT NULL,
//        `tax_payable`	REAL NOT NULL DEFAULT 0.16,
//        `amount_due`	REAL NOT NULL
//        );