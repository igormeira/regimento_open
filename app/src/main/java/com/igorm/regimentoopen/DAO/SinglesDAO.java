package com.igorm.regimentoopen.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.igorm.regimentoopen.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igorm on 25/06/2016.
 */
public class SinglesDAO {

    private Context context;

    public SinglesDAO(Context context) {
        this.context = context;
    }

    public void addPlayer(Player item) {
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", item.getName());
        values.put("win", item.getWin());
        values.put("lose", item.getLose());
        values.put("games_win", item.getGamesW());
        values.put("games_lose", item.getGamesL());
        values.put("sets_win", item.getSetsW());
        values.put("sets_lose", item.getSetsL());

        db.insert("player", null, values);
    }

    public void updatePlayer(long id, String nome, Integer win, Integer lose, Integer games_win, Integer games_lose, Integer sets_win, Integer sets_lose) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        values.put("nome", nome);
        values.put("win", win);
        values.put("lose", lose);
        values.put("games_win", games_win);
        values.put("games_lose", games_lose);
        values.put("sets_win", sets_win);
        values.put("sets_lose", sets_lose);

        String where = DataBaseCore.ID + "=" + id;

        db.update("player", values, where, null);
        db.close();
    }

    public void deletePlayer(long id) {
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String where = DataBaseCore.ID + "=" + id;
        db.delete("player", where, null);
        db.close();
    }

    public Cursor searchItemById(long id) {
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};
        String where = DataBaseCore.ID + "=" + id;
        cursor = db.query("player", colunas, where, null, null, null, "nome ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor searchPlayer() {
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("player", colunas, null, null, null, null, "nome ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor[] searchPlayerByName(String player1, String player2) {
        Cursor cursorP1, cursorP2;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};
        String whereP1 = DataBaseCore.NAME + "=" + player1;
        String whereP2 = DataBaseCore.NAME + "=" + player2;

        cursorP1 = db.query("player", colunas, whereP1, null, null, null, "nome ASC");
        cursorP2 = db.query("player", colunas, whereP2, null, null, null, "nome ASC");

        if (cursorP1 != null) {
            cursorP1.moveToFirst();
        }
        if (cursorP2 != null) {
            cursorP2.moveToFirst();
        }

        Cursor[] cursorList = new Cursor[] {cursorP1, cursorP2};

        db.close();
        return cursorList;
    }

    public List<String> getAllPlayersName() {
        List<String> names = new ArrayList<String>();
        Cursor cursor;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("player", colunas, null, null, null, null, "nome ASC");

        if (cursor != null) {
            if (cursor.moveToFirst()){
                do {
                    names.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }

        db.close();
        return names;
    }

    public Cursor[] rankingByPlayer() {
        Cursor cursor_win, cursor_games, cursor_sets;
        SQLiteDatabase db = new DataBaseCore(context).getWritableDatabase();
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor_win = db.query("player", colunas, null, null, null, null, "cast(win as REAL) DESC");
        cursor_games = db.query("player", colunas, null, null, null, null, "cast(games_win as REAL) DESC");
        cursor_sets = db.query("player", colunas, null, null, null, null, "cast(sets_win as REAL) DESC");

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