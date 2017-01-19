package com.appbaco.appbaco.controller.activity.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appbaco.appbaco.model.entity.Account;

import java.util.ArrayList;
/**
 * Created by SMP on 19/01/2017.
 */

public class AccountController {
    private SQLiteDatabase dataBase;

    public AccountController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }

    private ContentValues getContentValues(Account entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync", "0");
        contentValues.put("account_type_id", entity.getAccountTypeId().toString());
        contentValues.put("name", entity.getName().toString());
        contentValues.put("description", entity.getDescription().toString());
        contentValues.put("initial_balance", entity.getInitialBalance().toString());
        contentValues.put("amount_limit", entity.getAmountLimit().toString());
        contentValues.put("pay_day", entity.getDayPay().toString());
        contentValues.put("expire_month", entity.getExpireMonth().toString());
        contentValues.put("expire_year", entity.getExpireYear().toString());
        contentValues.put("color", entity.getColor());
        return contentValues;
    }

    public void create(Account entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        try {
            this.dataBase.insertOrThrow("main.account", null, contentValues);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void update(Account entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        if (entity == null) {
            throw new Exception("Entity for update is null");
        }
        if (entity.getId() == null) {
            throw new Exception("Entity Id for update is null");
        }
        try {
            this.dataBase.update("main.account", contentValues, "id=" + entity.getId(), null);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void delete(Account entity) throws Exception {
        try {
            dataBase.delete("main.account", "id=?", new String[]{entity.getId().toString()});
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public Account findById(Integer id)throws Exception {
        Account tag = null;
        try {
            Cursor c = dataBase.rawQuery("select id,sync,account_type_id,name,description,initial_balance,amount_limit,pay_day,expire_month,expire_year,color from main.account where id=" + id, null);
            if (c.moveToFirst()) {
                tag = new Account(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getDouble(5),c.getDouble(6),c.getInt(7),c.getInt(8),c.getInt(9),c.getInt(10));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return tag;
    }

    public ArrayList<Account> findAll() throws Exception {
        ArrayList<Account> entities = new ArrayList<Account>();
        try {
            Cursor c = dataBase.rawQuery("select id,sync,account_type_id,name,description,initial_balance,amount_limit,pay_day,expire_month,expire_year,color from main.account order by id desc", null);
            if (c.moveToFirst()) {
                do {
                    entities.add(new Account(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getDouble(5),c.getDouble(6),c.getInt(7),c.getInt(8),c.getInt(9),c.getInt(10)));
                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entities;
    }

}
