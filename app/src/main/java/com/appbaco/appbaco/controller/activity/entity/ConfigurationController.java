package com.appbaco.appbaco.controller.activity.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
/**
 * Created by SMP on 19/01/2017.
 */

public class ConfigurationController {
    private SQLiteDatabase dataBase;

    public ConfigurationController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }
}
