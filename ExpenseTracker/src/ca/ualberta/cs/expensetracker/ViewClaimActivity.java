package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_claim);
        ClaimListManager.initManager(this.getApplicationContext());
        ExpenseListManager.initManager(this.getApplicationContext());
        Claim claim = getIntent().getParcelableExtra("claimTag");
    	EditText editText = (EditText) findViewById(R.id.claimNameEditText);
    	editText.setText(claim.getName());
    	editText = (EditText) findViewById(R.id.startDateEditText1);
    	editText.setText(claim.getStartDate());
    	editText = (EditText) findViewById(R.id.endDateEditText1);
    	editText.setText(claim.getEndDate());
    	editText = (EditText) findViewById(R.id.descriptionEditText1);
    	editText.setText(claim.getDescription());
    	
    	ListView listView = (ListView) findViewById(R.id.expensesListView);
		Collection<Expense> expenses = ExpenseListController.getExpenseList().getExpenses();
		final ArrayList<Expense> list = new ArrayList<Expense>(expenses);
		final ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(expenseAdapter);
    	
		ExpenseListController.getExpenseList().addListener(new Listener() {
			@Override
			public void update () {
				list.clear();
				Collection<Expense> expenses = ExpenseListController.getExpenseList().getExpenses();
				list.addAll(expenses);
				expenseAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Toast.makeText(ViewClaimActivity.this, "select "+list.get(position), Toast.LENGTH_SHORT).show();
				AlertDialog.Builder abd = new AlertDialog.Builder(ViewClaimActivity.this);
				abd.setMessage("Select "+list.get(position).toString()+"?");
				abd.setCancelable(true);
				final int finalPosition = position;
				abd.setNegativeButton("Cancel", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				abd.setPositiveButton("Select", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Expense expense = list.get(finalPosition);
				    	Intent intent = new Intent(ViewClaimActivity.this, ViewExpenseActivity.class);
				    	intent.putExtra("expenseTag", (Parcelable)expense);
				    	startActivity(intent);
					}
				});
				abd.show();
			}
		});
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
}
