package ca.ualberta.cs.expensetracker;

public class Expense {

	protected String date;
	protected String category;
	protected String description;
	protected String currency;
	protected double amount;
	
	public Expense(String date, String category, String description, String currency, double amount){
		this.date = date;
		this.category = category;
		this.description = description;
		this.currency = currency;
		this.amount = amount;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getCurrency(){
		return this.currency;
	}
	
	public double getAmount(){
		return this.amount;
	}
}
