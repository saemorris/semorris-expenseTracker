package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewExpenseActivity extends Activity {

	private int expenseIndex;
	private int claimIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_expense);
		
		ClaimListManager.initManager(this.getApplicationContext());
        ExpenseListManager.initManager(this.getApplicationContext());
        
        //Expense expense = getIntent().getParcelableExtra("expenseTag");
        
        expenseIndex = getIntent().getIntExtra("expensePos",0);
        claimIndex = getIntent().getIntExtra("claimPos",0);
        Expense expense;
		try {
			expense = ClaimListController.getClaimList().chooseClaim(claimIndex).getExpense(expenseIndex);
		} catch (EmptyClaimListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			expense = new Expense();
		}
        
    	EditText editText = (EditText) findViewById(R.id.expenseNameEditText);
    	editText.setText(expense.getName());
    	editText = (EditText) findViewById(R.id.expenseDateEditText);
    	editText.setText(expense.getDate());
    	
    	Toast.makeText(this, expense.getCategory(), Toast.LENGTH_SHORT).show();
    	
    	Spinner spinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
		spinner.setSelection(((ArrayAdapter)spinner.getAdapter()).getPosition(expense.getCategory()));
    	
    	spinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
    	spinner.setSelection(((ArrayAdapter)spinner.getAdapter()).getPosition(expense.getCurrency()));
    	
    	editText = (EditText) findViewById(R.id.expenseAmountEditText);
    	editText.setText(expense.getAmount());
    	editText = (EditText) findViewById(R.id.expenseDescriptionEditText);
    	editText.setText(expense.getDescription());
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_expense, menu);
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
	
	public void saveExpense(View v) throws EmptyClaimListException{
Toast.makeText(this, "Save Expense", Toast.LENGTH_SHORT).show();
    	
    	Claim claim = ClaimListController.getClaimList().chooseClaim(claimIndex);
    	Expense expense = ClaimListController.getClaimList().chooseClaim(claimIndex).getExpense(expenseIndex);
    	
    	EditText nameText = (EditText) findViewById(R.id.expenseNameEditText);
    	EditText dateText = (EditText) findViewById(R.id.expenseDateEditText);
    	Spinner categorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
    	Spinner currencySpinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
    	EditText amountText = (EditText) findViewById(R.id.expenseAmountEditText);
    	EditText descritionText = (EditText) findViewById(R.id.expenseDescriptionEditText);
    	
    	expense.updateExpense(nameText.getText().toString(), 
    			dateText.getText().toString(), 
    			categorySpinner.getSelectedItem().toString(), 
    			currencySpinner.getSelectedItem().toString(), 
    			amountText.getText().toString(), 
    			descritionText.getText().toString() );
    	
    	//claim.addExpense(expense);
    	ClaimListController.saveClaimList();
    	//claim.notify();
    	//Double.parseDouble(amountText.getText().toString())
    	Intent intent = new Intent(ViewExpenseActivity.this, ViewClaimActivity.class);
    	intent.putExtra("claimPos", claimIndex);
    	startActivity(intent);
    }
}
