package com.example.cmpt371project.test;
/**
 * Tests for researcher option UI.
 */
import com.example.cmpt371project.R;
import com.example.cmpt371project.researcherEdit;
import com.example.cmpt371project.researcherOption;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

public class ResearcherOptionTest extends ActivityInstrumentationTestCase2<researcherOption> {
	
	private Solo solo;
	private Button editButton;
	
	public ResearcherOptionTest(){
		super(researcherOption.class);

	}
	
	@Override
	protected void setUp() throws Exception {
		// To Do: some parameters may need to show the activity.
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
		editButton = (Button) findViewById(R.id.opRe_edit_but);
		// To Do: inject some information in database.
	}
	
	/**
	 * Test whether dialog is popped when edit is pressed.
	 */
	public void testResearcherOption_EditUser() {
		solo.clickOnView(editButton);
		assertTrue("ERR - Could not jump to Edit Researcher screen in 5s", solo.waitForActivity(researcherEdit.class,5000));
		// To Do: verify info is correctly displayed.
	}
	
	/**
	 * Verify whether injected info is corrected shown.
	 * Stubbed test: since GUI is only skeleton now.
	 */
	public void testResearcherOption_ShowInfo() {
		assertEquals(true, true);
	}
	
	@Override
	protected void tearDown() throws Exception{
		// To Do: delete injected information in database.
		solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}

}
