package ca.ualberta.cs.expensetracker;

public class ClaimListController {
	//Lazy Singleton
	private static ClaimList claimList = null;
	
	static public ClaimList getClaimList(){
		if (claimList == null){
			claimList = new ClaimList();
		}
		
		return claimList;
	}

	public Claim chooseClaim() throws EmptyClaimListException {
		return getClaimList().chooseClaim();
	}
	
}
