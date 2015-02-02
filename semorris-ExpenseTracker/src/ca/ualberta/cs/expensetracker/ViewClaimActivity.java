/*  ExpenseTracker is an android app to track expense claims
    Copyright (C) 2015  Sarah Morris

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ViewClaimActivity extends Activity {

	private Spinner statusSpinner;
	private ArrayList<HashMap<String, String>> list;
	private int claimIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_claim);
		ClaimListManager.initManager(this.getApplicationContext());
        addItemsOnStatusSpinner();
        
        claimIndex = getIntent().getIntExtra("claimPos", 0);
        Claim claim;
		try {
			claim = ClaimListController.getClaimList().chooseClaim(claimIndex);
		} catch (EmptyClaimListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			claim = new Claim();  
		}
		
		
        
        Spinner spinner = (Spinner) findViewById(R.id.statusSpinner);
		spinner.setSelection(((ArrayAdapter)spinner.getAdapter()).getPosition(claim.getStatus()));
        
    	EditText nameEditText = (EditText) findViewById(R.id.claimNameEditText);
    	nameEditText.setText(claim.getName());
    	EditText startDateEditText = (EditText) findViewById(R.id.startDateEditText1);
    	startDateEditText.setText(claim.getStartDate());
    	EditText endDateEditText = (EditText) findViewById(R.id.endDateEditText1);
    	endDateEditText.setText(claim.getEndDate());
    	EditText descriptionEditText = (EditText) findViewById(R.id.descriptionEditText1);
    	descriptionEditText.setText(claim.getDescription());
    	
    	
    	ListView listView=(ListView)findViewById(R.id.expensesListView);
        try {
			populateList();
		} catch (EmptyClaimListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);
    	
        final ExpenseList expenses = claim.getExpenses();
        
        if ( spinner.getSelectedItem().toString() == "Approved"){
        	nameEditText.setKeyListener(null);
        	startDateEditText.setKeyListener(null);
        	endDateEditText.setKeyListener(null);
        	descriptionEditText.setKeyListener(null);
        }
        
        
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, 
					int position, long id){
				Toast.makeText(ViewClaimActivity.this, "select "+list.get(position), Toast.LENGTH_SHORT).show();
				AlertDialog.Builder abd = new AlertDialog.Builder(ViewClaimActivity.this);
				abd.setMessage("Delete "+expenses.getExpense(position).toString()+"?");
				abd.setCancelable(true);
				final int finalPosition = position;
				abd.setPositiveButton("Delete", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Expense expense = expenses.getExpense(finalPosition);
						expenses.removeExpense(expense);
					}
				});
				abd.setNegativeButton("Cancel", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				
				abd.show();
				return false;
			}
		});
        
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Toast.makeText(ViewClaimActivity.this, "select "+expenses.getExpense(position), Toast.LENGTH_SHORT).show();
				AlertDialog.Builder abd = new AlertDialog.Builder(ViewClaimActivity.this);
				abd.setMessage("Select "+expenses.getExpense(position).toString()+"?");
				abd.setCancelable(true);
				final int finalPosition = position;
				abd.setNegativeButton("Cancel", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				abd.setPositiveButton("Select", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Expense expense = expenses.getExpense(finalPosition);
						int expenseIndex;
						try {
							expenseIndex = ClaimListController.getClaimList().chooseClaim(claimIndex).getExpensePos(expense);
						} catch (EmptyClaimListException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							expenseIndex = 0;
						}
				    	Intent intent = new Intent(ViewClaimActivity.this, ViewExpenseActivity.class);
				    	intent.putExtra("expensePos", expenseIndex);
				    	intent.putExtra("claimPos", claimIndex);
				    	startActivity(intent);
					}
				});
				abd.show();
			}
		});
	}
	
	public void addItemsOnStatusSpinner(){
		statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
		ArrayList<String> list = new ArrayList<String>();
		list.add("In Progress");
		list.add("Submitted");
		list.add("Returned");
		list.add("Approved");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		statusSpinner.setAdapter(dataAdapter);
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void emailClaim(MenuItem menu){
    	Toast.makeText(this, "Email Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ViewClaimActivity.this, EmailClaimActivity.class);
    	intent.putExtra("claimPos", claimIndex);
    	startActivity(intent);
    }
	
	public void goHome(MenuItem menu){
    	Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ViewClaimActivity.this, ExistingClaimsActivity.class);
    	startActivity(intent);
    }
	
	public void addExpense(View v){
		
		Intent intent = new Intent(ViewClaimActivity.this, AddExpenseActivity.class);
		intent.putExtra("claimPos", claimIndex);
		startActivity(intent);
	}

	private void populateList() throws EmptyClaimListException {
        // TODO Auto-generated method stub
         
        list=new ArrayList<HashMap<String,String>>();
        int index = getIntent().getIntExtra("claimPos", 0);
        Claim claim = ClaimListController.getClaimList().chooseClaim(index);
         
        for (int i = 0; i < claim.getExpenses().size(); i++){
        	HashMap<String,String> temp=new HashMap<String, String>();
        	temp.put("First", claim.getExpenses().getExpense(i).getName());
        	temp.put("Second", claim.getExpenses().getExpense(i).getCurrency());
        	temp.put("Third", claim.getExpenses().getExpense(i).getAmount());
        	list.add(temp);
        }
    }
	
	public void saveClaim(View v) throws EmptyClaimListException{
		Claim claim = ClaimListController.getClaimList().chooseClaim(claimIndex);
		
		Spinner statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
		
		EditText nameText = (EditText) findViewById(R.id.claimNameEditText);
		EditText startDateText = (EditText) findViewById(R.id.startDateEditText1);
		EditText endDateText = (EditText) findViewById(R.id.endDateEditText1);
		EditText descriptionText = (EditText) findViewById(R.id.descriptionEditText1);
    	
		claim.updateClaim(nameText.getText().toString(), 
    			startDateText.getText().toString(), 
    			endDateText.getText().toString(), 
    			descriptionText.getText().toString(),
    			statusSpinner.getSelectedItem().toString());
    	
		ClaimListController.saveClaimList();
		
        if ( statusSpinner.getSelectedItem().toString() == "Approved"){
        	nameText.setEnabled(false);
        	startDateText.setKeyListener(null);
        	endDateText.setKeyListener(null);
        	descriptionText.setKeyListener(null);
        }
	}
}
