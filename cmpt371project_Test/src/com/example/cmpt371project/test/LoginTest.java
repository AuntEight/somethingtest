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
	public void testLoginIn(){
		
		//check current login activity
		solo.assertCurrentActivity("Check on login activity", Login.class);
		
		//input nothing both username and password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on login activity", Login.class);
		solo.goBackToActivity("Login");

		//input empty username 
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(password, "123");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on login activity", Login.class);
		solo.goBackToActivity("Login");
		
		//input empty password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "adb");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on login activity", Login.class);
		solo.goBackToActivity("Login");
		
		//input super long character
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "abcsdflaslkdjflsjdiofelrjlsjfd");
		solo.enterText(password, "sdfsdfsdsdfsdf");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on login activity", Login.class);
		solo.goBackToActivity("Login");
		
		//input a user name who are not in database
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "yyy345");
		solo.enterText(password, "sdf");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on login activity", Login.class);
		solo.goBackToActivity("Login");
			
		//input a user name that exists in database, with wrong password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "admin");
		solo.enterText(password, "123456");
		solo.clickOnButton("Log in");
		solo.sleep(1000);
		solo.assertCurrentActivity("Login incorrectly", Login.class);
		solo.goBackToActivity("Login");	
		

		//input correct admin username and password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "admin");
		solo.enterText(password, "admin");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on Admin activity", admin.class);
		solo.goBackToActivity("Login");	
		
		//input correct researcher username and password
		solo.clearEditText(userID);
		solo.clearEditText(password);
		solo.enterText(userID, "res");
		solo.enterText(password, "res");
		solo.clickOnButton("Log in");
		solo.sleep(500);
		solo.assertCurrentActivity("Check on researcher activity", researcher.class);
		solo.goBackToActivity("Login");			
		
	}
	
	/**
	 * test select language button
	 */
	public void testSelectLanguage(){
		
	}
	
	/**
	 * test update button
	 */
	public void testUpdate(){
		
	}
	

	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
