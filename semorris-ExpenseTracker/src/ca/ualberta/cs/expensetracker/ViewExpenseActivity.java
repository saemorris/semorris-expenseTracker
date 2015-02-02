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
                
        expenseIndex = getIntent().getIntExtra("expensePos",0);
        claimIndex = getIntent().getIntExtra("claimPos",0);
        Claim claim;
		try {
			claim = ClaimListController.getClaimList().chooseClaim(claimIndex);
		} catch (EmptyClaimListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			claim = new Claim();
		}
        Expense expense;
		try {
			expense = ClaimListController.getClaimList().chooseClaim(claimIndex).getExpense(expenseIndex);
		} catch (EmptyClaimListException e) {
			e.printStackTrace();
			expense = new Expense();
		}
        
    	EditText nameEditText = (EditText) findViewById(R.id.expenseNameEditText);
    	nameEditText.setText(expense.getName());
    	EditText dateEditText = (EditText) findViewById(R.id.expenseDateEditText);
    	dateEditText.setText(expense.getDate());
    	 	
    	Spinner categorySpinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
		categorySpinner.setSelection(((ArrayAdapter)categorySpinner.getAdapter()).getPosition(expense.getCategory()));
    	
    	Spinner currencySpinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
    	currencySpinner.setSelection(((ArrayAdapter)currencySpinner.getAdapter()).getPosition(expense.getCurrency()));
    	
    	EditText amountEditText = (EditText) findViewById(R.id.expenseAmountEditText);
    	amountEditText.setText(expense.getAmount());
    	EditText descriptionEditText = (EditText) findViewById(R.id.expenseDescriptionEditText);
    	descriptionEditText.setText(expense.getDescription());
    	
    	if (claim.getStatus() == "Approved"){
    		nameEditText.setKeyListener(null);
    		dateEditText.setKeyListener(null);
    		categorySpinner.setClickable(false);
    		currencySpinner.setClickable(false);
    		amountEditText.setKeyListener(null);
    		descriptionEditText.setKeyListener(null);
    	}
    	
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
    	
    	ClaimListController.saveClaimList();
    	Intent intent = new Intent(ViewExpenseActivity.this, ViewClaimActivity.class);
    	intent.putExtra("claimPos", claimIndex);
    	startActivity(intent);
    }
}
