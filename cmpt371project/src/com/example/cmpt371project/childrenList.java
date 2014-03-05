package com.example.cmpt371project;

import com.example.cmpt371project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;



public class childrenList extends Activity{
private Button locationButton;
private Button addButton;
private ListView list;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_children_list);
		locationButton=(Button)findViewById(R.id.alCh_loca_but);
		addButton=(Button)findViewById(R.id.alCh_addN_but);
		list = (ListView)findViewById(R.id.alCh_chil_lst);
		
		//list adapter
		final simpleListAdapter thisAdapter = this.initializeListAdapter();
		list.setAdapter(thisAdapter);
		
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
		       	childOption.setClass(childrenList.this, childrenOption.class);
		       	childOption.putExtra("location", item);
		       	childrenList.this.startActivity(childOption);
			}
		});
		
		///
		locationButton.setOnClickListener(new View.OnClickListener(){
	       	
			@Override
			public void onClick(View v) {
				
		       	 Intent addIntent = new Intent();
		       	 addIntent.setClass(childrenList.this, locationList.class);
		       	 childrenList.this.startActivity(addIntent);
		       	 	
			}});
		
		addButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
		       	 Intent addIntent = new Intent();
		       	 addIntent.setClass(childrenList.this, childrenEdit.class);
		       	childrenList.this.startActivity(addIntent);
				
			}});
	}
	
	
	private simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		
		LocalDB getDB= new LocalDB(this.getApplication());
		listItem = getDB.getListofChildren();
//		for(int i=0;i<100;i++){  
//			HashMap<String,Object> newLocation = new HashMap<String,Object>();
//			newLocation.put("location", "Child"+i);
//			listItem.add(newLocation);
//		}
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.list, 
				new String[]{"location"}, new int[]{R.id.list_textview});;
			
		return newAdapter;		
	}
}
