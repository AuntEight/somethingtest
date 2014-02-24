package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cmpt371project.R;

public class surveyFinish extends Activity{
private Button preButton;
private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey_finish	);
		
		preButton= (Button)findViewById(R.id.suFi_prev_but);
		nextButton= (Button)findViewById(R.id.suQU_next_but);
		
		preButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(surveyFinish.this, survey.class);
	           	surveyFinish.this.startActivity(addIntent);
				
			}});
		
//		nextButton.setOnClickListener(new View.OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//	           	 Intent addIntent = new Intent();
//	           	 addIntent.setClass(surveyFinish.this, surveyFinish.class);
//	           	surveyFinish.this.startActivity(addIntent);
//				
//			}});
	}
	
	

}