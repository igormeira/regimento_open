package com.igorm.regimentoopen.Doubles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.igorm.regimentoopen.DAO.DoublesDAO;
import com.igorm.regimentoopen.Main.DoublesActivity;
import com.igorm.regimentoopen.R;

public class AddDoubleActivity extends AppCompatActivity {

    private Intent intentMain;
    EditText nameP1AddET, nameP2AddET;
    EditText winAddET;
    EditText loseAddET;
    EditText gamesAddWET;
    EditText gamesAddLET;
    EditText setsAddWET;
    EditText setsAddLET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_double);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentMain = new Intent(AddDoubleActivity.this, DoublesActivity.class);

        this.nameP1AddET = (EditText) findViewById(R.id.editText_add_name_doubleP1);
        this.nameP2AddET = (EditText) findViewById(R.id.editText_add_name_doubleP2);
        this.winAddET = (EditText) findViewById(R.id.editText_add_win_doubles);
        this.loseAddET = (EditText) findViewById(R.id.editText_add_lose_doubles);
        this.gamesAddWET = (EditText) findViewById(R.id.editText_add_games_win_doubles);
        this.gamesAddLET = (EditText) findViewById(R.id.editText_add_games_lose_doubles);
        this.setsAddWET = (EditText) findViewById(R.id.editText_add_sets_win_doubles);
        this.setsAddLET = (EditText) findViewById(R.id.editText_add_sets_lose_doubles);

        addDouble();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    private void addDouble() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DoublesDAO db = new DoublesDAO(getBaseContext());
                    String name_p1 = nameP1AddET.getText().toString();
                    String name_p2 = nameP2AddET.getText().toString();
                    String win = winAddET.getText().toString();
                    String lose = loseAddET.getText().toString();
                    String gamesW = gamesAddWET.getText().toString();
                    String gamesL = gamesAddLET.getText().toString();
                    String setsW = setsAddWET.getText().toString();
                    String setsL = setsAddLET.getText().toString();

                    if (validateFields(name_p1, name_p2, win, lose, gamesW, gamesL, setsW, setsL)) {
                        Double doubles = new Double(name_p1, name_p2, Integer.parseInt(win), Integer.parseInt(lose), Integer.parseInt(gamesW), Integer.parseInt(gamesL),
                                Integer.parseInt(setsW), Integer.parseInt(setsL));

                        db.addDouble(doubles);

                        startActivity(intentMain);
                        Toast.makeText(AddDoubleActivity.this, getString(R.string.added), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }

    private boolean validateFields(String name_p1, String name_p2, String win, String lose, String gamesW, String gamesL, String setsW, String setsL) {
        if (validateNames(name_p1, name_p2) && validateMatches(win, lose) && validateGames(gamesW, gamesL) && validateSets(setsW, setsL)) return true;
        else return false;
    }

    private boolean validateSets(String setsW, String setsL) {
        boolean bool = false;
        if (setsW == null || setsW.equals("")) setsAddWET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(setsW) < 0) setsAddWET.setError(getString(R.string.negative_win));
        else if (setsL == null || setsL.equals("")) setsAddLET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(setsL) < 0) setsAddLET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateGames(String gamesW, String gamesL) {
        boolean bool = false;
        if (gamesW == null || gamesW.equals("")) gamesAddWET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(gamesW) < 0) gamesAddWET.setError(getString(R.string.negative_win));
        else if (gamesL == null || gamesL.equals("")) gamesAddLET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(gamesL) < 0) gamesAddLET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateMatches(String win, String lose) {
        boolean bool = false;
        if (win == null || win.equals("")) winAddET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(win) < 0) winAddET.setError(getString(R.string.negative_win));
        else if (lose == null || lose.equals("")) loseAddET.setError(getString(R.string.blank_field));
        else if (Integer.parseInt(lose) < 0) loseAddET.setError(getString(R.string.negative_lose));
        else bool = true;
        return bool;
    }

    private boolean validateNames(String name_p1, String name_p2) {
        boolean bool = false;
        if (name_p1 == null || name_p1.equals("")) nameP1AddET.setError(getString(R.string.blank_field));
        else if (name_p2 == null || name_p2.equals("")) nameP2AddET.setError(getString(R.string.blank_field));
        else bool = true;
        return bool;
    }
}
