package ca.ualberta.cs.expensetracker;

public class Claim {

    protected String startDate;
    protected String endDate;
    protected String descrpition;

    public Claim(String startDate, String endDate, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.descrpition = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return descrpition;
    }

}