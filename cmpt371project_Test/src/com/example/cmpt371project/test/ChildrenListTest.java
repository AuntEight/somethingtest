package com.example.cmpt371project.test;

import com.example.cmpt371project.childrenList;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class ChildrenListTest extends
		ActivityInstrumentationTestCase2<childrenList> {
			
	private Solo solo;

	public ChildrenListTest() {
		super(childrenList.class);
	}

	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		super.setUp();
	}
	
	/**
	 * test add 
	 */
	public void testAdd(){
		
	}
	
	/**
	 * test children button
	 */
	public void testChildren(){
		//TODO
	}
	
	/**
	 * test tap on a Children button
	 */
	public void testTapingChildren(){
		//TODO
	}
	
	/**
	 * test search field
	 */

	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}
}
