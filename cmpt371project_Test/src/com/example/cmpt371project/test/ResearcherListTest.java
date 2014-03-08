package com.example.cmpt371project.test;
/**
 * Tests for researcher list UI.
 * All the tests run in admin mode.
 */


import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
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
	
	/**
	 * Test add an user normally.
	 */
	public void testResearcherList_AddUserAndSave(){
		Log.d("NutriStep", "Begin Test: Add a user.");
		Log.d("NutriStep", "Try to jump to Add Researcher screen");
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		
		Log.d("NutriStep", "Filling in new user information.");
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Adam");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Alpha");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "ala999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		
		Log.d("NutriStep", "Save user information.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		solo.hideSoftKeyboard();
		solo.goBack();
		Log.d("NutriStep", "Search user information we just added.");
		assertTrue("User not added - Could not find new user first name", solo.searchText("Adam", 1, true));
		assertTrue("User not added - Could not find new user last name", solo.searchText("Alpha", 1, true));
		Log.d("NutriStep", "Confirm no duplicate.");
		assertFalse("User added but search result incorrect - duplicate result found", solo.searchText("Adam", 2, true));
		assertFalse("User added but search result incorrect - duplicate result found", solo.searchText("Alpha", 2, true));
	}
	
	/**
	 * Test add user guard.
	 */
	public void testResearcherList_CheckBeforeAddUser(){
		Log.d("NutriStep", "Begin Test: Check Before Add User.");
		Log.d("NutriStep", "Try to jump to Add Researcher screen");
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		
		Log.d("NutriStep", "Filling in new user information - no last name.");
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Alice");		
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "ala999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		
		Log.d("NutriStep", "Try to save user information - no last name.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		
		Log.d("NutriStep", "Verify Activity  - no last name:");
		solo.assertCurrentActivity("Should stay on add user screen.", researcherEdit.class);
		
		Log.d("NutriStep", "Filling in new user information - no first name.");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Alpha");
		solo.clearEditText((EditText) findViewById(R.id.edRe_firstname_txt));

		Log.d("NutriStep", "Save user information  - no first name.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		
		Log.d("NutriStep", "Verify Activity  - no first name:");
		solo.assertCurrentActivity("Should stay on add user screen.", researcherEdit.class);
		
		Log.d("NutriStep", "Filling in new user information - no phone num.");
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Alice");
		solo.clearEditText((EditText) findViewById(R.id.edRe_phon_txt));
		
		Log.d("NutriStep", "Save user information  - no phone num.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		
		Log.d("NutriStep", "Verify Activity  - no phone num:");
		solo.assertCurrentActivity("Should stay on add user screen.", researcherEdit.class);
		
		Log.d("NutriStep", "Filling in new user information - no user name.");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.clearEditText((EditText) findViewById(R.id.edRe_userName_txt));
		
		Log.d("NutriStep", "Save user information  - no user name.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		
		Log.d("NutriStep", "Verify Activity  - no user name:");
		solo.assertCurrentActivity("Should stay on add user screen.", researcherEdit.class);
		
		Log.d("NutriStep", "Filling in new user information - no password.");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "ala999");
		solo.clearEditText((EditText) findViewById(R.id.edRe_password_txt));
		
		Log.d("NutriStep", "Save user information  - no password.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		
		Log.d("NutriStep", "Verify Activity  - no password:");
		solo.assertCurrentActivity("Should stay on add user screen.", researcherEdit.class);
	}
	
	/**
	 * Test viewing user.
	 * 
	 */
	public void testResearcherList_ViewUser(){
		Log.d("NutriStep", "Begin Test: View a user.");
		//TODO Add user not using add user screen.
		Log.d("NutriStep", "Add a new user.");
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		
		Log.d("NutriStep", "Filling in new user information.");
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Barret");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Beta");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-111-111-1111");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "beb999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		
		Log.d("NutriStep", "Save user information.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		solo.hideSoftKeyboard();
		solo.goBack();
		
		Log.d("NutriStep", "View user information we just added.");
		Log.d("NutriStep", "Click on it.");
		solo.clickOnText("Barret");
		
		Log.d("NutriStep", "Check information.");
		String userFirstName = ((EditText) findViewById(R.id.edRe_firstname_txt)).getText().toString();
		String userLastName = ((EditText) findViewById(R.id.edRe_lastname_txt)).getText().toString();
		String userPhoneNum = ((EditText) findViewById(R.id.edRe_phon_txt)).getText().toString();
		String userName = ((EditText) findViewById(R.id.edRe_userName_txt)).getText().toString();
		String userPassword = ((EditText) findViewById(R.id.edRe_password_txt)).getText().toString();
		assertEquals("View User - first name incorrect", "Barret", userFirstName);
		assertEquals("View User - last name incorrect", "Beta", userLastName);
		assertEquals("View User - phone num incorrect", "1-111-111-1111", userPhoneNum);
		assertEquals("View User - user name incorrect", "beb999", userName);
		assertEquals("View User - password incorrect", "test", userPassword);
		
		
		
	}
	
	/**
	 * Test editing user and saving changes.
	 * 
	 */
	public void testResearcherList_EditUserAndSave(){
		Log.d("NutriStep", "Begin Test: Edit a user.");
		//TODO Add user not using add user screen.
		Log.d("NutriStep", "Add a new user.");
		solo.clickOnView(addResearcherButton);
		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		
		Log.d("NutriStep", "Filling in new user information.");
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Tera");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Theta");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-222-222-2222");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "tht999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		
		Log.d("NutriStep", "Save user information.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		solo.hideSoftKeyboard();
		solo.goBack();
		
		Log.d("NutriStep", "View user information we just added.");
		Log.d("NutriStep", "Click on it.");
		solo.clickOnText("Tera");
		
		Log.d("NutriStep", "Start editing.");
		solo.clickOnView(findViewById(R.id.edRe_Edit_but));
		
		Log.d("NutriStep", "Input new information.");
		solo.clearEditText((EditText) findViewById(R.id.edRe_firstname_txt));
		solo.clearEditText((EditText) findViewById(R.id.edRe_lastname_txt));
		solo.clearEditText((EditText) findViewById(R.id.edRe_phon_txt));
		solo.clearEditText((EditText) findViewById(R.id.edRe_password_txt));
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "TeraE");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "ThetaE");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-222-222-3333");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test3");
		
		Log.d("NutriStep", "Save user information.");
		solo.clickOnView(findViewById(R.id.edRe_save_but));
		solo.hideSoftKeyboard();
		solo.goBack();
		
		Log.d("NutriStep", "View user information we just edited.");
		Log.d("NutriStep", "Click on it.");
		solo.clickOnText("TeraE");
		
		Log.d("NutriStep", "Check information.");
		String userFirstName = ((EditText) findViewById(R.id.edRe_firstname_txt)).getText().toString();
		String userLastName = ((EditText) findViewById(R.id.edRe_lastname_txt)).getText().toString();
		String userPhoneNum = ((EditText) findViewById(R.id.edRe_phon_txt)).getText().toString();
		String userName = ((EditText) findViewById(R.id.edRe_userName_txt)).getText().toString();
		String userPassword = ((EditText) findViewById(R.id.edRe_password_txt)).getText().toString();
		assertEquals("Edit User - first name not changed", "TeraE", userFirstName);
		assertEquals("Edit User - last name not changed", "ThetaE", userLastName);
		assertEquals("Edit User - phone num not changed", "1-222-222-3333", userPhoneNum);
		assertEquals("Edit User - user name not changed", "tht999", userName);
		assertEquals("Edit User - password changed", "test3", userPassword);
	}
	
	
	/**
	 * Test remove user
	 * Stubbed test: GUI is being revamped. Will test in future test. 
	 */
//	public void testResearcherList_LongPressOnUserAndRemove(){
//		assertEquals(true, true);
//	}

	
// Need to find a way to test searchView.
//
//	/**
//	 * Test search function when input does not match anything.
//	 * Stubbed test: since database function is not implemented, this will always succeed.
//	 */
//	@UiThreadTest
//	public void testResearcherList_SearchNonExistUser(){
//		Log.d("NutriStep", "Begin Test: Search non-existed user.");
//		searchText.setQuery("IAMNOTEXISTED", false);
//		assertTrue("Search Non-Existed User - Find something when I should not.", researcherListView.getChildCount() == 0);
//	}
//	
//	/**
//	 * Test search function when input matches only one user.
//	 * Stubbed test: since database function is not implemented, this will always succeed.
//	 */
//	public void testResearcherList_SearchOnlyOneMatchingUser(){
//		Log.d("NutriStep", "Begin Test: Search Only One Matching User.");
//		//TODO Add user not using add user screen.
//		Log.d("NutriStep", "Add a new user.");
//		solo.clickOnView(addResearcherButton);
//		assertTrue("ERR - Could not jump to Add Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
//		
//		Log.d("NutriStep", "Filling in new user information.");
//		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "OnlyMe");
//		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Existed");
//		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "0-000-000-0001");
//		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "OnlyMe");
//		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "Here");
//		
//		Log.d("NutriStep", "Save user information.");
//		solo.clickOnView(findViewById(R.id.edRe_save_but));
//		solo.hideSoftKeyboard();
//		solo.goBack();
//		
//		Log.d("NutriStep", "Input keyword.");
//		searchText.setQuery("OnlyMe", true);
//		assertTrue("Search Only One Matching User - Find result count other than one.", researcherListView.getChildCount() == 1);
//	}
//	
//	/**
//	 * Test search function when input matches some users.
//	 * Stubbed test: since database function is not implemented, this will always succeed.
//	 */
//	public void testResearcherList_SearchSomeUsers(){
//		assertEquals(true, true);
//	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}
	
}
