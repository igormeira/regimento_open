package com.igorm.regimentoopen.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by igorm on 25/06/2016.
 */
public class DataBaseCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "DataBase";
    private static final int DB_VERSION = 1;
    public static final String ID = "_id";
    private static final String TABLE = "player";
    private static final String DOUBLES_TABLE = "doubles";
    public static final String NAME_P1 = "name_p1";
    public static final String NAME_P2 = "name_p2";
    public static final String NAME = "nome";
    public static final String WIN = "win";
    public static final String LOSE = "lose";
    public static final String GAMES_WIN = "games_win";
    public static final String GAMES_LOSE = "games_lose";
    public static final String SETS_WIN = "sets_win";
    public static final String SETS_LOSE = "sets_lose";

    public DataBaseCore(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE+"("
                +ID+" integer primary key autoincrement, "
                +NAME+" text not null, "
                +WIN+" integer not null, "
                +LOSE+" integer not null, "
                +GAMES_WIN+" integer not null, "
                +GAMES_LOSE+" integer not null, "
                +SETS_WIN+" integer not null, "
                +SETS_LOSE+" integer not null)");

        db.execSQL("create table "+DOUBLES_TABLE+"("
                +ID+" integer primary key autoincrement, "
                +NAME_P1+" text not null, "
                +NAME_P2+" text not null, "
                +WIN+" integer not null, "
                +LOSE+" integer not null, "
                +GAMES_WIN+" integer not null, "
                +GAMES_LOSE+" integer not null, "
                +SETS_WIN+" integer not null, "
                +SETS_LOSE+" integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + DOUBLES_TABLE);
        onCreate(db);
    }
}
