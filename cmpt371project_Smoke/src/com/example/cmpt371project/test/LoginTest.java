package com.example.cmpt371project.test;

import com.example.cmpt371project.R;
import com.example.cmpt371project.Login;
import com.example.cmpt371project.admin;
import com.example.cmpt371project.researcher;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class LoginTest extends ActivityInstrumentationTestCase2<Login> {
	
	private Solo solo;
	private EditText userID;
	private EditText password;
	
	
	public LoginTest() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
		userID = (EditText) solo.getView(R.id.userNameInput);
		password = (EditText) solo.getView(R.id.passWordInput);
		super.setUp();
	}
	
	/**
	 * try to type in all kinds different stuff into text field
	 */
	public void testLoginBasic(){
		
		//check current login activity
		solo.assertCurrentActivity("Check on login activity", Login.class);
		
		//input nothing both username and password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.clickOnButton("Log in");
		solo.sleep(1000);
		solo.assertCurrentActivity("Check on login activity", Login.class);

		//input correct admin username and password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "admin");
		solo.enterText(password, "admin");
		solo.clickOnButton("Log in");
		solo.sleep(1000);
		solo.assertCurrentActivity("Check on Admin activity", admin.class);
		solo.clickOnButton("Log Out");
		solo.assertCurrentActivity("Check on Admin activity", Login.class);
	}
	
	public void testLogin(){
		//input correct researcher username and password
		solo.assertCurrentActivity("Check on Admin activity", Login.class);
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "res");
		solo.enterText(password, "res");
		solo.clickOnButton("Log in");
		solo.sleep(1000);
		solo.assertCurrentActivity("Check on researcher activity", researcher.class);	
		solo.clickOnButton("Log Out");
		solo.assertCurrentActivity("Check on Admin activity", Login.class);
	}

	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
