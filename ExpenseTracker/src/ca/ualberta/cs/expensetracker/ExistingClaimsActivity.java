package ca.ualberta.cs.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ExistingClaimsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing_claims);
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
