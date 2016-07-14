package com.igorm.regimentoopen.Player;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.igorm.regimentoopen.DAO.Core;
import com.igorm.regimentoopen.DAO.DataBase;
import com.igorm.regimentoopen.Main.MainActivity;
import com.igorm.regimentoopen.R;

public class EditPlayerActivity extends AppCompatActivity {

    private Intent intentMain;
    private String cod;
    private DataBase dbListView, db;
    private Cursor cursor;
    private EditText winET;
    private EditText loseET;
    private EditText gamesWET;
    private EditText gamesLET;
    private EditText setsWET;
    private EditText setsLET;
    private EditText nameET;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.winET = (EditText) findViewById(R.id.editText_edit_win);
        this.loseET = (EditText) findViewById(R.id.editText_edit_lose);
        this.gamesWET = (EditText) findViewById(R.id.editText_edit_games_win);
        this.gamesLET = (EditText) findViewById(R.id.editText_edit_games_lose);
        this.setsWET = (EditText) findViewById(R.id.editText_edit_sets_win);
        this.setsLET = (EditText) findViewById(R.id.editText_edit_sets_lose);
        this.nameET = (EditText) findViewById(R.id.editText_edit_name);

        this.intentMain = new Intent(EditPlayerActivity.this, MainActivity.class);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        dataBaseGET(position);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase dbUp = new DataBase(getBaseContext());
                String name = nameET.getText().toString();
                String win = winET.getText().toString();
                String lose = loseET.getText().toString();
                String gamesW = gamesWET.getText().toString();
                String gamesL = gamesLET.getText().toString();
                String setsW = setsWET.getText().toString();
                String setsL = setsLET.getText().toString();

                if (validateFields(name, win, lose, gamesW, gamesL, setsW, setsL)) {
                    dbUp.updatePlayer(Long.parseLong(cod), name, Integer.parseInt(win), Integer.parseInt(lose), Integer.parseInt(gamesW),
                            Integer.parseInt(gamesL), Integer.parseInt(setsW), Integer.parseInt(setsL));

                    startActivity(intentMain);
                    Toast.makeText(EditPlayerActivity.this, "Dados atualizados!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase dbDel = new DataBase(getBaseContext());
                dbDel.deletePlayer(Long.parseLong(cod));

                startActivity(intentMain);
                Toast.makeText(EditPlayerActivity.this, "Jogador deletado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    private boolean validateFields(String name, String win, String lose, String gamesW, String gamesL, String setsW, String setsL) {
        if (validateName(name) && validateMatches(win, lose) && validateGames(gamesW, gamesL) && validateSets(setsW, setsL)) return true;
        else return false;
    }

    private boolean validateSets(String setsW, String setsL) {
        boolean bool = false;
        if (setsW == null || setsW.equals("")) setsWET.setError("Preencger campo!");
        else if (Integer.parseInt(setsW) < 0) setsWET.setError("Vitórias não podem ser negativas!");
        else if (setsL == null || setsL.equals("")) setsLET.setError("Preencger campo!");
        else if (Integer.parseInt(setsL) < 0) setsLET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateGames(String gamesW, String gamesL) {
        boolean bool = false;
        if (gamesW == null || gamesW.equals("")) gamesWET.setError("Preencger campo!");
        else if (Integer.parseInt(gamesW) < 0) gamesWET.setError("Vitórias não podem ser negativas!");
        else if (gamesL == null || gamesL.equals("")) gamesLET.setError("Preencger campo!");
        else if (Integer.parseInt(gamesL) < 0) gamesLET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateMatches(String win, String lose) {
        boolean bool = false;
        if (win == null || win.equals("")) winET.setError("Preencger campo!");
        else if (Integer.parseInt(win) < 0) winET.setError("Vitórias não podem ser negativas!");
        else if (lose == null || lose.equals("")) loseET.setError("Preencger campo!");
        else if (Integer.parseInt(lose) < 0) loseET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateName(String name) {
        boolean bool = false;
        if (name == null || name.equals("")) nameET.setError("Preencher campo!");
        else bool = true;
        return bool;
    }

    private void dataBaseGET(int position) {
        dbListView = new DataBase(getBaseContext());
        db = new DataBase(this);
        cursor = db.searchPlayer();
        cursor.moveToPosition(position);
        cod = cursor.getString(cursor.getColumnIndexOrThrow(Core.ID));
        Cursor cursorAux = dbListView.searchItemById(Long.parseLong(cod));

        nameET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.NAME)));
        winET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.WIN)));
        loseET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.LOSE)));
        gamesWET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.GAMES_WIN)));
        gamesLET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.GAMES_LOSE)));
        setsWET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.SETS_WIN)));
        setsLET.setText(cursorAux.getString(cursorAux.getColumnIndexOrThrow(Core.SETS_LOSE)));
    }

}
