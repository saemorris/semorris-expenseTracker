package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ExistingClaimsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing_claims);
        ClaimListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.existingClaimsListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);
		
		ClaimListController.getClaimList().addListener(new Listener() {
			@Override
			public void update () {
				list.clear();
				Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
				list.addAll(claims);
				claimAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, 
					int position, long id){
				//Toast.makeText(ExistingClaimsActivity.this, "select "+list.get(position), Toast.LENGTH_SHORT).show();
				AlertDialog.Builder abd = new AlertDialog.Builder(ExistingClaimsActivity.this);
				abd.setMessage("Delete "+list.get(position).toString()+"?");
				abd.setCancelable(true);
				final int finalPosition = position;
				abd.setPositiveButton("Delete", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Claim claim = list.get(finalPosition);
						ClaimListController.getClaimList().removeClaim(claim);
					}
				});
				abd.setNegativeButton("Cancel", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		    	//Intent intent = new Intent(ExistingClaimsActivity.this, ViewClaimActivity.class);
		    	//EditText editText = (EditText) findViewById(R.id.claimNameEditText);
		    	//editText.setText(claim.getName());
		    	//startActivity(intent);
				
				abd.show();
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.existing_claims, menu);
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
	
    public void chooseAClaim(View v){
    	Toast.makeText(this, "Choose a claim", Toast.LENGTH_SHORT).show();
    	ClaimListController cl = new ClaimListController();
    	try {
    		Claim c = cl.chooseClaim();
    	} catch (EmptyClaimListException e) {
    		Toast.makeText(this, "Choose a claim", Toast.LENGTH_SHORT).show();
    	}
    }
    
    
    public void emailClaim(MenuItem menu){
    	Toast.makeText(this, "Email Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExistingClaimsActivity.this, EmailClaimActivity.class);
    	startActivity(intent);
    }
    
    public void editExpense(MenuItem menu){
    	Toast.makeText(this, "Edit Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExistingClaimsActivity.this, EditExpenseActivity.class);
    	startActivity(intent);
    }
    
    public void addExpense(MenuItem menu){
    	Toast.makeText(this, "Add Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExistingClaimsActivity.this, AddExpenseActivity.class);
    	startActivity(intent);
    }
    
    public void changeStatus(MenuItem menu){
    	Toast.makeText(this, "Change Claim Status", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExistingClaimsActivity.this, ChangeStatusActivity.class);
    	startActivity(intent);
    }
    
}
