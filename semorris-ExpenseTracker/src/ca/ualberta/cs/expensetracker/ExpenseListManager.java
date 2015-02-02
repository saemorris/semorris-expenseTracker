package ca.ualberta.cs.expensetracker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ExpenseListManager {
	static final String prefFile = "ExpenseList";
	static final String elKey = "expenseList";
	
	Context context;

	private static ExpenseListManager expenseListManager = null;
	
	public static void initManager (Context context){
		if (expenseListManager == null){
			if (context == null){
				throw new RuntimeException("missing context for expenseListManager");
			}
			expenseListManager = new ExpenseListManager(context);
		}
	}
	
	public static ExpenseListManager getManager(){
		if (expenseListManager == null) {
			throw new RuntimeException("Did not initialize expenseListManager");
		}
		return expenseListManager;
		
	}
	
	public ExpenseListManager(Context context) {
		this.context = context;
	}

	public ExpenseList loadExpenseList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String expenseListData = settings.getString(elKey, "");
		if (expenseListData.equals("")) {
			return new ExpenseList();
		} else {
			return expenseListFromString(expenseListData);
		}
	}

	public void saveExpenseList(ExpenseList el) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(elKey, expenseListToString(el));
		editor.commit();
	}

	public static ExpenseList expenseListFromString(String expenseListData) throws IOException, ClassNotFoundException, StreamCorruptedException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(expenseListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ExpenseList)oi.readObject();
		
	}
	
	public static String expenseListToString(ExpenseList el) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(el);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
}
