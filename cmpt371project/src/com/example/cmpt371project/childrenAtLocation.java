package com.example.cmpt371project;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.cmpt371project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class childrenAtLocation extends Activity{
private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_children_at_location);
		list=(ListView)findViewById(R.id.chLo_chil_lst);
		
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
		       	childOption.setClass(childrenAtLocation.this, childrenOption.class);
		       	childOption.putExtra("location", item);
		       	childrenAtLocation.this.startActivity(childOption);
			}
		});
		
	}

	private simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		
		for(int i=0;i<3;i++){
			HashMap<String,Object> newLocation = new HashMap<String,Object>();
			newLocation.put("location", "Child"+i);
			listItem.add(newLocation);
		}
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.list, 
				new String[]{"location"}, new int[]{R.id.list_textview});;
			
		return newAdapter;		
	}
}
