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

    public Claim chooseClaim(Claim testClaim) {
        return testClaim;
    }

}
