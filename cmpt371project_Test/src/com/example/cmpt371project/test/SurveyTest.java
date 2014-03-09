package com.example.cmpt371project.test;

import com.example.cmpt371project.survey;
import com.example.cmpt371project.surveyFinish;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class SurveyTest extends ActivityInstrumentationTestCase2<survey> {
	
	private Solo solo;
	
	public SurveyTest() {
		super(survey.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
		super.setUp();
	}
	
	/**
	 * test next button
	 */
	public void testNext(){
		solo.assertCurrentActivity("Check current activity", survey.class);
		solo.clickOnButton("Next");
		//right now assume the next button is the last button
		solo.assertCurrentActivity("survey should be finished", surveyFinish.class);
		
		//should have a finished button
		//solo.clickOnButton("Finish");
		//solo.assertCurrentActivity("survey should be finished", surveyFinish.class);
	}
	
	/**
	 * test previous button
	 */
//	public void testPrevious(){
		//this function is implemented yet. have no idea how develop will deal with it
//	}
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}

}
