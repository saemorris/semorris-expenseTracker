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

public class ClaimListManager {
	static String prefFile = "ClaimList";
	static String clKey = "ClaimList";
	
	Context context;

	private static ClaimListManager claimListManager = null;
	
	public static void initManager (Context context){
		if (claimListManager == null){
			if (context == null){
				throw new RuntimeException("missing context for claimListManager");
			}
			claimListManager = new ClaimListManager(context);
		}
	}
	
	public static ClaimListManager getManager(){
		if (claimListManager == null) {
			throw new RuntimeException("Did not initialize claimListManager");
		}
		return claimListManager;
		
	}
	
	public ClaimListManager(Context context) {
		this.context = context;
	}

	public ClaimList loadClaimList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String studentListData = settings.getString(clKey, "");
		if (studentListData.equals("")) {
			return new ClaimList();
		} else {
			return claimListFromString(studentListData);
		}
	}

	public void saveClaimList(ClaimList cl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(clKey, claimListToString(cl));
		editor.commit();
	}

	public static ClaimList claimListFromString(String claimListData) throws IOException, ClassNotFoundException, StreamCorruptedException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimList)oi.readObject();
		
	}
	
	public static String claimListToString(ClaimList cl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
}
