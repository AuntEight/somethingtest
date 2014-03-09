package com.example.cmpt371project.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

import com.example.cmpt371project.Login;
import com.example.cmpt371project.R;
import com.example.cmpt371project.admin;
import com.example.cmpt371project.childrenList;
import com.example.cmpt371project.locationList;
import com.example.cmpt371project.researcherList;
import com.robotium.solo.Solo;

public class AdminTest extends
ActivityInstrumentationTestCase2<admin>{

	private Solo solo;
	private Button userButton;
	private Button childrenButton;
	private Button locationButton;
	private Button logOutButton;
	
	public AdminTest() {
		super(admin.class);
	}
	

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
		
		userButton= (Button) findViewById(R.id.admin_user_but);
		childrenButton= (Button) findViewById(R.id.admin_child_but);
		locationButton= (Button) findViewById(R.id.admin_loca_but);
		logOutButton= (Button) findViewById(R.id.admin_logO_but);
	}
	
	/**
	 * Test if the button is navigating user between activities correctly.
	 * Since the GUI is skeleton now, this button is not tested:
	 * Stats
	 */
	public void testAdmin_Navigation(){
		
		solo.clickOnView(userButton);
		solo.sleep(500);
		solo.assertCurrentActivity("ERR - Could not jump to researcher list.", researcherList.class);
		solo.hideSoftKeyboard();
		solo.goBack();
		solo.goBack();
		solo.assertCurrentActivity("ERR - Could not jump back from researcher list.", admin.class);
		
		solo.clickOnView(childrenButton);
		solo.sleep(500);
		solo.assertCurrentActivity("ERR - Could not jump to children list.", childrenList.class);
		solo.hideSoftKeyboard();
		solo.goBack();
		solo.assertCurrentActivity("ERR - Could not jump back from children list.", admin.class);
		
		solo.clickOnView(locationButton);
		solo.sleep(500);
		solo.assertCurrentActivity("ERR - Could not jump to children list.", locationList.class);
		solo.hideSoftKeyboard();
		solo.goBack();
		solo.assertCurrentActivity("ERR - Could not jump back from children list.", admin.class);

		solo.clickOnView(logOutButton);
		solo.sleep(500);
		solo.assertCurrentActivity("ERR - Could not jump to login screen.", Login.class);

	}

	/**
	 * Test whether the database is updated after clicking Update.
	 * Stubbed test: since function is not implemented, this will always succeed.
	 */
	
	public void testResearcher_Update() {
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
