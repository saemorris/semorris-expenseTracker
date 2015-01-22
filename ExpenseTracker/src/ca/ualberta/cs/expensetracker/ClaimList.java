package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.Collection;


public class ClaimList {

    protected ArrayList<Claim> claimList;

    public ClaimList(){
        claimList = new ArrayList<Claim>();
    }

    public Collection<Claim> getClaims() {
        return claimList;
    }

    public void addClaim(Claim claim) {
        claimList.add(claim);
    }

    public void removeClaim(Claim claim) {
        claimList.remove(claim);
    }

    public Claim chooseClaim() throws EmptyClaimListException {
        int size = claimList.size();
        if (size <= 0){
        	throw new EmptyClaimListException();
        }
        int index = (int) (claimList.size() * Math.random());
        return claimList.get(index);
    }
    
}
