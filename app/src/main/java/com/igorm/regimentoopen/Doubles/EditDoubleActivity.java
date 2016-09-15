package com.igorm.regimentoopen.Doubles;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.igorm.regimentoopen.DAO.DataBaseCore;
import com.igorm.regimentoopen.DAO.DoublesDAO;
import com.igorm.regimentoopen.Main.DoublesActivity;
import com.igorm.regimentoopen.R;

public class EditDoubleActivity extends AppCompatActivity {

    private Intent intentMain;
    private String cod;
    private DoublesDAO dbListView, db;
    private Cursor cursor;
    private EditText winET;
    private EditText loseET;
    private EditText gamesWET;
    private EditText gamesLET;
    private EditText setsWET;
    private EditText setsLET;
    private EditText nameP1ET, nameP2ET;

    public EditDoubleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_double);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.winET = (EditText) findViewById(R.id.editText_edit_win_doubles);
        this.loseET = (EditText) findViewById(R.id.editText_edit_lose_doubles);
        this.gamesWET = (EditText) findViewById(R.id.editText_edit_games_win_doubles);
        this.gamesLET = (EditText) findViewById(R.id.editText_edit_games_lose_doubles);
        this.setsWET = (EditText) findViewById(R.id.editText_edit_sets_win_doubles);
        this.setsLET = (EditText) findViewById(R.id.editText_edit_sets_lose_doubles);
        this.nameP1ET = (EditText) findViewById(R.id.editText_edit_name_doubleP1);
        this.nameP2ET = (EditText) findViewById(R.id.editText_edit_name_doubleP2);

        this.intentMain = new Intent(EditDoubleActivity.this, DoublesActivity.class);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        dataBaseGET(position);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DoublesDAO dbUp = new DoublesDAO(getBaseContext());
                    String name_p1 = nameP1ET.getText().toString();
                    String name_p2 = nameP2ET.getText().toString();
                    String win = winET.getText().toString();
                    String lose = loseET.getText().toString();
                    String gamesW = gamesWET.getText().toString();
                    String gamesL = gamesLET.getText().toString();
                    String setsW = setsWET.getText().toString();
                    String setsL = setsLET.getText().toString();

                    if (validateFields(name_p1, name_p2, win, lose, gamesW, gamesL, setsW, setsL)) {
                        dbUp.updateDouble(Long.parseLong(cod), name_p1, name_p2, Integer.parseInt(win), Integer.parseInt(lose), Integer.parseInt(gamesW),
                                Integer.parseInt(gamesL), Integer.parseInt(setsW), Integer.parseInt(setsL));

                        startActivity(intentMain);
                        Toast.makeText(EditDoubleActivity.this, getString(R.string.updated), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            });
        }

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        if (fab2 != null) {
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DoublesDAO dbDel = new DoublesDAO(getBaseContext());
                    dbDel.deletePlayer(Long.parseLong(cod));

                    startActivity(intentMain);
                    Toast.makeText(EditDoubleActivity.this, String.valueOf(R.string.deleted), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    private boolean validateFields(String name_p1, String name_p2, String win, String lose, String gamesW, String gamesL, String setsW, String setsL) {
        if (validateNames(name_p1, name_p2) && validateMatches(win, lose) && validateGames(gamesW, gamesL) && validateSets(setsW, setsL)) return true;
        else return false;
    }

    private boolean validateSets(String setsW, String setsL) {
        boolean bool = false;
        if (setsW == null || setsW.equals("")) setsWET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(setsW) < 0) setsWET.setError(getString(R.string.negative_win));
        else if (setsL == null || setsL.equals("")) setsLET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(setsL) < 0) setsLET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateGames(String gamesW, String gamesL) {
        boolean bool = false;
        if (gamesW == null || gamesW.equals("")) gamesWET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(gamesW) < 0) gamesWET.setError(getString(R.string.negative_win));
        else if (gamesL == null || gamesL.equals("")) gamesLET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(gamesL) < 0) gamesLET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateMatches(String win, String lose) {
        boolean bool = false;
        if (win == null || win.equals("")) winET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(win) < 0) winET.setError(getString(R.string.negative_win));
        else if (lose == null || lose.equals("")) loseET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(lose) < 0) loseET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateNames(String name_p1, String name_p2) {
        boolean bool = false;
        if (name_p1 == null || name_p1.equals("")) nameP1ET.setError(getString(R.string.blank_field));
        else if (name_p2 == null || name_p2.equals("")) nameP2ET.setError(getString(R.string.blank_field));
        else bool = true;
        return bool;
    }

    private void dataBaseGET(int position) {
        dbListView = new DoublesDAO(getBaseContext());
        db = new DoublesDAO(this);
        cursor = db.searchDouble();
        cursor.moveToPosition(position);
        cod = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseCore.ID));
        Cursor cursorAux = dbListView.searchDoubleById(Long.parseLong(cod));

        nameP1ET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P1)));
        nameP2ET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.NAME_P2)));
        winET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.WIN)));
        loseET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.LOSE)));
        gamesWET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_WIN)));
        gamesLET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.GAMES_LOSE)));
        setsWET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_WIN)));
        setsLET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(DataBaseCore.SETS_LOSE)));
    }

}
