package com.appbaco.appbaco.controllers.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appbaco.appbaco.models.entity.AppbacoConfiguration;

import java.util.ArrayList;

/**
 * Created by SMP on 19/01/2017.
 */

public class AppbacoConfigurationController {
    private SQLiteDatabase dataBase;

    public AppbacoConfigurationController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }

    private ContentValues getContentValues(AppbacoConfiguration entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync", entity.getSync());
        contentValues.put("security", entity.getSecurity());
        contentValues.put("app_pin", entity.getAppPin().toString());
        contentValues.put("date_format", entity.getDateFormat().toString());
        contentValues.put("hour_format", entity.getHourFormat().toString());
        contentValues.put("theme", entity.getAppTheme().toString());
        return contentValues;
    }

    public void create(AppbacoConfiguration entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        try {
            this.dataBase.insertOrThrow("main.configuration", null, contentValues);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void update(AppbacoConfiguration entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        if (entity == null) {
            throw new Exception("Entity for update is null");
        }
        if (entity.getId() == null) {
            throw new Exception("Entity Id for update is null");
        }
        try {
            this.dataBase.update("main.configuration", contentValues, "id=" + entity.getId(), null);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void delete(AppbacoConfiguration entity) throws Exception {
        try {
            dataBase.delete("main.configuration", "id=?", new String[]{entity.getId().toString()});
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public AppbacoConfiguration findById(Integer id)throws Exception {
        AppbacoConfiguration entity = null;
        try {
            Cursor c = dataBase.rawQuery("select id,sync,app_pin,security,monetary_simbol,date_format,hour_format,app_theme from main.configuration where id=" + id, null);
            if (c.moveToFirst()) {
                entity = new AppbacoConfiguration(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entity;
    }

    public ArrayList<AppbacoConfiguration> findAll() throws Exception {
        ArrayList<AppbacoConfiguration> entities = new ArrayList<AppbacoConfiguration>();
        try {
            Cursor c = dataBase.rawQuery("select id,sync,app_pin,security,monetary_simbol,date_format,hour_format,app_theme from main.configuration  order by id desc", null);
            if (c.moveToFirst()) {
                do {
                    entities.add(new AppbacoConfiguration(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7)));
                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entities;
    }
}
