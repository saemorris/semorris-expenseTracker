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
				throw new RuntimeException("Could not deserialize expenseList from expenseListManager, ClassNotFoundException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize expenseList from expenseListManager, IOException");
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
	
	public void addExpense(Expense expense){
		getExpenseList().addExpense(expense);
	}
}
