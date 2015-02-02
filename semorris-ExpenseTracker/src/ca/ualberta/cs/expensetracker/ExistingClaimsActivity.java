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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
				
				abd.show();
				return false;
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder abd = new AlertDialog.Builder(ExistingClaimsActivity.this);
				abd.setMessage("Select "+list.get(position).toString()+"?");
				abd.setCancelable(true);
				final int finalPosition = position;
				abd.setPositiveButton("Select", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Claim claim = list.get(finalPosition);
						int index = ClaimListController.getClaimList().getPos(claim);
				    	Intent intent = new Intent(ExistingClaimsActivity.this, ViewClaimActivity.class);
				    	intent.putExtra("claimPos", index);
				    	startActivity(intent);
					}
				});
				abd.setNegativeButton("Cancel", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				abd.show();
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
	
    public void createClaim (View v) {
    	Intent intent = new Intent(ExistingClaimsActivity.this, CreateClaimActivity.class);
    	startActivity(intent);
    }
    
    
    public void emailClaim(MenuItem menu){
    	Intent intent = new Intent(ExistingClaimsActivity.this, EmailClaimActivity.class);
    	startActivity(intent);
    }
    
    public void addExpense(MenuItem menu){
    	Intent intent = new Intent(ExistingClaimsActivity.this, AddExpenseActivity.class);
    	startActivity(intent);
    }

    
}
