package com.appbaco.appbaco.controller.activity.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appbaco.appbaco.model.entity.TransactionCategory;

import java.util.ArrayList;
/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionCategoryController {
    private SQLiteDatabase dataBase;

    public TransactionCategoryController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }

    private ContentValues getContentValues(TransactionCategory entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync", "0");
        contentValues.put("transaction_type_id", entity.getTransactionTypeId().toString());
        contentValues.put("name", entity.getName().toString());
        contentValues.put("description", entity.getDescription().toString());
        contentValues.put("color", entity.getColor().toString());
        return contentValues;
    }

    public void create(TransactionCategory entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        try {
            this.dataBase.insertOrThrow("main.transaction_category", null, contentValues);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void update(TransactionCategory entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        if (entity == null) {
            throw new Exception("Entity for update is null");
        }
        if (entity.getId() == null) {
            throw new Exception("Entity Id for update is null");
        }
        try {
            this.dataBase.update("main.transaction_category", contentValues, "id=" + entity.getId(), null);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void delete(TransactionCategory entity) throws Exception {
        try {
            dataBase.delete("main.transaction_category", "id=?", new String[]{entity.getId().toString()});
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public TransactionCategory findById(Integer id)throws Exception {
        TransactionCategory tag = null;
        try {
            Cursor c = dataBase.rawQuery("select id,sync,transaction_type_id,name,description,color from main.transaction_category where id=" + id, null);
            if (c.moveToFirst()) {
                tag = new TransactionCategory(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getInt(5));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return tag;
    }

    public ArrayList<TransactionCategory> findAll() throws Exception {
        ArrayList<TransactionCategory> entities = new ArrayList<TransactionCategory>();
        try {
            Cursor c = dataBase.rawQuery("select id,sync,transaction_type_id,name,description,color from main.transaction_category order by id desc", null);
            if (c.moveToFirst()) {
                do {
                    entities.add(new TransactionCategory(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getInt(5)));
                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entities;
    }

}
