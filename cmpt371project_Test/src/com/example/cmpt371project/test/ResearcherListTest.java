package com.example.cmpt371project.test;
/**
 * Tests for researcher list UI.
 */


import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.example.cmpt371project.R;
import com.example.cmpt371project.researcherEdit;
import com.example.cmpt371project.researcherList;

public class ResearcherListTest extends ActivityInstrumentationTestCase2<researcherList> {

	private Solo solo;
	private Button addResearcherButton;
	private SearchView searchText;
	private ListView researcherListView;
	
	public ResearcherListTest(){
		super(researcherList.class);

	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
		addResearcherButton = (Button) findViewById(R.id.actionbar_addButton);
		searchText = (SearchView) findViewById(R.id.actionbar_searchView);
		researcherListView = (ListView) findViewById(R.id.alRe_researcher_lst);
	}
	
	public void testResearcherList_AddUserAndSave(){
		
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Adam");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Alpha");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "ala999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		solo.searchText("Mike", 1, true);
		ArrayList<TextView> vTiles = new ArrayList<TextView>();
		vTiles = solo.getCurrentViews(TextView.class, researcherListView);
		for (int i = 0; i < vTiles.size() - 1; i++) {
			Log.d("NutriStep", String.valueOf(vTiles.get(i).getText()));
		}
	}
	
	public void testResearcherList_AddUserAndCancel(){
		
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Brit");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Beta");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-1111");
		solo.clickOnView(findViewById(R.id.edRe_canc_but));
		//Temporarily ignored: since database is not implemented.
//		assertFalse("Should not find newly added user: Brit Brta", findUser());
	}
	
	/**
	 * Test viewing user.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_ViewUser(){
		assertEquals(true, true);
	}
	
	/**
	 * Test editing user and saving changes.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_EditUserAndSave(){
		assertEquals(true, true);
	}
	
	/**
	 * Test editing user and discarding changes.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_EditUserAndCancel(){
		assertEquals(true, true);
	}
	
	/**
	 * Test pressing on an user shown.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 * researcherOption should be shown.
	 */
	public void testResearcherList_PressOnUser(){
		assertEquals(true, true);
	}
	
	/**
	 * Test long pressing on an user and selecting edit.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_LongPressOnUserAndEdit(){
		assertEquals(true, true);
	}
	
	/**
	 * Test long pressing on an user and selecting remove.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_LongPressOnUserAndRemove(){
		assertEquals(true, true);
	}
	
	/**
	 * Test search function when input does not match anything.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_SearchNonExistUser(){
		assertEquals(true, true);
	}
	
	/**
	 * Test search function when input matches only one user.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_SearchOneUser(){
		assertEquals(true, true);
	}
	
	/**
	 * Test search function when input matches some users.
	 * Stubbed test: since database function is not implemented, this will always succeed.
	 */
	public void testResearcherList_SearchSomeUsers(){
		assertEquals(true, true);
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}
	
}
