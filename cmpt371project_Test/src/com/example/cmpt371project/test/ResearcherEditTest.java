package com.example.cmpt371project.test;
/**
 * Tests for researcher edit UI.
 */
import com.example.cmpt371project.R;
import com.example.cmpt371project.researcherEdit;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResearcherEditTest extends ActivityInstrumentationTestCase2<researcherEdit> {

	private Solo solo;
	private Button saveButton;
	private Button cancelButton;
	
	public ResearcherEditTest(){
		super(researcherEdit.class);

	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
		saveButton = (Button) findViewById(R.id.edRe_save_but);
		cancelButton = (Button) findViewById(R.id.edRe_canc_but);
	}
	
	/**
	 * Test adding a user and pressing save.
	 */
	public void testResearcherEdit_AddUserAndSave(){
		
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Adam");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Alpha");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.clickOnView(saveButton);
		//Temporarily ignored: since database is not implemented.
		//To Do: check DB.
	}
	
	/**
	 * Test adding a user and pressing cancel.
	 */
	public void testResearcherEdit_AddUserAndCancel(){
		
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Brit");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "Beta");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-1111");
		solo.clickOnView(cancelButton);
		//Temporarily ignored: since database is not implemented.
		//To Do: check DB.
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}
}