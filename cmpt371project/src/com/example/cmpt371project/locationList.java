package com.example.cmpt371project;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.cmpt371project.R;

public class locationList extends Activity{
private Button childrenButton;
private Button add;
private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_list);
		childrenButton=(Button)findViewById(R.id.alLo_chil_but);
		list=(ListView)findViewById(R.id.alLo_Loca_lst);
		
		add=(Button)findViewById(R.id.alLo_addN_but);
		
		//
		final simpleListAdapter thisAdapter = this.initializeListAdapter();
		list.setAdapter(thisAdapter);
		//
		
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				//read which item be clicked
				int itemId = (int)id;
				HashMap<String,Object> listItem = (HashMap<String, Object>) thisAdapter.getItem(itemId);
				String item = (String) listItem.get("location");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(item);
				
		       	//go to children option
		       	 Intent childOption = new Intent();
		       	childOption.setClass(locationList.this, childrenAtLocation.class);
		       	childOption.putExtra("location", item);
		       	locationList.this.startActivity(childOption);
			}
		});
		
		
		childrenButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(locationList.this, childrenList.class);
	           	 locationList.this.startActivity(addIntent);
				
			}});
		
		add.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
	           	 Intent addIntent = new Intent();
	           	 addIntent.setClass(locationList.this, locationEdit.class);
	           	 locationList.this.startActivity(addIntent);
				
			}});
		
	}

	private simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		
		for(int i=0;i<5;i++){
			HashMap<String,Object> newLocation = new HashMap<String,Object>();
			newLocation.put("location", "Daycare"+i);
			listItem.add(newLocation);
		}
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.list, 
				new String[]{"location"}, new int[]{R.id.list_textview});;
			
		return newAdapter;		
	}
}
