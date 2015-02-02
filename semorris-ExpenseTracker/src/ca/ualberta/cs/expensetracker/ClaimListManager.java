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
	private ClaimList cl = null;
	
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
		String claimListData = settings.getString(clKey, "");
		if (claimListData.equals("")) {
			cl = new ClaimList();
			return cl;
		} else {
			cl = claimListFromString(claimListData);
			return cl;
		}
	}

	public void saveClaimList(ClaimList cl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
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
