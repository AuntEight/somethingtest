package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cmpt371project.R;

public class survey extends Activity{
private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		nextButton= (Button)findViewById(R.id.suQU_next_but);
		
		nextButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(survey.this, surveyFinish.class);
	           	survey.this.startActivity(addIntent);
				
			}});
	}

}
