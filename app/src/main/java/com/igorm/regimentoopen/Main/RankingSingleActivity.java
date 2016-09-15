package com.igorm.regimentoopen.Main;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.igorm.regimentoopen.DAO.DataBaseCore;
import com.igorm.regimentoopen.DAO.SinglesDAO;
import com.igorm.regimentoopen.R;

public class RankingSingleActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static ListView list;
    private static SimpleCursorAdapter adapterRankingW;
    private static SimpleCursorAdapter adapterRankingG;
    private static SimpleCursorAdapter adapterRankingS;
    private static Context context;
    private static Cursor[] cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //ListViews
        SinglesDAO db = new SinglesDAO(this);
        cursor = db.rankingByPlayer();

        context = getBaseContext();

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                String[] nomeColunasWin = new String [] {DataBaseCore.NAME, DataBaseCore.WIN};
                int[] idViewsWin = new int[] {R.id.namePlayer_win, R.id.ranking_win};
                adapterRankingW = new SimpleCursorAdapter(context, R.layout.ranking_win_view, cursor[0],
                        nomeColunasWin, idViewsWin, 0);
                list = (ListView)rootView.findViewById(R.id.listView_ranking);
                list.setAdapter(adapterRankingW);
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                String[] nomeColunasGames = new String [] {DataBaseCore.NAME, DataBaseCore.GAMES_WIN};
                int[] idViewsGames = new int[] {R.id.namePlayer_games, R.id.ranking_games};
                adapterRankingG = new SimpleCursorAdapter(context, R.layout.ranking_games_view, cursor[1],
                        nomeColunasGames, idViewsGames, 0);
                list = (ListView)rootView.findViewById(R.id.listView_ranking);
                list.setAdapter(adapterRankingG);
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                String[] nomeColunasSets = new String [] {DataBaseCore.NAME, DataBaseCore.SETS_WIN};
                int[] idViewsSets = new int[] {R.id.namePlayer_sets, R.id.ranking_sets};
                adapterRankingS = new SimpleCursorAdapter(context, R.layout.ranking_sets_view, cursor[2],
                        nomeColunasSets, idViewsSets, 0);
                list = (ListView)rootView.findViewById(R.id.listView_ranking);
                list.setAdapter(adapterRankingS);
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.matches).toUpperCase();
                case 1:
                    return getString(R.string.games).toUpperCase();
                case 2:
                    return getString(R.string.sets).toUpperCase();
            }
            return null;
        }
    }
}
