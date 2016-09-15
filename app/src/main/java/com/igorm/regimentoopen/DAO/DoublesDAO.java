package com.igorm.regimentoopen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igorm.regimentoopen.Doubles.Double;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igorm on 14/09/2016.
 */
public class DoublesDAO {

    private Context context;

    public DoublesDAO(Context context) {
        this.context = context;
    }

    public void addDouble(Double item) {
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name_p1", item.getNamePlayer1());
        values.put("name_p2", item.getNamePlayer2());
        values.put("win", item.getWin());
        values.put("lose", item.getLose());
        values.put("games_win", item.getGamesW());
        values.put("games_lose", item.getGamesL());
        values.put("sets_win", item.getSetsW());
        values.put("sets_lose", item.getSetsL());

        db.insert("doubles", null, values);
    }

    public void updateDouble(long id, String name_p1, String name_p2, Integer win, Integer lose, Integer games_win, Integer games_lose, Integer sets_win, Integer sets_lose) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        values.put("name_p1", name_p1);
        values.put("name_p2", name_p2);
        values.put("win", win);
        values.put("lose", lose);
        values.put("games_win", games_win);
        values.put("games_lose", games_lose);
        values.put("sets_win", sets_win);
        values.put("sets_lose", sets_lose);

        String where = DataBaseCore.ID + "=" + id;

        db.update("doubles", values, where, null);
        db.close();
    }

    public void deletePlayer(long id) {
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String where = DataBaseCore.ID + "=" + id;
        db.delete("doubles", where, null);
        db.close();
    }

    public Cursor searchDoubleById(long id) {
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "name_p1", "name_p2", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};
        String where = DataBaseCore.ID + "=" + id;
        cursor = db.query("doubles", colunas, where, null, null, null, "name_p1 ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor searchDouble() {
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "name_p1", "name_p2", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("doubles", colunas, null, null, null, null, "name_p1 ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public List<String> getAllDoublesName() {
        List<String> names = new ArrayList<String>();
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "name_p1", "name_p2", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("doubles", colunas, null, null, null, null, "name_p1 ASC");

        if (cursor != null) {
            if (cursor.moveToFirst()){
                do {
                    names.add(cursor.getString(1)+"/"+cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }

        db.close();
        return names;
    }

    public Cursor[] rankingByDoubles() {
        Cursor cursor_win, cursor_games, cursor_sets;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "name_p1", "name_p2", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor_win = db.query("doubles", colunas, null, null, null, null, "cast(win as REAL) DESC");
        cursor_games = db.query("doubles", colunas, null, null, null, null, "cast(games_win as REAL) DESC");
        cursor_sets = db.query("doubles", colunas, null, null, null, null, "cast(sets_win as REAL) DESC");

        if (cursor_win != null) {
            cursor_win.moveToFirst();
        }
        if (cursor_games != null) {
            cursor_games.moveToFirst();
        }
        if (cursor_sets != null) {
            cursor_sets.moveToFirst();
        }

        Cursor[] cursorList = new Cursor[] {cursor_win, cursor_games, cursor_sets};

        db.close();
        return cursorList;
    }

}
