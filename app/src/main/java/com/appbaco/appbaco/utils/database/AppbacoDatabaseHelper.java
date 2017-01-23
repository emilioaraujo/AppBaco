package com.appbaco.appbaco.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SMP on 19/01/2017.
 */

public class AppbacoDatabaseHelper extends SQLiteOpenHelper {

    String sqlConfigurationCreate = "" +
            "CREATE TABLE main.configuration (\n" +
            "    id               INTEGER \n" +
            "                     UNIQUE\n" +
            "                     NOT NULL,\n" +
            "    sync    INTEGER DEFAULT 0,\n" +
            "    app_pin          TEXT,\n" +
            "    security         INTEGER DEFAULT 0,\n" +
            "    monetary_simbol  TEXT " +
            "                     DEFAULT '$',\n" +
            "    date_format      TEXT " +
            "                     DEFAULT 'yyyy-mm-dd',\n" +
            "    hourFormat       TEXT " +
            "                     DEFAULT 'hh12',\n" +
            "    app_theme        TEXT " +
            "                     DEFAULT 'Default',\n" +
            ");\n" +
            "";



    String sqlAccountTypeCreate = "" +
            "CREATE TABLE main.account_type (\n" +
            "    id                  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                        UNIQUE\n" +
            "                        NOT NULL,\n" +
            "    sync         INTEGER DEFAULT 0,\n" +
            "    account_category_id INTEGER DEFAULT 0,\n" +
            "    name   TEXT    UNIQUE\n" +
            "                   NOT NULL\n" +
            ");\n" +
            "";

    String sqlAccountCreate = "" +
            "CREATE TABLE main.account (\n" +
            "    id               INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                            UNIQUE,\n" +
            "    sync             INTEGER DEFAULT 0,\n" +
            "    account_type_id  INTEGER DEFAULT 0,\n" +
            "    name             TEXT    NOT NULL\n" +
            "                            DEFAULT ''\n" +
            "                            UNIQUE,\n" +
            "    description      TEXT,\n" +
            "    initial_balance  REAL    DEFAULT 0,\n" +
            "    amount_limit     REAL    DEFAULT 0,\n" +
            "    pay_day          INTEGER DEFAULT 0,\n" +
            "    expire_month     INTEGER DEFAULT 0,\n" +
            "    expire_year      INTEGER DEFAULT 0,\n" +
            "    color            TEXT\n" +
            ");\n" +
            "";

    String sqlTransactionCategoryCreate = "" +
            "CREATE TABLE main.transaction_category (\n" +
            "    id                  INTEGER NOT NULL\n" +
            "                                PRIMARY KEY AUTOINCREMENT\n" +
            "                                UNIQUE,\n" +
            "    sync                INTEGER DEFAULT 0,\n" +
            "    transaction_type_id INTEGER NOT NULL\n" +
            "                                DEFAULT 0\n" +
            "                                REFERENCES transaction_type (id),\n" +
            "    name                TEXT    NOT NULL\n" +
            "                                DEFAULT ''\n" +
            "                                UNIQUE,\n" +
            "    description         TEXT    DEFAULT '',\n" +
            "    color               INTEGER DEFAULT 0\n" +
            ");" +
            "";



    String sqlTransactionDetailCreate = "" +
            "CREATE TABLE main.transaction_detail (\n" +
            "    id                       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                                  UNIQUE\n" +
            "                                  NOT NULL,\n" +
            "    sync                    INTEGER DEFAULT 0,\n" +
            "    transaction_header_id   INTEGER REFERENCES transaction_header (id),\n" +
            "    account_id              INTEGER REFERENCES account (id) \n" +
            "                            NOT NULL,\n" +
            "    transaction_category_id INTEGER NOT NULL\n" +
            "                            REFERENCES category (id),\n" +
            "    amount                  REAL  NOT NULL\n" +
            "                                  DEFAULT (0) \n" +
            ");" +
            "";

    String sqlTransactionHeaderCreate = "" +
            "CREATE TABLE main.transaction_header (\n" +
            "    id                  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                                UNIQUE\n" +
            "                                NOT NULL,\n" +
            "    sync                INTEGER DEFAULT 0,\n" +
            "    transaction_type_id INTEGER REFERENCES transaction_type (id) \n" +
            "                                NOT NULL,\n" +
            "    datetime_create     INTEGER DEFAULT (datetime('now') ),\n" +
            "    datetime            INTEGER DEFAULT (datetime('now') ),\n" +
            "    completed           INTEGER DEFAULT (0),\n" +
            "    concept             TEXT,\n" +
            "    image               TEXT,\n" +
            "    location            TEXT\n" +
            ");\n" +
            "";



