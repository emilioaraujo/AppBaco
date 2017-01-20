package com.appbaco.appbaco.controllers.entity;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SMP on 19/01/2017.
 */

public class ConfigurationController {
    private SQLiteDatabase dataBase;

    public ConfigurationController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }
}
