package com.example.cmpt371project.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.cmpt371project.childrenAtLocation;
import com.robotium.solo.Solo;

/**
 * Tests for children at location UI.
 * All the tests run in admin mode.
 */
public class ChildrenAtLocationTest extends ActivityInstrumentationTestCase2<childrenAtLocation>{
	
	private Solo solo;
	//TODO waiting for the code of children_at_Location to test. 
	 
	
	public ChildrenAtLocationTest() {
		super(childrenAtLocation.class);
	}
	@Override
	protected void setUp() throws Exception{
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();
	}
	
	
	public void testChildrenAtLocation(){
		//TODO waiting for the code of children_at_Location to test.
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
