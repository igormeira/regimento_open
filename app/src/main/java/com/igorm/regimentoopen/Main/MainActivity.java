package com.igorm.regimentoopen.Main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.igorm.regimentoopen.DAO.Core;
import com.igorm.regimentoopen.DAO.DataBase;
import com.igorm.regimentoopen.Player.AddPlayerActivity;
import com.igorm.regimentoopen.Player.EditPlayerActivity;
import com.igorm.regimentoopen.R;
import com.igorm.regimentoopen.Tournament.TournamentActivity;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBase db = new DataBase(this);
        final Cursor cursor = db.searchPlayer();

        String[] nomeColunas = new String [] {Core.NAME, Core.WIN, Core.LOSE, Core.GAMES_WIN, Core.GAMES_LOSE, Core.SETS_WIN, Core.SETS_LOSE};
        int[] idViews = new int[] {R.id.namePlayer, R.id.win, R.id.lose, R.id.games_win, R.id.games_lose, R.id.sets_win, R.id.sets_lose};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.player_view, cursor,
                nomeColunas, idViews, 0);

        list = (ListView)findViewById(R.id.listView_player);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentEdit = new Intent(MainActivity.this, EditPlayerActivity.class);
                intentEdit.putExtra("status", "update");
                intentEdit.putExtra("position", position);
                startActivity(intentEdit);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEdit = new Intent(MainActivity.this, AddPlayerActivity.class);
                intentEdit.putExtra("status", "add");
                startActivity(intentEdit);
            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_ranking:
                final Intent ranking_intent = new Intent(this, RankingActivity.class);
                startActivity(ranking_intent);
                return true;
            case R.id.action_tournament:
                final Intent tournament_intent = new Intent(this, TournamentActivity.class);
                startActivity(tournament_intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
