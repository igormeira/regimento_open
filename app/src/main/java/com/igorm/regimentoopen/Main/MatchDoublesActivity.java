package com.igorm.regimentoopen.Main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.igorm.regimentoopen.DAO.DataBaseCore;
import com.igorm.regimentoopen.DAO.DoublesDAO;
import com.igorm.regimentoopen.Doubles.Double;
import com.igorm.regimentoopen.R;

import java.util.List;

public class MatchDoublesActivity extends AppCompatActivity {

    private Spinner spinnerD1, spinnerD2;
    private EditText gamesD1, gamesD2, setsD1, setsD2;
    private DoublesDAO doublesDAO;
    private Double d1, d2;
    private Intent intentMain;

    private String cod;
    private DoublesDAO dbListView, db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_doubles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentMain = new Intent(MatchDoublesActivity.this, DoublesActivity.class);

        spinnerD1 = (Spinner) findViewById(R.id.spinner_d1);
        spinnerD2 = (Spinner) findViewById(R.id.spinner_d2);

        gamesD1 = (EditText) findViewById(R.id.editText_games_d1);
        gamesD2 = (EditText) findViewById(R.id.editText_games_d2);
        setsD1 = (EditText) findViewById(R.id.editText_sets_d1);
        setsD2 = (EditText) findViewById(R.id.editText_sets_d2);

        doublesDAO = new DoublesDAO(getBaseContext());

        populateSpinners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validadeData()) saveMatchResult();
                }
            });
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    private boolean validadeData() {
        if (gamesD1.getText().toString().equals("")) gamesD1.setError(getString(R.string.blank_field));
        else if (gamesD2.getText().toString().equals("")) gamesD2.setError(getString(R.string.blank_field));
        else if (setsD1.getText().toString().equals("")) setsD1.setError(getString(R.string.blank_field));
        else if (setsD2.getText().toString().equals("")) setsD2.setError(getString(R.string.blank_field));
        else return true;

        return false;
    }

    private void saveMatchResult() {
        saveP1();
        saveP2();

        updatePlayers();
        save();
    }

    private void saveP2() {
        dbListView = new DoublesDAO(getBaseContext());
        db = new DoublesDAO(this);
        cursor = db.searchDouble();
        cursor.moveToPosition(spinnerD2.getSelectedItemPosition());
        cod = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCore.ID));
        Cursor cursorAux = dbListView.searchDoubleById(Long.parseLong(cod));

        d2 = new Double(Long.parseLong(cod), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P1)), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P2)),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_LOSE))));
    }

    private void saveP1() {
        dbListView = new DoublesDAO(getBaseContext());
        db = new DoublesDAO(this);
        cursor = db.searchDouble();
        cursor.moveToPosition(spinnerD1.getSelectedItemPosition());
        cod = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCore.ID));
        Cursor cursorAux = dbListView.searchDoubleById(Long.parseLong(cod));

        d1 = new Double(Long.parseLong(cod), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P1)), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P2)),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_LOSE))));
    }

    private void save() {
        DoublesDAO dbUp = new DoublesDAO(getBaseContext());

        dbUp.updateDouble(d1.getId(), d1.getNamePlayer1(), d1.getNamePlayer2(), d1.getWin(), d1.getLose(), d1.getGamesW(),
                d1.getGamesL(), d1.getSetsW(), d1.getSetsL());

        dbUp.updateDouble(d2.getId(), d2.getNamePlayer1(), d2.getNamePlayer2(), d2.getWin(), d2.getLose(), d2.getGamesW(),
                d2.getGamesL(), d2.getSetsW(), d2.getSetsL());

        startActivity(intentMain);
        Toast.makeText(MatchDoublesActivity.this, getString(R.string.updated), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updatePlayers() {
        if (!setsD1.getText().toString().equals("0") || !setsD2.getText().toString().equals("0")) {
            if (Integer.parseInt(setsD1.getText().toString()) > Integer.parseInt(setsD2.getText().toString())) {
                d1.setWin(d1.getWin() + 1);
                d1.setSetsW(d1.getSetsW() + Integer.parseInt(setsD1.getText().toString()));
                d1.setSetsL(d1.getSetsL() + Integer.parseInt(setsD2.getText().toString()));
                d1.setGamesW(d1.getGamesW() + Integer.parseInt(gamesD1.getText().toString()));
                d1.setGamesL(d1.getGamesL() + Integer.parseInt(gamesD2.getText().toString()));

                d2.setLose(d2.getLose() + 1);
                d2.setSetsW(d2.getSetsW() + Integer.parseInt(setsD2.getText().toString()));
                d2.setSetsL(d2.getSetsL() + Integer.parseInt(setsD1.getText().toString()));
                d2.setGamesW(d2.getGamesW() + Integer.parseInt(gamesD2.getText().toString()));
                d2.setGamesL(d2.getGamesL() + Integer.parseInt(gamesD1.getText().toString()));

            } else if (Integer.parseInt(setsD1.getText().toString()) < Integer.parseInt(setsD2.getText().toString())) {
                d2.setWin(d2.getWin() + 1);
                d2.setSetsW(d2.getSetsW() + Integer.parseInt(setsD2.getText().toString()));
                d2.setSetsL(d2.getSetsL() + Integer.parseInt(setsD1.getText().toString()));
                d2.setGamesW(d2.getGamesW() + Integer.parseInt(gamesD2.getText().toString()));
                d2.setGamesL(d2.getGamesL() + Integer.parseInt(gamesD1.getText().toString()));

                d1.setLose(d1.getLose() + 1);
                d1.setSetsW(d1.getSetsW() + Integer.parseInt(setsD1.getText().toString()));
                d1.setSetsL(d1.getSetsL() + Integer.parseInt(setsD2.getText().toString()));
                d1.setGamesW(d1.getGamesW() + Integer.parseInt(gamesD1.getText().toString()));
                d1.setGamesL(d1.getGamesL() + Integer.parseInt(gamesD2.getText().toString()));
            }

        } else {
            if (Integer.parseInt(gamesD1.getText().toString()) > Integer.parseInt(gamesD2.getText().toString())) {
                d1.setWin(d1.getWin() + 1);
                d1.setGamesW(d1.getGamesW() + Integer.parseInt(gamesD1.getText().toString()));
                d1.setGamesL(d1.getGamesL() + Integer.parseInt(gamesD2.getText().toString()));

                d2.setLose(d2.getLose() + 1);
                d2.setGamesW(d2.getGamesW() + Integer.parseInt(gamesD2.getText().toString()));
                d2.setGamesL(d2.getGamesL() + Integer.parseInt(gamesD1.getText().toString()));

            } else if (Integer.parseInt(gamesD1.getText().toString()) < Integer.parseInt(gamesD2.getText().toString())) {
                d2.setWin(d2.getWin() + 1);
                d2.setGamesW(d2.getGamesW() + Integer.parseInt(gamesD2.getText().toString()));
                d2.setGamesL(d2.getGamesL() + Integer.parseInt(gamesD1.getText().toString()));

                d1.setLose(d1.getLose() + 1);
                d1.setGamesW(d1.getGamesW() + Integer.parseInt(gamesD1.getText().toString()));
                d1.setGamesL(d1.getGamesL() + Integer.parseInt(gamesD2.getText().toString()));
            }
        }
    }

    private void populateSpinners() {
        List<String> list = doublesDAO.getAllDoublesName();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinnerD1.setAdapter(dataAdapter);
        spinnerD2.setAdapter(dataAdapter);
    }

}
