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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcelable;

public class CreateClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_claim);
        ClaimListManager.initManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_claim, menu);
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
	
    //switches to ClaimCreatedActivity when "Create a New Claim" button is clicked
    public void createClaim(View v) throws ParseException{
    	Toast.makeText(this, "Create a claim", Toast.LENGTH_SHORT).show();
    	
    	ClaimListController cl = new ClaimListController();
    	EditText nameText = (EditText) findViewById(R.id.claimNameEditText);
    	EditText startDateText = (EditText) findViewById(R.id.startDateEditText);
    	EditText endDateText = (EditText) findViewById(R.id.endDateEditText1);
    	EditText descritionText = (EditText) findViewById(R.id.descriptionOfClaimEditText);
    	
    	Claim claim = new Claim(nameText.getText().toString(), 
    			startDateText.getText().toString(), 
    			endDateText.getText().toString(), 
    			descritionText.getText().toString());
    	cl.addClaim(claim);
    	
    	Intent intent = new Intent(CreateClaimActivity.this, ViewClaimActivity.class);
    	intent.putExtra("claimTag", (Parcelable)claim);
    	startActivity(intent);
    }
    
}
