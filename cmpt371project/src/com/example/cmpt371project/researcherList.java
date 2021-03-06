package com.example.cmpt371project;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

import com.example.cmpt371project.R;

public class researcherList extends Activity implements SearchView.OnQueryTextListener{
private Button addButton;
private SearchView  searchView;
private ListView listView;
private int clickPosition;
//private Object[] names;
private ArrayList<String> listContent = new ArrayList<String>();  
private HashMap<String,Object> clickItem;  //the item be clicked in the list 
private LocalDB resDB;
private simpleListAdapter thisAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_researchers_list);
		resDB = new LocalDB(this);
		
		//test DB
//		resDB.addNewUser("username1", "password1", "firstname1", "lastname1", "phonenum1", "privilege");
		
		ArrayList<HashMap<String,Object>> testRes = resDB.getAllUsers();
		System.out.println("~~~~~~~~~~~~~~~~~~");
		System.out.println(testRes.toString());
		System.out.println("~~~~~~~~~~~~~~~~~~");
		
		//finish
		
		initActionbar();  //for acthion bar	    
		listView = (ListView) findViewById(R.id.alRe_researcher_lst);
		        
		addButton = (Button) findViewById(R.id.actionbar_addButton);
//		searchView = (SearchView) findViewById(R.id.actionbar_searchView);
				
		thisAdapter = this.initializeListAdapter();
		
		//set listView
		listView.setAdapter(thisAdapter);
		listView.setTextFilterEnabled(true);
		
		//set option 
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){

            @Override  
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
            	
            	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            	clickItem = (HashMap<String,Object>)listView.getItemAtPosition(info.position);
            	System.out.println(clickItem.toString());
            	
                menu.setHeaderTitle("Option");     
                menu.add(0, 0, 0, "View");  
                menu.add(0, 1, 0, "Edit");     
                
                //for test 
//                ArrayList<HashMap<String,Object>> testRESDB = resDB.getAllUsers();
//                System.out.println(testRESDB.toString());
            }  
        }); 
		
		//set on click
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				//read which item be clicked
				int itemId = (int)id;
				HashMap<String,Object> listItem = (HashMap<String, Object>) thisAdapter.getItem(itemId);
				
				String itemID = (String) listItem.get("userID");
				String password = (String) listItem.get("userPassword");
				String firstName = (String) listItem.get("userFirstName");
				String lastName = (String) listItem.get("userLastName");
				String phoneN = (String) listItem.get("userPhoneNum");

				
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(itemID);
				
		       	//go to children option
		       	Intent viewIntent = new Intent();
		       	viewIntent.setClass(researcherList.this, researcherEdit.class);
		       	
		       	viewIntent.putExtra("from", "res_view");
		       	       	
		       	viewIntent.putExtra("userID", itemID);
		       	viewIntent.putExtra("userPassword", password);
		       	viewIntent.putExtra("userFirstName", firstName);
		       	viewIntent.putExtra("userLastName", lastName);
		       	viewIntent.putExtra("userPhoneNum", phoneN);
		       	
		       	researcherList.this.startActivity(viewIntent);
			}
		});
		
		//set searchView
		searchView.setOnQueryTextListener(this); 
		searchView.setSubmitButtonEnabled(false);  

		
		addButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
		       	 Intent addIntent = new Intent();
		       	 addIntent.putExtra("from", "res_add");
		       	 addIntent.setClass(researcherList.this, researcherEdit.class);
		       	 researcherList.this.startActivity(addIntent);
				
			}});
	}
	
	/**
	 * @author Xingze
	 * initialize Actionbar
	 */
    public void initActionbar() {  
        getActionBar().setDisplayShowHomeEnabled(false);  
        getActionBar().setDisplayShowTitleEnabled(false);  
        getActionBar().setDisplayShowCustomEnabled(true);  
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View mTitleView = mInflater.inflate(R.layout.actionbar_search,  
                null);  
        getActionBar().setCustomView(  
                mTitleView,  
                new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT,  
                        LayoutParams.WRAP_CONTENT));  
        searchView = (SearchView) mTitleView.findViewById(R.id.actionbar_searchView);  
    }  
    
    /**
     * @author Xingze
     * test list
     */
    private ArrayList<HashMap<String,Object>> testData(){
    	ArrayList<HashMap<String,Object>> listData = new ArrayList<HashMap<String,Object>>();
    	
    	HashMap<String,Object> data0 = new HashMap<String,Object>();
    	HashMap<String,Object> data1 = new HashMap<String,Object>();
    	HashMap<String,Object> data2 = new HashMap<String,Object>();
    	HashMap<String,Object> data3 = new HashMap<String,Object>();
    	HashMap<String,Object> data4 = new HashMap<String,Object>();
    	HashMap<String,Object> data5 = new HashMap<String,Object>();
    	HashMap<String,Object> data6 = new HashMap<String,Object>();
    	HashMap<String,Object> data7 = new HashMap<String,Object>();
    	HashMap<String,Object> data8 = new HashMap<String,Object>();
    	HashMap<String,Object> data9 = new HashMap<String,Object>();
    	
    	data0.put("name", "Mike");
    	data1.put("name", "Adair");
    	data2.put("name", "Bill");
    	data3.put("name", "Carter");
    	data4.put("name", "Dave");
    	data5.put("name", "Dean");
    	data6.put("name", "Ellis");
    	data7.put("name", "Dean");
    	data8.put("name", "Chad");
    	data9.put("name", "Cleveland");
    	
    	listData.add(data0);
    	listData.add(data1);
    	listData.add(data2);
    	listData.add(data3);
    	listData.add(data4);
    	listData.add(data5);
    	listData.add(data6);
    	listData.add(data7);
    	listData.add(data8);
    	listData.add(data9);
    	
    	return listData;
    	
    }
