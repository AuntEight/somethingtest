package com.example.cmpt371project.test;

import com.example.cmpt371project.locationEdit;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LocationEditTest extends
		ActivityInstrumentationTestCase2<locationEdit> {
			
	private Solo solo;		

	public LocationEditTest() {
		super(locationEdit.class);
	}

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
		
		super.setUp();
	}
	
	/**
	 * try to save all wired data
	 */
	public void testSave(){
		solo.enterText(0, "UUU");
		solo.enterText(1,"123 Ave");
		solo.enterText(2,"");
		solo.clickOnButton("Save");
		//TODO need to check database
	}
	
//	/**
//	 * test remove button, it is not implemented yet
//	 */
//	public void testRemove(){
		
//	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}

}
