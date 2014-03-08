package com.example.cmpt371project.test;
/**
 * Tests for Children option UI.
 * Test is possible to be obsoleted in coming milestone/ID, since the activity is not used anymore.
 * TODO: Remove unnecessary test and activity. 
 */
import com.example.cmpt371project.R;
import com.example.cmpt371project.childrenEdit;
import com.example.cmpt371project.childrenOption;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

public class ChildrenOptionTest extends ActivityInstrumentationTestCase2<childrenOption> {
	
	private Solo solo;

	
	public ChildrenOptionTest(){
		super(childrenOption.class);

	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
		super.setUp();

	}
	

	public void testChildrenOption_TakeSurvey() {

	}
	

	public void testChildrenOption_ViewStats() {
		//TODO
	}
	
	public void testChildrenOption_EditInfo() {
		//TODO
	}
	
	public void testChildrenOption_Cancel() {
		//TODO
	}
	@Override
	protected void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}
	
	private View findViewById(int id){
		return solo.getView(id);
	}

}
