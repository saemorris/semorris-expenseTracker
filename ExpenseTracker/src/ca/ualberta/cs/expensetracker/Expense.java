package ca.ualberta.cs.expensetracker;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Serializable, Parcelable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2956475683263843254L;
	protected String name;
	protected String date;
	protected String category;
	protected String description;
	protected String currency;
	protected String amount;
	
	public Expense(String name, String date, String category, String currency, String amount, String description){
		this.name = name;
		this.date = date;
		this.category = category;
		this.currency = currency;
		this.amount = amount;
		this.description = description;
	}
	
	public Expense (Parcel in){
    	String[] data = new String[6];
    	
    	in.readStringArray(data);
    	this.name = data[0];
    	this.date = data[1];
    	this.category = data[2];
    	this.currency = data[3];
    	this.amount = data[4];
    	this.description = data[5];
    }
	
	public String getName(){
		return this.name;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public String getCurrency(){
		return this.currency;
	}
	
	public String getAmount(){
		return this.amount;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String toString() {
		return getName();
	}
	
	public int hashcode(){
    	return ("Expense: " + getName()).hashCode();
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[]{this.name, this.date, this.category, this.description, this.currency, this.amount,});
	}
	
	public static final Parcelable.Creator<Expense> CREATOR = new Parcelable.Creator<Expense>() {

		@Override
		public Expense createFromParcel(Parcel source) {
			return new Expense(source);
		}

		@Override
		public Expense[] newArray(int size) {
			return new Expense[size];
		}
	};
}
