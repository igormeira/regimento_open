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
public class DataBase {

    private SQLiteDatabase db;

    public DataBase (Context ctx) {
        Core auxBD = new Core(ctx);
        db = auxBD.getWritableDatabase();
    }

    public void addPlayer(Player item) {
        ContentValues values = new ContentValues();
        values.put("nome", item.getName());
        values.put("win", item.getWin());
        values.put("lose", item.getLose());
        values.put("games_win", item.getGamesW());
        values.put("games_lose", item.getGamesW());
        values.put("sets_win", item.getSetsW());
        values.put("sets_lose", item.getGamesW());

        db.insert("player", null, values);
    }

    public void updatePlayer(long id, String nome, Integer win, Integer lose, Integer games_win, Integer games_lose, Integer sets_win, Integer sets_lose) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("win", win);
        values.put("lose", lose);
        values.put("games_win", games_win);
        values.put("games_lose", games_lose);
        values.put("sets_win", sets_win);
        values.put("sets_lose", sets_lose);

        String where = Core.ID + "=" + id;

        db.update("player", values, where, null);
        db.close();
    }

    public void deletePlayer(long id) {
        String where = Core.ID + "=" + id;
        db.delete("player", where, null);
        db.close();
    }

    public Cursor searchItemById(long id) {
        Cursor cursor;
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};
        String where = Core.ID + "=" + id;
        cursor = db.query("player", colunas, where, null, null, null, "nome ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor searchPlayer() {
        Cursor cursor;
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("player", colunas, null, null, null, null, "nome ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor searchPlayerByName(String playerName) {
        Player player = new Player();
        Cursor cursor;
        String[] colunas = new String[] {"_id", "nome", "win", "lose", "games_win", "games_lose", "sets_win", "sets_lose"};

        cursor = db.query("player", colunas, "nome = playerName", null, null, null, "nome ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public List<String> getAllPlayersName() {
        List<String> names = new ArrayList<String>();
        Cursor cursor;
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