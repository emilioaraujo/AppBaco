package com.appbaco.appbaco.controllers.entity;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionHeaderController {
    private SQLiteDatabase dataBase;

    public TransactionHeaderController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }
}
