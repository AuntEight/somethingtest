package com.example.cmpt371project.test;

import com.example.cmpt371project.childrenEdit;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class ChildrenEditTest extends
		ActivityInstrumentationTestCase2<childrenEdit> {
			
	private Solo solo;		

	public ChildrenEditTest() {
		super(childrenEdit.class);
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
		//TODO
	}
	
	@Override
	protected void tearDown() throws Exception{

			solo.finishOpenedActivities();
	}

}
