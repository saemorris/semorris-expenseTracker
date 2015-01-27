package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;


public class ClaimList {

    protected ArrayList<Claim> claimList;
    protected ArrayList<Listener> listeners;

    public ClaimList(){
        claimList = new ArrayList<Claim>();
        listeners = new ArrayList<Listener>();
    }

    public Collection<Claim> getClaims() {
        return claimList;
    }

    public void addClaim(Claim claim) {
        claimList.add(claim);
        notifyListeners();
    }

    public void removeClaim(Claim claim) {
        claimList.remove(claim);
        notifyListeners();
    }

    public Claim chooseClaim() throws EmptyClaimListException {
        int size = claimList.size();
        if (size <= 0){
        	throw new EmptyClaimListException();
        }
        int index = (int) (claimList.size() * Math.random());
        return claimList.get(index);
    }
    
    public void notifyListeners(){
    	for (Listener listener : listeners) {
    		listener.update();
    	}
    }
    
    public void addListener(Listener l){
    	listeners.add(l);
    }
    
    public void removeListener(Listener l){
    	listeners.remove(l);
    }
}
