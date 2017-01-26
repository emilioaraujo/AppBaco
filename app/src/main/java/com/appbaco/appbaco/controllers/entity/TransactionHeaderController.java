package com.appbaco.appbaco.controllers.entity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appbaco.appbaco.models.entity.TransactionHeader;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionHeaderController {
    private SQLiteDatabase dataBase;
private TransactionDetailController transactionDetailController;
    public TransactionHeaderController(SQLiteDatabase dataBase) {
        this.dataBase = dataBase;
        this.transactionDetailController= new TransactionDetailController(this.dataBase);
    }

    private ContentValues getContentValues(TransactionHeader entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync", "0");
        contentValues.put("transaction_type_id", entity.getTransactionTypeId().toString());
        contentValues.put("datetime_create", entity.getDateTimeCreate().toString());
        contentValues.put("datetime", entity.getDateTime().toString());
        contentValues.put("completed", entity.getComplete().toString());
        contentValues.put("concept", entity.getConcept().toString());
        contentValues.put("image", entity.getImage().toString());
        contentValues.put("location", entity.getLocation().toString());
        return contentValues;
    }

    public void create(TransactionHeader entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        try {
            this.dataBase.insertOrThrow("main.transaction_header", null, contentValues);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void update(TransactionHeader entity) throws Exception {
        ContentValues contentValues = getContentValues(entity);
        if (entity == null) {
            throw new Exception("Entity for update is null");
        }
        if (entity.getId() == null) {
            throw new Exception("Entity Id for update is null");
        }
        try {
            this.dataBase.update("main.transaction_header", contentValues, "id=" + entity.getId(), null);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void delete(TransactionHeader entity) throws Exception {
        try {
            dataBase.delete("main.transaction_header", "id=?", new String[]{entity.getId().toString()});
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public TransactionHeader findById(Integer id)throws Exception {
        TransactionHeader entity = null;
        try {
            Cursor c = dataBase.rawQuery("select id,sync,transaction_type_id,datetime_create,datetime,completed,concept,image,location from main.transaction_header where id=" + id, null);
            if (c.moveToFirst()) {
                entity = new TransactionHeader(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        new Date(c.getString(3)),
                        new Date(c.getString(4)),
                        c.getInt(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        transactionDetailController.findByTransactionId(c.getInt(0)));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entity;
    }

    public ArrayList<TransactionHeader> findAll() throws Exception {
        ArrayList<TransactionHeader> entities = new ArrayList<TransactionHeader>();
        try {
            Cursor c = dataBase.rawQuery("select id,sync,transaction_type_id,datetime_create,datetime,completed,concept,image,location from main.transaction_header order by id desc", null);
            if (c.moveToFirst()) {
                do {
                    entities.add(new TransactionHeader(
                            c.getInt(0),
                            c.getInt(1),
                            c.getInt(2),
                            new Date(),
                            new Date(),
                            c.getInt(5),
                            c.getString(6),
                            c.getString(7),
                            c.getString(8),
                            transactionDetailController.findByTransactionId(c.getInt(0))));
                } while (c.moveToNext());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return entities;
    }
}
