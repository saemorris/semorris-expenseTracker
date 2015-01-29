package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;

public class ExpenseList {

	protected ArrayList<Expense> expenseList = null;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
	}
	
	public Collection<Expense> getExpenses(){
		return expenseList;
	}

	public void addExpense(Expense e){
		expenseList.add(e);
	}
	
	public void removeExpense(Expense e){
		expenseList.remove(e);
	}
	
	public int size(){
		return expenseList.size();
	}
	
	public boolean contains(Expense e){
		return expenseList.contains(e);
	}
}
