package com.igorm.regimentoopen.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.igorm.regimentoopen.DAO.DataBase;
import com.igorm.regimentoopen.Main.MainActivity;
import com.igorm.regimentoopen.R;

public class AddPlayerActivity extends AppCompatActivity {

    private Intent intentMain;
    EditText nameAddET;
    EditText winAddET;
    EditText loseAddET;
    EditText gamesAddWET;
    EditText gamesAddLET;
    EditText setsAddWET;
    EditText setsAddLET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentMain = new Intent(AddPlayerActivity.this, MainActivity.class);

        Intent intent = getIntent();
        String status = intent.getStringExtra("status");

        this.nameAddET = (EditText) findViewById(R.id.editText_add_name);
        this.winAddET = (EditText) findViewById(R.id.editText_add_win);
        this.loseAddET = (EditText) findViewById(R.id.editText_add_lose);
        this.gamesAddWET = (EditText) findViewById(R.id.editText_add_games_win);
        this.gamesAddLET = (EditText) findViewById(R.id.editText_add_games_lose);
        this.setsAddWET = (EditText) findViewById(R.id.editText_add_sets_win);
        this.setsAddLET = (EditText) findViewById(R.id.editText_add_sets_lose);

        if (status.equals("add")) {
            addPlayer();
        }
        else {
            finish();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    private void addPlayer() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBase db = new DataBase(getBaseContext());
                String name = nameAddET.getText().toString();
                String win = winAddET.getText().toString();
                String lose = loseAddET.getText().toString();
                String gamesW = gamesAddWET.getText().toString();
                String gamesL = gamesAddLET.getText().toString();
                String setsW = setsAddWET.getText().toString();
                String setsL = setsAddLET.getText().toString();

                if (validateFields(name, win, lose, gamesW, gamesL, setsW, setsL)) {
                    Player player = new Player(name, Integer.parseInt(win), Integer.parseInt(lose), Integer.parseInt(gamesW), Integer.parseInt(gamesL),
                            Integer.parseInt(setsW), Integer.parseInt(setsL));

                    db.addPlayer(player);

                    startActivity(intentMain);
                    Toast.makeText(AddPlayerActivity.this, "Jogador adicionado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean validateFields(String name, String win, String lose, String gamesW, String gamesL, String setsW, String setsL) {
        if (validateName(name) && validateMatches(win, lose) && validateGames(gamesW, gamesL) && validateSets(setsW, setsL)) return true;
        else return false;
    }

    private boolean validateSets(String setsW, String setsL) {
        boolean bool = false;
        if (setsW == null || setsW.equals("")) setsAddWET.setError("Preencger campo!");
        else if (Integer.parseInt(setsW) < 0) setsAddWET.setError("Vitórias não podem ser negativas!");
        else if (setsL == null || setsL.equals("")) setsAddLET.setError("Preencger campo!");
        else if (Integer.parseInt(setsL) < 0) setsAddLET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateGames(String gamesW, String gamesL) {
        boolean bool = false;
        if (gamesW == null || gamesW.equals("")) gamesAddWET.setError("Preencger campo!");
        else if (Integer.parseInt(gamesW) < 0) gamesAddWET.setError("Vitórias não podem ser negativas!");
        else if (gamesL == null || gamesL.equals("")) gamesAddLET.setError("Preencger campo!");
        else if (Integer.parseInt(gamesL) < 0) gamesAddLET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateMatches(String win, String lose) {
        boolean bool = false;
        if (win == null || win.equals("")) winAddET.setError("Preencger campo!");
        else if (Integer.parseInt(win) < 0) winAddET.setError("Vitórias não podem ser negativas!");
        else if (lose == null || lose.equals("")) loseAddET.setError("Preencger campo!");
        else if (Integer.parseInt(lose) < 0) loseAddET.setError("Derrotas não podem ser negativas!");
        else bool = true;
        return bool;
    }

    private boolean validateName(String name) {
        boolean bool = false;
        if (name == null || name.equals("")) nameAddET.setError("Preencher campo!");
        else bool = true;
        return bool;
    }

}
