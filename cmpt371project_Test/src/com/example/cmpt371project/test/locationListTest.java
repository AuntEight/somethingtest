package com.example.cmpt371project.test;

import com.example.cmpt371project.childrenAtLocation;
import com.example.cmpt371project.childrenList;
import com.example.cmpt371project.locationEdit;
import com.example.cmpt371project.locationList;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class LocationListTest extends
		ActivityInstrumentationTestCase2<locationList> {
			
	private Solo solo;

	public LocationListTest() {
		super(locationList.class);
	}

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		super.setUp();
	}
	
	/**
	 * test add button
	 */
	public void testAdd(){
		solo.clickOnButton("Add");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to Location_edit activity", locationEdit.class);
	    solo.goBackToActivity("locationList");	    
	}
	
	/**
	 * test children button
	 */
	public void testChildren(){
		solo.clickOnButton("Children");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to children_list activity", childrenList.class);
	    solo.goBackToActivity("locationList");			
	}
	
	/**
	 * test tap on a location button
	 */
	public void testTapingLocation(){
		solo.clickInList(1);
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to childrenAtLocation activity", childrenAtLocation.class);
	    solo.goBackToActivity("locationList");	
	}
	
	/**
	 * test search field
	 */
	public void testSearchFiled(){
		solo.enterText(0, "Daycare0");
		//TODO after it is connected to the database
	}

	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
