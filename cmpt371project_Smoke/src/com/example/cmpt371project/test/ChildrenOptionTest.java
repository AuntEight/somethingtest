package com.example.cmpt371project.test;

import com.example.cmpt371project.childrenEdit;
import com.example.cmpt371project.childrenOption;
import com.example.cmpt371project.surveyOption;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class ChildrenOptionTest extends
		ActivityInstrumentationTestCase2<childrenOption> {

	private Solo solo;
	
	public ChildrenOptionTest() {
		super(childrenOption.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		super.setUp();
	}

	public void testTakeSurvey(){
		solo.clickOnButton("Take Survey");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to survey option activity", surveyOption.class);
	    solo.goBackToActivity("childrenOption");	
	}
	
	public void testEditInfo(){
		solo.clickOnButton("Edit Info");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to edit activity", childrenEdit.class);
	    solo.goBackToActivity("childrenOption");	
	}
}
