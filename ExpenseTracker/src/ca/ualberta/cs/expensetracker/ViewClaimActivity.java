package ca.ualberta.cs.expensetracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ViewClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_claim);
        ClaimListManager.initManager(this.getApplicationContext());
        Claim claim = getIntent().getParcelableExtra("claimTag");
    	EditText editText = (EditText) findViewById(R.id.claimNameEditText);
    	editText.setText(claim.getName());
    	editText = (EditText) findViewById(R.id.startDateEditText1);
    	editText.setText(claim.getStartDate());
    	editText = (EditText) findViewById(R.id.endDateEditText1);
    	editText.setText(claim.getEndDate());
    	editText = (EditText) findViewById(R.id.descriptionEditText1);
    	editText.setText(claim.getDescription());
    	
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
