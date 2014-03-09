package com.example.cmpt371project.test;

import com.example.cmpt371project.survey;
import com.example.cmpt371project.surveyOption;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class SurveyOptionTest extends
		ActivityInstrumentationTestCase2<surveyOption> {

	private Solo solo;		
			
	public SurveyOptionTest() {
		super(surveyOption.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
		super.setUp();
	}
	
	/**
	 * test begin the survey
	 */
	public void testBeginSurvey(){
		solo.assertCurrentActivity("running the wrong activity", surveyOption.class);
		
		solo.clickOnButton("Begin Survey");
		solo.assertCurrentActivity("Should be running survey activity", survey.class);
	}
	
	/**
	 * test previous survey
	 */
//	public void testPreviousSurvey(){
	//this function is not clear yet	
//	}
	
	/**
	 * test cancel
	 */
	//app will fail it right now
	public void testCancel(){
		solo.clickOnButton("Cancel");
		assertTrue("Cancel button is not working", solo.getCurrentActivity().getClass() != surveyOption.class);   
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}

}
