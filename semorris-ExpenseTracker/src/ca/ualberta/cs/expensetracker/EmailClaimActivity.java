
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
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EmailClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_claim);
        ClaimListManager.initManager(this.getApplicationContext());
        
        try {
			sendEmail();
		} catch (EmptyClaimListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_claim, menu);
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
	
    public void goHome(MenuItem menu){
    	Intent intent = new Intent(EmailClaimActivity.this, ExistingClaimsActivity.class);
    	startActivity(intent);
    	finish();
    }
    
    public void sendEmail() throws EmptyClaimListException{
 
    	int claimIndex = getIntent().getIntExtra("claimPos", 0);
    	Claim claim = ClaimListController.getClaimList().chooseClaim(claimIndex);
    	String expenses = "";
    	for (int i = 0; i < claim.getExpenses().size(); i++){
    		expenses = expenses.concat(claim.getExpense(i).getName().toString() + "\n\t\t" + claim.getExpense(i).getDate().toString() +
    				"\n\t\t" + claim.getExpense(i).getCategory().toString()+ "\n\t\t" + 
    				claim.getExpense(i).getCurrency() + "\n\t\t" +
    				claim.getExpense(i).getAmount() + "\n\t\t" +
    				claim.getExpense(i).getDescription() + "\n\n");
    	}
        
        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode("semorris@ualberta.ca") + 
                  "?subject=" + Uri.encode("Claim Request: "+ claim.getName()) + 
                  "&body=" + Uri.encode("Claim name: " + claim.getName() + "\n\t" + 
                		  "Start Date: " + claim.getStartDate() + "\n\t" +
                		  "End Date: " + claim.getEndDate() + "\n\t" +
                		  "Description: " + claim.getDescription() + "\n\t" +
                		  "Expenses: \n" + expenses + "\n");
        Uri uri = Uri.parse(uriText);

        send.setData(uri);
        startActivity(Intent.createChooser(send, "Send mail..."));
    }
    
}
