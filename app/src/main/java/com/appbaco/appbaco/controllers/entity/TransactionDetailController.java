package com.appbaco.appbaco.controllers.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appbaco.appbaco.models.entity.TransactionDetail;

import java.util.ArrayList;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionDetailController {
    private SQLiteDatabase dataBase;

    public TransactionDetailController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
    }
    private ContentValues getContentValues(TransactionDetail entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync", "0");
        contentValues.put("transaction_header_id", entity.getTransactionId().toString());
        contentValues.put("account_id", entity.getAccountId().toString());
        contentValues.put("transaction_category_id", entity.getTransactionCategoryId().toString());
        contentValues.put("amount", entity.getAmount().toString());

        return contentValues;
    }

    public void create(TransactionDetail entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        try {
            this.dataBase.insertOrThrow("main.transaction_detail", null, contentValues);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void update(TransactionDetail entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        if (entity == null) {
            throw new Exception("Entity for update is null");
        }
        if (entity.getId() == null) {
            throw new Exception("Entity Id for update is null");
        }
        try {
            this.dataBase.update("main.transaction_detail", contentValues, "id=" + entity.getId(), null);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void delete(TransactionDetail entity) throws Exception {
        try {
            dataBase.delete("main.transaction_detail", "id=?", new String[]{entity.getId().toString()});
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public ArrayList<TransactionDetail> findByTransactionId(Integer id)throws Exception {
        ArrayList<TransactionDetail> entities = new ArrayList<TransactionDetail>();
        try {
            Cursor c = dataBase.rawQuery("select id,sync,transaction_header_id,account_id,transaction_category_id,amount from main.transaction_detail where transaction_header_id=" + id, null);
            if (c.moveToFirst()) {
                do {
                    entities.add( new TransactionDetail(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getDouble(5)));
                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entities;
    }

}
