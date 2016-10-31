package com.igorm.regimentoopen.Main;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.igorm.regimentoopen.DAO.DataBaseCore;
import com.igorm.regimentoopen.DAO.SinglesDAO;
import com.igorm.regimentoopen.Player.AddPlayerActivity;
import com.igorm.regimentoopen.Player.EditPlayerActivity;
import com.igorm.regimentoopen.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SinglesDAO db = new SinglesDAO(this);
        final Cursor cursor = db.searchPlayer();

        String[] nomeColunas = new String [] {DataBaseCore.NAME, DataBaseCore.WIN, DataBaseCore.LOSE, DataBaseCore.GAMES_WIN, DataBaseCore.GAMES_LOSE, DataBaseCore.SETS_WIN, DataBaseCore.SETS_LOSE};
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
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentEdit = new Intent(MainActivity.this, AddPlayerActivity.class);
                    intentEdit.putExtra("status", "add");
                    startActivity(intentEdit);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.doubles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.players) {
            onBackPressed();
        } else if (id == R.id.add_match_single) {
            final Intent match_intent = new Intent(this, MatchSingleActivity.class);
            startActivity(match_intent);
            return true;
        } else if (id == R.id.ranking_single) {
            final Intent ranking_intent = new Intent(this, RankingSingleActivity.class);
            startActivity(ranking_intent);
            return true;
        } else if (id == R.id.double_players) {
            final Intent double_intent = new Intent(this, DoublesActivity.class);
            startActivity(double_intent);
            return true;
        } else if (id == R.id.add_match_doubles) {
            final Intent match_intent = new Intent(this, MatchDoublesActivity.class);
            startActivity(match_intent);
            return true;
        } else if (id == R.id.ranking_doubles) {
            final Intent ranking_intent = new Intent(this, RankingDoublesActivity.class);
            startActivity(ranking_intent);
            return true;
        } else if (id == R.id.about) {
            final Intent about_intent = new Intent(this, AboutActivity.class);
            startActivity(about_intent);
            return true;
        } else if (id == R.id.rate) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
