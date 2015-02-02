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

public class ViewClaimActivity extends Activity {

	private Spinner statusSpinner;
	private ArrayList<HashMap<String, String>> list;
	private int claimIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_claim);
        
		ClaimListManager.initManager(this.getApplicationContext());
        //ExpenseListManager.initManager(this.getApplicationContext());
        addItemsOnStatusSpinner();
        
        //final Claim claim = getIntent().getParcelableExtra("claimTag");c
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
        
    	EditText editText = (EditText) findViewById(R.id.claimNameEditText);
    	editText.setText(claim.getName());
    	editText = (EditText) findViewById(R.id.startDateEditText1);
    	editText.setText(claim.getStartDate());
    	editText = (EditText) findViewById(R.id.endDateEditText1);
    	editText.setText(claim.getEndDate());
    	editText = (EditText) findViewById(R.id.descriptionEditText1);
    	editText.setText(claim.getDescription());
    	
    	
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
	
	public void emailClaim(MenuItem menu){
    	Toast.makeText(this, "Email Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ViewClaimActivity.this, EmailClaimActivity.class);
    	startActivity(intent);
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
		
    	EditText nameText = (EditText) findViewById(R.id.claimNameEditText);
    	EditText startDateText = (EditText) findViewById(R.id.startDateEditText1);
    	EditText endDateText = (EditText) findViewById(R.id.endDateEditText1);
    	EditText descritionText = (EditText) findViewById(R.id.descriptionEditText1);
    	Spinner statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
    	
    	claim.updateClaim(nameText.getText().toString(), 
    			startDateText.getText().toString(), 
    			endDateText.getText().toString(), 
    			descritionText.getText().toString(),
    			statusSpinner.getSelectedItem().toString());
    	
    	ClaimListController.saveClaimList();
	}
}
