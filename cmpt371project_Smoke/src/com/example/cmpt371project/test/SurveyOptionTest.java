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
		solo = new Solo(getInstrumentation(), getActivity());
		super.setUp();
	}
	
	public void testBeginSurvey(){
		solo.clickOnButton("Begin Survey");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to begin survey activity", survey.class);
	    solo.goBackToActivity("surveyOption");	
	}

}
