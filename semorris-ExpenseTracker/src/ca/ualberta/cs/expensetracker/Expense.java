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
	
	public Expense (){
		
	}
	
	public Expense (Parcel in){
    	String[] data = new String[6];
    	
    	in.readStringArray(data);
    	this.name = data[0];
    	this.date = data[1];
    	this.category = data[2];
    	this.description = data[3];
    	this.currency = data[4];
    	this.amount = data[5];
    	
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
	
	public void updateExpense(String name, String date, String category, String currency, String amount, String description){
		this.name = name;
		this.date = date;
		this.category = category;
		this.currency = currency;
		this.amount = amount;
		this.description = description;
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
