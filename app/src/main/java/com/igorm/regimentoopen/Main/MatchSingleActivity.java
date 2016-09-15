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
import com.igorm.regimentoopen.DAO.SinglesDAO;
import com.igorm.regimentoopen.Player.Player;
import com.igorm.regimentoopen.R;

import java.util.List;

public class MatchSingleActivity extends AppCompatActivity {

    private Spinner spinnerP1, spinnerP2;
    private EditText gamesP1, gamesP2, setsP1, setsP2;
    private SinglesDAO singlesDAO;
    private Player p1, p2;
    private Intent intentMain;

    private String cod;
    private SinglesDAO dbListView, db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentMain = new Intent(MatchSingleActivity.this, MainActivity.class);

        spinnerP1 = (Spinner) findViewById(R.id.spinner_p1);
        spinnerP2 = (Spinner) findViewById(R.id.spinner_p2);

        gamesP1 = (EditText) findViewById(R.id.editText_games_p1);
        gamesP2 = (EditText) findViewById(R.id.editText_games_p2);
        setsP1 = (EditText) findViewById(R.id.editText_sets_p1);
        setsP2 = (EditText) findViewById(R.id.editText_sets_p2);

        singlesDAO = new SinglesDAO(getBaseContext());

        populateSpinners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveMatchResult();
                }
            });
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void saveMatchResult() {
        saveP1();
        saveP2();

        updatePlayers();
        save();
    }

    private void saveP2() {
        dbListView = new SinglesDAO(getBaseContext());
        db = new SinglesDAO(this);
        cursor = db.searchPlayer();
        cursor.moveToPosition(spinnerP2.getSelectedItemPosition());
        cod = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCore.ID));
        Cursor cursorAux = dbListView.searchItemById(Long.parseLong(cod));

        p2 = new Player(Long.parseLong(cod), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME)), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_LOSE))));
    }

    private void saveP1() {
        dbListView = new SinglesDAO(getBaseContext());
        db = new SinglesDAO(this);
        cursor = db.searchPlayer();
        cursor.moveToPosition(spinnerP1.getSelectedItemPosition());
        cod = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCore.ID));
        Cursor cursorAux = dbListView.searchItemById(Long.parseLong(cod));

        p1 = new Player(Long.parseLong(cod), cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME)), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_LOSE))), Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_WIN))),
                Integer.parseInt(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_LOSE))));
    }

    private void save() {
        SinglesDAO dbUp = new SinglesDAO(getBaseContext());

        dbUp.updatePlayer(p1.getId(), p1.getName(), p1.getWin(), p1.getLose(), p1.getGamesW(),
                p1.getGamesL(), p1.getSetsW(), p1.getSetsL());
        
        dbUp.updatePlayer(p2.getId(), p2.getName(), p2.getWin(), p2.getLose(), p2.getGamesW(),
                p2.getGamesL(), p2.getSetsW(), p2.getSetsL());

        startActivity(intentMain);
        Toast.makeText(MatchSingleActivity.this, getString(R.string.updated), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updatePlayers() {
        if (!setsP1.getText().toString().equals("0") || !setsP2.getText().toString().equals("0")) {
            if (Integer.parseInt(setsP1.getText().toString()) > Integer.parseInt(setsP2.getText().toString())) {
                p1.setWin(p1.getWin() + 1);
                p1.setSetsW(p1.getSetsW() + Integer.parseInt(setsP1.getText().toString()));
                p1.setSetsL(p1.getSetsL() + Integer.parseInt(setsP2.getText().toString()));
                p1.setGamesW(p1.getGamesW() + Integer.parseInt(gamesP1.getText().toString()));
                p1.setGamesL(p1.getGamesL() + Integer.parseInt(gamesP2.getText().toString()));
                
                p2.setLose(p2.getLose() + 1);
                p2.setSetsW(p2.getSetsW() + Integer.parseInt(setsP2.getText().toString()));
                p2.setSetsL(p2.getSetsL() + Integer.parseInt(setsP1.getText().toString()));
                p2.setGamesW(p2.getGamesW() + Integer.parseInt(gamesP2.getText().toString()));
                p2.setGamesL(p2.getGamesL() + Integer.parseInt(gamesP1.getText().toString()));
                
            } else if (Integer.parseInt(setsP1.getText().toString()) < Integer.parseInt(setsP2.getText().toString())) {
                p2.setWin(p2.getWin() + 1);
                p2.setSetsW(p2.getSetsW() + Integer.parseInt(setsP2.getText().toString()));
                p2.setSetsL(p2.getSetsL() + Integer.parseInt(setsP1.getText().toString()));
                p2.setGamesW(p2.getGamesW() + Integer.parseInt(gamesP2.getText().toString()));
                p2.setGamesL(p2.getGamesL() + Integer.parseInt(gamesP1.getText().toString()));

                p1.setLose(p1.getLose() + 1);
                p1.setSetsW(p1.getSetsW() + Integer.parseInt(setsP1.getText().toString()));
                p1.setSetsL(p1.getSetsL() + Integer.parseInt(setsP2.getText().toString()));
                p1.setGamesW(p1.getGamesW() + Integer.parseInt(gamesP1.getText().toString()));
                p1.setGamesL(p1.getGamesL() + Integer.parseInt(gamesP2.getText().toString()));
            }
            
        } else {
            if (Integer.parseInt(gamesP1.getText().toString()) > Integer.parseInt(gamesP2.getText().toString())) {
                p1.setWin(p1.getWin() + 1);
                p1.setGamesW(p1.getGamesW() + Integer.parseInt(gamesP1.getText().toString()));
                p1.setGamesL(p1.getGamesL() + Integer.parseInt(gamesP2.getText().toString()));

                p2.setLose(p2.getLose() + 1);
                p2.setGamesW(p2.getGamesW() + Integer.parseInt(gamesP2.getText().toString()));
                p2.setGamesL(p2.getGamesL() + Integer.parseInt(gamesP1.getText().toString()));
            
            } else if (Integer.parseInt(gamesP1.getText().toString()) < Integer.parseInt(gamesP2.getText().toString())) {
                p2.setWin(p2.getWin() + 1);
                p2.setGamesW(p2.getGamesW() + Integer.parseInt(gamesP2.getText().toString()));
                p2.setGamesL(p2.getGamesL() + Integer.parseInt(gamesP1.getText().toString()));

                p1.setLose(p1.getLose() + 1);
                p1.setGamesW(p1.getGamesW() + Integer.parseInt(gamesP1.getText().toString()));
                p1.setGamesL(p1.getGamesL() + Integer.parseInt(gamesP2.getText().toString()));
            }
        }
    }

    private void populateSpinners() {
        List<String> list = singlesDAO.getAllPlayersName();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinnerP1.setAdapter(dataAdapter);
        spinnerP2.setAdapter(dataAdapter);
    }
}
