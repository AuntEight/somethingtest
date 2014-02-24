package com.example.cmpt371project;

import com.example.cmpt371project.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class childrenOption extends Activity{

	private Button takeSurveyButton;
	private Button editButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_children_option);
		
		takeSurveyButton=(Button)findViewById(R.id.opCh_take_but);
		editButton=(Button)findViewById(R.id.opCh_edit_but);
	
	takeSurveyButton.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View v) {
           	 Intent addIntent = new Intent();
           	 addIntent.setClass(childrenOption.this, surveyOption.class);
           	childrenOption.this.startActivity(addIntent);
			
		}});
	
	editButton.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View v) {
           	 Intent addIntent = new Intent();
           	 addIntent.setClass(childrenOption.this, childrenEdit.class);
           	childrenOption.this.startActivity(addIntent);
			
		}});
	}
}