    public AppbacoDatabaseHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlConfigurationCreate);
        db.execSQL(sqlAccountTypeCreate);
        db.execSQL(sqlAccountCreate);
        db.execSQL(sqlTransactionCategoryCreate);
        db.execSQL(sqlTransactionDetailCreate);
        db.execSQL(sqlTransactionHeaderCreate);


        this.defaultTableRecords(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        // Desactivado por el momento hasta que se termine la app
        /*
        //Se elimina la versión anterior de las tablas temporales
        db.execSQL("DROP TABLE IF EXISTS temp.configuration");
        db.execSQL("DROP TABLE IF EXISTS temp.account_type");
        db.execSQL("DROP TABLE IF EXISTS temp.account");
        db.execSQL("DROP TABLE IF EXISTS temp.transaction_category");
        db.execSQL("DROP TABLE IF EXISTS temp.transaction_header");
        db.execSQL("DROP TABLE IF EXISTS temp.transaction_detail");


        // Se pasan los valores actuales de cada tabla a una nueva tabla temporal
        db.execSQL("CREATE TEMPORARY TABLE temp.configuration AS  SELECT * FROM main.configuration;");
        db.execSQL("CREATE TEMPORARY TABLE temp.account_type AS  SELECT * FROM main.account_type;");
        db.execSQL("CREATE TEMPORARY TABLE temp.account AS  SELECT * FROM main.account;");
        db.execSQL("CREATE TEMPORARY TABLE temp.transaction_category AS  SELECT * FROM main.transaction_category;");
        db.execSQL("CREATE TEMPORARY TABLE temp.transaction_header AS  SELECT * FROM main.transaction_header;");
        db.execSQL("CREATE TEMPORARY TABLE temp.transaction_detail AS  SELECT * FROM main.transaction_detail;");


        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS main.configuration");
        db.execSQL("DROP TABLE IF EXISTS main.account_type");
        db.execSQL("DROP TABLE IF EXISTS main.account");
        db.execSQL("DROP TABLE IF EXISTS main.transaction_category");
        db.execSQL("DROP TABLE IF EXISTS main.transaction_header");
        db.execSQL("DROP TABLE IF EXISTS main.transaction_detail");


        //Se crea la nueva versión de la tabla
        db.execSQL(sqlConfigurationCreate);
        db.execSQL(sqlAccountTypeCreate);
        db.execSQL(sqlAccountCreate);
        db.execSQL(sqlTransactionCategoryCreate);
        db.execSQL(sqlTransactionDetailCreate);
        db.execSQL(sqlTransactionHeaderCreate);


        //Se pasan los valores de las tablas temporales a las nuevas tablas
        db.execSQL("insert into main.configuration " +
                "         (id,sync,app_pin,monetary_simbol,formato_fecha,formato_hora,app_theme) " +
                "   select id,sync,app_pin,monetary_simbol,formato_fecha,formato_hora,app_theme " +
                "     from temp.configuration;");

        db.execSQL("insert into main.account_type" +
                "         (id,sync,account_category_id,name) " +
                "   select id,sync,account_category_id,name " +
                "     from temp.account_type;");
        db.execSQL("insert into main.account" +
                "          (id,sync,account_type_id,name,description,initial_balance,amount_limit,pay_day,expire_month,expire_year,color)" +
                "    select id,sync,account_type_id,name,description,initial_balance,amount_limit,pay_day,expire_month,expire_year,color " +
                "      from temp.account;");
        db.execSQL("insert into main.transaction_category" +
                "          (id,sync,transaction_type_id,name,description,color)" +
                "    select id,sync,transaction_type_id,name,description,color " +
                "      from temp.transaction_category;");

        db.execSQL("insert into main.transaction_detail" +
                "         (id,sync,transaction_header_id,account_id,transaction_category_id,tag_id,amount) " +
                "   select id,sync,transaction_header_id,account_id,transaction_category_id,tag_id,amount " +
                "     from temp.transaction_detail;");
        db.execSQL("insert into main.transaction_header" +
                "         (id,sync,transaction_type_id,datetime,datetime_create,completed,note,image,location) " +
                "   select id,sync,transaction_type_id,datetime,datetime_create,completed,note,image,location " +
                "     from temp.transaction_header;");

*/
    }

    private void defaultTableRecords(SQLiteDatabase db){
        db.execSQL("insert into configuration (id,sync,app_pin,monetary_simbol,formato_fecha,formato_hora,app_theme) " +
                "values (1,0,'','$','yyyy-mm-dd','hh12','Default');");

        db.execSQL("insert into account_type (id,name,account_category_id) values (1,'WALLET',1);");
        db.execSQL("insert into account_type (id,name,account_category_id) values (2,'SAVING',1);");
        db.execSQL("insert into account_type (id,name,account_category_id) values (3,'LOAN',2);");
        db.execSQL("insert into account_type (id,name,account_category_id) values (4,'CREDIT CARD',2);");


    }
}