package ca.ualberta.cs.expensetracker;

import java.io.IOException;

public class ClaimListController {
	//Lazy Singleton
	private static ClaimList claimList = null;
	//Warning: throws runTime exception
	static public ClaimList getClaimList(){
		if (claimList == null){
			try {
				claimList = ClaimListManager.getManager().loadClaimList();
				claimList.addListener(new Listener() {
					@Override
					public void update(){
						saveClaimList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager, ClassNotFoundException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager, IOException");
			}
		}
		return claimList;
	}
	
	static public void saveClaimList(){
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
		}
	}

	public Claim chooseClaim() throws EmptyClaimListException {
		return getClaimList().chooseClaim();
	}
	
	public void addClaim(Claim claim){
		getClaimList().addClaim(claim);
	}
}
