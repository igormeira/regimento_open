package com.igorm.regimentoopen.Tournament;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.igorm.regimentoopen.DAO.DataBase;
import com.igorm.regimentoopen.R;

import java.util.ArrayList;
import java.util.List;

public class TournamentActivity extends AppCompatActivity {

    private List<String> playersList;
    private LinearLayout playersLayout;
    private String tournamentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playersList = new ArrayList<String>();
        DataBase dataBase = new DataBase(getBaseContext());

        //playersLayout = (LinearLayout) findViewById(R.id.linearLayoutPlayers);

        //Radio Buttons
        final RadioButton rb_MataMata = (RadioButton) findViewById(R.id.radioButton_mm);
        final RadioButton rb_PontosCorridos = (RadioButton) findViewById(R.id.radioButton_pc);
        rb_MataMata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_PontosCorridos.setSelected(false);
                tournamentType = rb_MataMata.getText().toString();
            }
        });
        rb_PontosCorridos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_MataMata.setSelected(false);
                tournamentType = rb_PontosCorridos.getText().toString();
            }
        });

        //Spinners
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerP1);
        List<String> list = dataBase.getAllPlayersName();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Button addBT = (Button) findViewById(R.id.tournament_add);
        addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                if (!playersList.contains(player)) {
                    playersList.add(player);
                    Snackbar.make(view, player + " " + "adicionado ao torneio.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //showPlayer(player);
                }
            }
        });

        Button delBT = (Button) findViewById(R.id.tournament_remove);
        delBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                if (playersList.contains(player)) {
                    playersList.remove(player);
                    Snackbar.make(view, player + " " + "removido do torneio.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //removePlayer(player);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TournamentActivity.this, TournamentGamesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Players", (Parcelable) playersList);
                intent.putExtras(bundle);
                intent.putExtra("Type", tournamentType);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

}
