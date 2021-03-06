package com.example.cmpt371project.test;
/**
 * Tests for researcher edit UI.
 */
import com.example.cmpt371project.R;
import com.example.cmpt371project.researcherEdit;
import com.robotium.solo.Solo;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResearcherEditTest extends ActivityInstrumentationTestCase2<researcherEdit> {

	private Solo solo;
	private Button saveButton;
	
	public ResearcherEditTest(){
		super(researcherEdit.class);

	}
	
	@Override
	protected void setUp() throws Exception {
		Intent addIntent = new Intent();
      	addIntent.putExtra("from", "res_add");
      	setActivityIntent(addIntent);
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
		saveButton = (Button) findViewById(R.id.edRe_save_but);
	}
	
	/**
	 * Test adding a user and pressing save.
	 */
	public void testResearcherEdit_AddUserAndSave(){
		
		solo.enterText((EditText) findViewById(R.id.edRe_firstname_txt), "Test");
		solo.enterText((EditText) findViewById(R.id.edRe_lastname_txt), "EditScreen");
		solo.enterText((EditText) findViewById(R.id.edRe_phon_txt), "1-000-000-0000");
		solo.enterText((EditText) findViewById(R.id.edRe_userName_txt), "ted999");
		solo.enterText((EditText) findViewById(R.id.edRe_password_txt), "test");
		solo.clickOnView(saveButton);
		//TODO: Access DB directly
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}
}
