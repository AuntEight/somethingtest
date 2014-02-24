package com.example.cmpt371project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cmpt371project.R;

public class researcherList extends Activity{
private Button addButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_researchers_list);
		addButton=(Button)findViewById(R.id.alRe_add_button);
		
		addButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
		       	 Intent addIntent = new Intent();
		       	 addIntent.setClass(researcherList.this, researcherEdit.class);
		       	researcherList.this.startActivity(addIntent);
				
			}});
	}

}



