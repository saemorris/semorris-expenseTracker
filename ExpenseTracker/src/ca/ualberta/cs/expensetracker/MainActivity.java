package ca.ualberta.cs.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    //switches to ExistingClaimsActivity when "Choose an Existing Claim button is clicked
    public void chooseAClaim(View v){
    	Toast.makeText(this, "Choose a claim", Toast.LENGTH_SHORT).show();
    	
    	Intent intent = new Intent(MainActivity.this, ExistingClaimsActivity.class);
    	startActivity(intent);
    	
    }
    
    //switches to CreateClaimActivity when "Create a New Claim" button is clicked
    public void createClaim(View v){
    	Toast.makeText(this, "Create a claim", Toast.LENGTH_SHORT).show();
    	
    	Intent intent = new Intent(MainActivity.this, CreateClaimActivity.class);
    	startActivity(intent);
    }
    
    public void emailClaim(MenuItem menu){
    	Toast.makeText(this, "Email Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, EmailClaimActivity.class);
    	startActivity(intent);
    }
    
    public void editExpense(MenuItem menu){
    	Toast.makeText(this, "Edit Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, EditExpenseActivity.class);
    	startActivity(intent);
    }
    
    public void addExpense(MenuItem menu){
    	Toast.makeText(this, "Add Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
    	startActivity(intent);
    }
    
    public void changeStatus(MenuItem menu){
    	Toast.makeText(this, "Change Claim Status", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ChangeStatusActivity.class);
    	startActivity(intent);
    }

}