/**
 * @author Xingze
 * @return simpleListAdapter 
 * initial list adapter
 */
	private simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		listItem = resDB.getAllUsers();
		
	//	ArrayList<HashMap<String,Object>> temp = resDB.getAllUsers();
//		if(temp.size()>3){
//		HashMap<String,Object> test = temp.get(2);
//		listItem.add(test);
		
//		HashMap<String,Object> test2 = temp.get(3);
//		listItem.add(test);
//		}

//		listItem = testData();
		
//  	key		
//		oneUser.put("userID", userID);
//		oneUser.put("userPassword", userPassword);
//		oneUser.put("userFirstName", userFirstName);
//		oneUser.put("userLastName", userLastName);
//		oneUser.put("userPhoneNum", userPhoneNum);
//		oneUser.put("userPrivilege", userPrivilege);    
		
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.list_for_name, 
				new String[]{"userFirstName","userLastName"}, new int[]{R.id.list_for_name_first,R.id.list_for_name_last});
			
//		
//		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.list_for_name, 
//				new String[]{"userFirstName"}, new int[]{R.id.list_for_name_last});
		return newAdapter;		
	}

    @Override  
    public boolean onQueryTextChange(String newText) {  
        if (TextUtils.isEmpty(newText)) {  
            // Clear the text filter.  
            listView.clearTextFilter();  
        } else {  
            // Sets the initial value for the text filter.  
            listView.setFilterText(newText.toString());  
        }  
        return false;  
    }  

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//for edit popup
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.main, menu);
//        menu.add(0, 0, 0, "View");  
//        menu.add(0, 1, 0, "Edit");  
// 
//		return true;
//	}
	
	 @Override  
	    public boolean onContextItemSelected(MenuItem item) {  
	       switch (item.getItemId()) {  
	        case 0:  
	        	view();
	            System.out.println("View TEST"); 
	            break;  
	        case 1:  
	        	edit();
	        	System.out.println("Edit TEST");  
	            break;  
	      }
	      return true;
	 }
	 
	 /**
	  * @author Xingze
	  * view profile 
	  */
	 public void view(){
		 //need link to DB
       	 Intent addIntent = new Intent();
       	 addIntent.putExtra("from", "res_view");
       	 String clickName = (String) clickItem.get("name");
       	 addIntent.putExtra("name", clickName);
       	 addIntent.setClass(researcherList.this, researcherEdit.class);
       	 researcherList.this.startActivity(addIntent);
	 }
	 
	 /**
	  * @author Xingze
	  * edit profile
	  */
	 
	 public void edit(){
		 //need link to DB
       	 Intent addIntent = new Intent();
       	 addIntent.putExtra("from", "res_edit");
       	 String clickName = (String) clickItem.get("name");
       	 addIntent.putExtra("name", clickName);
       	 addIntent.setClass(researcherList.this, researcherEdit.class);
       	 researcherList.this.startActivity(addIntent);
		 
	 }
}



