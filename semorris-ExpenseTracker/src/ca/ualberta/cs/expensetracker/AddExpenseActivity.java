package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends Activity {

	private Spinner expenseCurrencySpinner;
	private Spinner expenseCategorySpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_expense);
        ClaimListManager.initManager(this.getApplicationContext());
        ExpenseListManager.initManager(this.getApplicationContext());
        addItemsOnExpenseCurrencySpinner();
        addItemsOnExpenseCategorySpinner();

    }
	
	public void addItemsOnExpenseCurrencySpinner(){
		expenseCurrencySpinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
		ArrayList<String> list = new ArrayList<String>();
		list.add("CAD");
		list.add("USD");
		list.add("EUR");
		list.add("GBP");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		expenseCurrencySpinner.setAdapter(dataAdapter);
	}
	
	public void addItemsOnExpenseCategorySpinner(){
		expenseCategorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Air Fare");
		list.add("Ground Transport");
		list.add("Vehicle Rental");
		list.add("Fuel");
		list.add("Parking");
		list.add("Registration");
		list.add("Accomodation");
		list.add("Meal");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		expenseCategorySpinner.setAdapter(dataAdapter);
	}
	
	public void addListenerOnSpinnerItemSelection() {
		expenseCurrencySpinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
		expenseCurrencySpinner.setOnItemSelectedListener(new OnItemSelectedListenerSpinner());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
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
	
	//switches to the expenseListActivity when the "save" button is clicked
    public void saveExpense(View v) throws EmptyClaimListException{
    	Toast.makeText(this, "Save Expense", Toast.LENGTH_SHORT).show();
    	
    	int index = getIntent().getIntExtra("claimPos",0);
    	Claim claim = ClaimListController.getClaimList().chooseClaim(index);
    	
    	EditText nameText = (EditText) findViewById(R.id.expenseNameEditText);
    	EditText dateText = (EditText) findViewById(R.id.expenseDateEditText);
    	Spinner categorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
    	Spinner currencySpinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
    	EditText amountText = (EditText) findViewById(R.id.expenseAmountEditText);
    	EditText descritionText = (EditText) findViewById(R.id.expenseDescriptionEditText);
    	
    	Expense expense = new Expense(nameText.getText().toString(), 
    			dateText.getText().toString(), 
    			categorySpinner.getSelectedItem().toString(), 
    			currencySpinner.getSelectedItem().toString(), 
    			amountText.getText().toString(), 
    			descritionText.getText().toString());
    	
    	claim.addExpense(expense);
    	ClaimListController.saveClaimList();
    	Intent intent = new Intent(AddExpenseActivity.this, ViewClaimActivity.class);
    	intent.putExtra("claimPos", index);
    	startActivity(intent);
    }
}
