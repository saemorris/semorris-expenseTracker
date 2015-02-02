package ca.ualberta.cs.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ExpenseList implements Serializable{

	/**
	 * Expense List serialization ID
	 */
	private static final long serialVersionUID = 2603396502620602602L;
	protected ArrayList<Expense> expenseList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public Collection<Expense> getExpenses(){
		return expenseList;
	}

	public void addExpense(Expense e){
		expenseList.add(e);
		notifyListeners();
	}
	
	public void removeExpense(Expense e){
		expenseList.remove(e);
		notifyListeners();
	}
	
	public int size(){
		return expenseList.size();
	}
	
	public boolean contains(Expense e){
		return expenseList.contains(e);
	}
	
	public Expense getExpense(int pos){
		return expenseList.get(pos);
	}
	
	public int getPos(Expense expense){
		return expenseList.indexOf(expense);
	}
	
	public Expense chooseExpense() throws EmptyExpenseListException {
		int size = expenseList.size();
		if (size <= 0) {
			throw new EmptyExpenseListException();
		}
		int index = (int) (expenseList.size() * Math.random());
		return expenseList.get(index);
	}
	

	private void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
}
