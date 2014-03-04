package com.example.cmpt371project;

import com.example.cmpt371project.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class childrenEdit extends Activity{

	private Button edCh_save_but;
	private EditText edCh_firs_txt;
	private EditText edCh_last_txt;
	private EditText edCh_addr_txt;
	private EditText edCh_birth_txt;
	private EditText edCh_Remo_but;
	private EditText edCh_phon_txt;
	private EditText edCh_gender_txt;
	private EditText edCh_post_txt;

	private LocalDB addNewChild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_children_edit);

		addNewChild = new LocalDB(this.getApplicationContext());
		edCh_save_but = (Button) findViewById(R.id.edCh_save_but);
		edCh_firs_txt = (EditText) findViewById(R.id.edCh_firs_txt);
		edCh_last_txt = (EditText) findViewById(R.id.edCh_last_txt);
		edCh_addr_txt = (EditText) findViewById(R.id.edCh_addr_txt);
		edCh_phon_txt = (EditText) findViewById(R.id.edCh_phon_txt);
		//edCh_Remo_but = (EditText) findViewById(R.id.edCh_Remo_but);
		edCh_birth_txt = (EditText) findViewById(R.id.edCh_birth_txt);
		edCh_gender_txt = (EditText) findViewById(R.id.edCh_gender_txt);
		edCh_post_txt = (EditText) findViewById(R.id.edCh_post_txt);

		try{
		edCh_save_but.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String childFirstName=edCh_firs_txt.getText().toString().trim();
				String childLastName=edCh_last_txt.getText().toString().trim();
				String childAddress=edCh_addr_txt.getText().toString().trim();
				String childPhoneNum=edCh_phon_txt.getText().toString().trim();			
				String childBirthdate=edCh_birth_txt.getText().toString().trim();
				String childGender=edCh_gender_txt.getText().toString().trim();
				String childPostalCode=edCh_post_txt.getText().toString().trim();

				addNewChild.addNewChild(childFirstName, childLastName, childGender, childBirthdate, childAddress, childPostalCode, childPhoneNum);

			}

		}); 
		}catch (Exception e){
			Log.d("add child", e.toString());
		}

	}


}
