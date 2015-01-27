package ca.ualberta.cs.expensetracker.test;

import java.io.IOException;

import ca.ualberta.cs.expensetracker.Claim;
import ca.ualberta.cs.expensetracker.ClaimList;
import ca.ualberta.cs.expensetracker.ClaimListManager;
import ca.ualberta.cs.expensetracker.EmptyClaimListException;
import android.test.AndroidTestCase;

public class ClaimListManagerTest extends AndroidTestCase {

	public void testClaimToString() {
		ClaimList cl = new ClaimList();
		Claim testClaim = new Claim("TestClaim");
		cl.addClaim(testClaim);
		try {
			String str = ClaimListManager.claimListToString(cl);
			assertTrue("String is too small ",str.length() > 0);
			ClaimList cl2 = ClaimListManager.claimListFromString(str);
			assertTrue("cl2 size > 0", cl2.size() > 0);
			assertTrue("Chosen Claim doesn't have same name", cl2.chooseClaim().toString().equals(testClaim.getName()));
			assertTrue("Chosen Claim doesn't have same name", cl2.chooseClaim().equals(testClaim));
			assertTrue("TestClaim!=cl2's testClaim", cl2.chooseClaim()!=testClaim);
			assertTrue("Collection Containment", cl2.getClaims().contains(testClaim));
			assertTrue("cl does not contain testClaim",cl.contains(testClaim));
			assertTrue("cl2 does not contain testClaim",cl2.contains(testClaim));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue("IOEXception "+e,false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			assertTrue("CLassNotFoundException "+e,false);
		} catch (EmptyClaimListException e) { 
			e.printStackTrace();
			assertTrue("EmptyClaimListException "+e,false);
		}		
	}
	
	public void testClaimListManager() {
		try {
			ClaimList cl = new ClaimList();
			Claim testClaim = new Claim("TestClaim");
			cl.addClaim(testClaim);
			ClaimListManager clm = new ClaimListManager(getContext());
			clm.saveClaimList(cl);
			ClaimList cl2 = clm.loadClaimList();
			assertTrue("cl2 Size is not consistent",cl2.size() == 1);
			assertTrue("Chosen Claim doesn't have same name", cl2.chooseClaim().equals(testClaim));
			assertTrue("TestClaim is not in Claim List2",cl2.contains(testClaim));
			assertTrue("TestClaim is in Claim List",cl.contains(testClaim));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue("IOException Thrown "+e.toString(),false);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			assertTrue("ClassNotFoundException Thrown "+e.toString(),false);			
		} catch (EmptyClaimListException e) {
			e.printStackTrace();
			assertTrue("EmptyClaimListException "+e,false);
		}
	}
}
