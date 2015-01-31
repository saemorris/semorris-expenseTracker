package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ViewExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_expense);
		
		ClaimListManager.initManager(this.getApplicationContext());
        ExpenseListManager.initManager(this.getApplicationContext());
        
        Expense expense = getIntent().getParcelableExtra("expenseTag");
    	EditText editText = (EditText) findViewById(R.id.expenseNameEditText);
    	editText.setText(expense.getName());
    	editText = (EditText) findViewById(R.id.expenseDateEditText);
    	editText.setText(expense.getDate());
    	Spinner spinner = (Spinner) findViewById(R.id.expenseCategorySpinner);
    	spinner.setPrompt(expense.getCategory());
    	spinner = (Spinner) findViewById(R.id.expenseCurrencySpinner);
    	spinner.setPrompt(expense.getCurrency());
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
}
