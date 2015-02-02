
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ClaimCreatedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_created);
        ClaimListManager.initManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_created, menu);
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
	
    //switches to CreateClaimActivity when "Create a New Claim" button is clicked
    public void createClaim(View v){
    	Toast.makeText(this, "Create a claim", Toast.LENGTH_SHORT).show();
    	
    	Intent intent = new Intent(ClaimCreatedActivity.this, CreateClaimActivity.class);
    	startActivity(intent);
    }
    
    //switches to AddExpenseActivity when "Add Expenses to your Claim" button is clicked
    public void addExpense(View v){
    	Toast.makeText(this, "Add Expense", Toast.LENGTH_SHORT).show();
    	Claim claim = getIntent().getParcelableExtra("claimTag");
    	
    	Intent intent = new Intent(ClaimCreatedActivity.this, AddExpenseActivity.class);
    	intent.putExtra("claimTag", (Parcelable)claim);
    	startActivity(intent);
    }
    
    public void viewClaims(View v){
    	Toast.makeText(this, "View claims", Toast.LENGTH_SHORT).show();
    	
    	Intent intent = new Intent(ClaimCreatedActivity.this, ExistingClaimsActivity.class);
    	startActivity(intent);
    }
}
