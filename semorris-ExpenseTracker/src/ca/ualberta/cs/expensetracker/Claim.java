package ca.ualberta.cs.expensetracker;

import java.io.IOException;
import java.io.Serializable;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;


public class Claim implements Serializable, Parcelable {

	/**
	 * Claim serialization ID
	 */
	private static final long serialVersionUID = -4886379463491912930L;
	protected String name;
    protected String startDate;
    protected String endDate;
    protected String descrpition;
    protected String status;
    protected ExpenseList expenseList;

    Context context;
    
    public Claim(){
    	
    }
    
    public Claim(String name){
    	this.name = name;
    }
    
    public Claim(String name, String startDate, String endDate, String description) {
    	this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descrpition = description;
        if(expenseList == null){
        	expenseList = new ExpenseList();
        }
        this.status = "In Progress";
    }
    
    public void addExpense(Expense expense){
    	this.expenseList.addExpense(expense);
    	//this.notifyAll();
    }
    
    public Claim (Parcel in) throws IOException, ClassNotFoundException{
    	String[] data = new String[5];
    	
    	in.readStringArray(data);
    	this.name = data[0];
    	this.startDate = data[1];
    	this.endDate = data[2];
    	this.descrpition = data[3];
    	this.expenseList = ExpenseListManager.expenseListFromString(data[4]);
    }
    
    public String getName(){
    	return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return descrpition;
    }
    
    public ExpenseList getExpenses(){
    	return expenseList;
    }
    
    public String getStatus(){
    	return status;
    }
    
    public Expense getExpense(int index){
    	return expenseList.getExpense(index);
    }
    
    public int getExpensePos(Expense expense){
    	return expenseList.getPos(expense);
    }
    
    public String toString(){
    	return getName();
    }
    
    public boolean equals (Object compareClaim){
    	if (compareClaim != null && compareClaim.getClass()==this.getClass()) {
    		return this.equals((Claim)compareClaim);
    	} else {
    		return false;
    	}
    }
    
    public boolean equals(Claim compareClaim){
    	if (compareClaim == null){
    		return false;
    	}
    	return getName().equals(compareClaim.getName());
    }
    
    public int hashcode(){
    	return ("Claim: " + getName()).hashCode();
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		try {
			dest.writeStringArray(new String[]{this.name, 
					this.startDate, 
					this.endDate, 
					this.descrpition, 
					ExpenseListManager.expenseListToString(expenseList)});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final Parcelable.Creator<Claim> CREATOR = new Parcelable.Creator<Claim>() {

		@Override
		public Claim createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			try {
				return new Claim(source);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		@Override
		public Claim[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Claim[size];
		}
	};

	public void updateClaim(String name, String startDate, String endDate, String description, String status) {
		this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descrpition = description;
        this.status = status;
	}

}