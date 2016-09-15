package com.igorm.regimentoopen.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class Test1 extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.igorm.regimentoopen.Main.SplashScreenActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public Test1() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.igorm.regimentoopen.Main.SplashScreenActivity'
		solo.waitForActivity("SplashScreenActivity", 2000);
        //Wait for activity: 'com.igorm.regimentoopen.Main.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
        //Sleep for 15237 milliseconds
		solo.sleep(15237);
        //Click on ImageView
		solo.clickOnView(solo.getView("fab"));
        //Wait for activity: 'com.igorm.regimentoopen.Player.AddPlayerActivity'
		assertTrue("AddPlayerActivity is not found!", solo.waitForActivity("AddPlayerActivity"));
        //Sleep for 7393 milliseconds
		solo.sleep(7393);
        //Enter the text: 'Igor'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_name"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_name"), "Igor");
        //Sleep for 1332 milliseconds
		solo.sleep(1332);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_win"));
        //Sleep for 10579 milliseconds
		solo.sleep(10579);
        //Enter the text: '5'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_win"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_win"), "5");
        //Sleep for 1418 milliseconds
		solo.sleep(1418);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_lose"));
        //Sleep for 1580 milliseconds
		solo.sleep(1580);
        //Enter the text: '2'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_lose"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_lose"), "2");
        //Sleep for 3188 milliseconds
		solo.sleep(3188);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_games_win"));
        //Sleep for 35218 milliseconds
		solo.sleep(35218);
        //Enter the text: '50'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_games_win"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_games_win"), "50");
        //Sleep for 1320 milliseconds
		solo.sleep(1320);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_games_lose"));
        //Sleep for 4379 milliseconds
		solo.sleep(4379);
        //Enter the text: '30'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_games_lose"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_games_lose"), "30");
        //Sleep for 1095 milliseconds
		solo.sleep(1095);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_sets_win"));
        //Sleep for 12247 milliseconds
		solo.sleep(12247);
        //Enter the text: '15'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_sets_win"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_sets_win"), "15");
        //Sleep for 836 milliseconds
		solo.sleep(836);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("editText_add_sets_lose"));
        //Sleep for 2913 milliseconds
		solo.sleep(2913);
        //Enter the text: '9'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_add_sets_lose"));
		solo.enterText((android.widget.EditText) solo.getView("editText_add_sets_lose"), "9");
        //Sleep for 2942 milliseconds
		solo.sleep(2942);
        //Click on ImageView
		solo.clickOnView(solo.getView("fab", 1));
        //Wait for activity: 'com.igorm.regimentoopen.Main.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
        //Sleep for 3774 milliseconds
		solo.sleep(3774);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("action_ranking", 1));
        //Wait for activity: 'com.igorm.regimentoopen.Main.RankingActivity'
		assertTrue("RankingActivity is not found!", solo.waitForActivity("RankingActivity"));
        //Sleep for 1852 milliseconds
		solo.sleep(1852);
        //Click on GAMES
		solo.clickOnView(solo.getView(android.widget.LinearLayout.class, 5));
        //Sleep for 1671 milliseconds
		solo.sleep(1671);
        //Click on SETS
		solo.clickOnView(solo.getView(android.widget.LinearLayout.class, 6));
        //Sleep for 3053 milliseconds
		solo.sleep(3053);
        //Click on VITÓRIAS
		solo.clickOnView(solo.getView(android.widget.LinearLayout.class, 4));
        //Sleep for 1649 milliseconds
		solo.sleep(1649);
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Wait for activity: 'com.igorm.regimentoopen.Main.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
        //Sleep for 2119 milliseconds
		solo.sleep(2119);
        //Click on Igor Vitórias:  5 Derrotas:  2 50 Games:  15 Sets:  9  /   /  30
		solo.clickInList(1, 0);
        //Wait for activity: 'com.igorm.regimentoopen.Player.EditPlayerActivity'
		assertTrue("EditPlayerActivity is not found!", solo.waitForActivity("EditPlayerActivity"));
        //Sleep for 1710 milliseconds
		solo.sleep(1710);
        //Click on editText_edit_win
		solo.clickOnView(solo.getView("editText_edit_win"));
        //Sleep for 2619 milliseconds
		solo.sleep(2619);
        //Enter the text: '7'
		solo.clearEditText((android.widget.EditText) solo.getView("editText_edit_win"));
		solo.enterText((android.widget.EditText) solo.getView("editText_edit_win"), "7");
        //Sleep for 5494 milliseconds
		solo.sleep(5494);
        //Click on ImageView
		solo.clickOnView(solo.getView("fab", 2));
        //Wait for activity: 'com.igorm.regimentoopen.Main.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
        //Sleep for 5680 milliseconds
		solo.sleep(5680);
        //Click on Igor Vitórias:  7 Derrotas:  2 50 Games:  15 Sets:  9  /   /  30
		solo.clickInList(1, 0);
        //Wait for activity: 'com.igorm.regimentoopen.Player.EditPlayerActivity'
		assertTrue("EditPlayerActivity is not found!", solo.waitForActivity("EditPlayerActivity"));
        //Sleep for 1239 milliseconds
		solo.sleep(1239);
        //Click on ImageView
		solo.clickOnView(solo.getView("fab2"));
        //Wait for activity: 'com.igorm.regimentoopen.Main.MainActivity'
		assertTrue("MainActivity is not found!", solo.waitForActivity("MainActivity"));
	}
}
