package ca.ualberta.cs.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class ClaimList implements Serializable{

    /**
	 * Claim List serialization ID
	 */
	private static final long serialVersionUID = -5346830892229501486L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList() {
		claimList = new ArrayList<Claim>();		
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public Collection<Claim> getClaims() {
		return claimList;		
	}

	public void addClaim(Claim testStudent) {
		claimList.add(testStudent);
		notifyListeners();
	}

	private void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}
	
	public void removeClaim(Claim testStudent) {
		claimList.remove(testStudent);
		notifyListeners();
	}
	
	public int getPos(Claim claim){
		return claimList.indexOf(claim);
	}

	public Claim chooseClaim(int index) throws EmptyClaimListException {
		int size = claimList.size();
		if (size <= 0) {
			throw new EmptyClaimListException();
		}
		return claimList.get(index);
	}

	public int size() {
		return claimList.size();
	}

	public boolean contains(Claim testStudent) {
		return claimList.contains(testStudent);
	}

	public void addListener(Listener l) {
		getListeners().add(l);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
	public void updateClaim(Claim claim){
		notifyListeners();
	}
	
}
