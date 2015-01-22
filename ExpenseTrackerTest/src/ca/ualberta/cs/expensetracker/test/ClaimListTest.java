package ca.ualberta.cs.expensetracker.test;

import java.util.Collection;

import junit.framework.TestCase;
import ca.ualberta.cs.expensetracker.Claim;
import ca.ualberta.cs.expensetracker.ClaimList;
import ca.ualberta.cs.expensetracker.EmptyClaimListException;

public class ClaimListTest extends TestCase {

    public void testEmptyClaimList(){
        ClaimList claimList = new ClaimList();
        Collection<Claim> claims = claimList.getClaims();
        assertTrue("empty claim list", claims.size() == 0);
    }

    public void testAddClaim(){
    	ClaimList claimList = new ClaimList();
    	String startDate = "start Date";
    	String endDate = "end Date";
        String description = "description";
        Claim testClaim = new Claim(startDate, endDate, description);
        claimList.addClaim(testClaim);
        Collection<Claim> claims = claimList.getClaims();
        assertTrue("Claim list size ", claims.size() == 1);
        assertTrue("Test claim not contained", claims.contains(testClaim));
        }
        
    public void testRemoveClaim(){
    	ClaimList claimList = new ClaimList();
        String startDate = "start Date";
        String endDate = "end Date";
        String description = "description";
        Claim testClaim = new Claim(startDate, endDate, description);
        claimList.addClaim(testClaim);
        Collection<Claim> claims = claimList.getClaims();
        assertTrue("claim list size isn't big enough", claims.size() == 1);
        assertTrue("Test claim not contained", claims.contains(testClaim));
        claimList.removeClaim(testClaim);
        claims = claimList.getClaims();
        assertTrue("Claim list size isn't small enough", claims.size() == 0);
        assertFalse("test claim still contained", claims.contains(testClaim));
    }

    public void testChooseClaim(){
        try{
    	ClaimList claimList = new ClaimList();
        String startDate = "start Date";
        String endDate = "end Date";
        String description = "description";
        Claim testClaim = new Claim(startDate, endDate, description);
        claimList.addClaim(testClaim);
        Claim chosenClaim = claimList.chooseClaim();
        assertTrue("claim is null", chosenClaim != null);
        assertTrue("Chosen claim", chosenClaim.equals(testClaim));
        } catch (EmptyClaimListException e) {
        	assertTrue("EmptyClaimListException was thrown!"+e, false);
        }
    }
    
    public void testChooseEmptyClaimList(){
    	ClaimList claimList = new ClaimList();
    	try {
    		Claim c = claimList.chooseClaim();
    		assertTrue("We shouln't reach here", c == null);
    		assertTrue("We shouln't reach here", false);
    	} catch (EmptyClaimListException e){
    		assertTrue("We should reach here", true);
    	}
    	
    }
}
