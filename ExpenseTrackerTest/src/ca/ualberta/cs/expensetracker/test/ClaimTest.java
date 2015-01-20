package ca.ualberta.cs.expensetracker.test;

import junit.framework.TestCase;
import ca.ualberta.cs.expensetracker.Claim;

public class ClaimTest extends TestCase {
    public void testClaim() {
        String startDate = "startDate";
        String endDate = "endDate";
        String description = "description";
        Claim claim = new Claim(startDate, endDate, description);
        assertTrue("claim start date is not equal", startDate.equals(claim.getStartDate()));
        assertTrue("Claim end date is not equal", endDate.equals(claim.getEndDate()));
        assertTrue("Claim description is not equal", description.equals(claim.getDescription()));
    }
}