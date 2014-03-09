package com.example.cmpt371project.test;

import com.example.cmpt371project.childrenEdit;
import com.example.cmpt371project.childrenList;
import com.example.cmpt371project.childrenOption;
import com.example.cmpt371project.locationList;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class ChildrenListTest extends
		ActivityInstrumentationTestCase2<childrenList> {

	private Solo solo;
			
	public ChildrenListTest() {
		super(childrenList.class);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		super.setUp();
	}

	public void testAdd(){
		solo.clickOnButton("Add");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to children_edit activity", childrenEdit.class);
	    solo.goBackToActivity("childrenList");	    
	}
	
	public void testLocation(){
		solo.clickOnButton("Location");
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to location_list activity", locationList.class);
	    solo.goBackToActivity("childrenList");			
	}
	
	public void testTapingChildren(){
		solo.clickInList(0);
		solo.sleep(1000);
		solo.assertCurrentActivity("Should jump to childrenAtLocation activity", childrenOption.class);
	    solo.goBackToActivity("childrenList");	
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
