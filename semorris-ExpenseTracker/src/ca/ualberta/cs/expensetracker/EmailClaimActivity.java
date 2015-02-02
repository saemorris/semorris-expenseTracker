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
    	Toast.makeText(this, "Email Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EmailClaimActivity.this, ExistingClaimsActivity.class);
    	startActivity(intent);
    	finish();
    }
    
    public void sendEmail() throws EmptyClaimListException{
    	Toast.makeText(EmailClaimActivity.this, "email claim", Toast.LENGTH_SHORT).show();
    	
       /* String[] TO = {"semorris@ualberta.ca"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Claim Request");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");	

        try {
           startActivity(Intent.createChooser(emailIntent, "Send mail..."));
           finish();
        } catch (android.content.ActivityNotFoundException ex) {
           Toast.makeText(EmailClaimActivity.this, 
           "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }*/
        
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
