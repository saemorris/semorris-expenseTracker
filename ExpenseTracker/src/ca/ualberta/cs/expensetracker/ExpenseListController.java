package ca.ualberta.cs.expensetracker;

import java.io.IOException;

public class ExpenseListController {

	//Lazy Singleton
	private static ExpenseList expenseList = null;
	//Warning: throws runTime exception
	static public ExpenseList getExpenseList(){
		if (expenseList == null){
			try {
				expenseList = ExpenseListManager.getManager().loadExpenseList();
				expenseList.addListener(new Listener() {
					@Override
					public void update(){
						saveExpenseList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize expenseList from expenseListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize expenseList from expenseListManager");
			}
		}
		return expenseList;
	}
		
	static public void saveExpenseList(){
		try {
			ExpenseListManager.getManager().saveExpenseList(getExpenseList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize expenseList from expenseListManager");
		}
	}

	public Expense chooseExpense() throws EmptyExpenseListException {
		return getExpenseList().chooseExpense();
	}
	
	public void addexpense(Expense expense){
		getExpenseList().addExpense(expense);
	}
}
