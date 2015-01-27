package ca.ualberta.cs.expensetracker;

public class Claim {

	protected String name;
    protected String startDate;
    protected String endDate;
    protected String descrpition;

    public Claim(String name, String startDate, String endDate, String description) {
    	this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descrpition = description;
    }
    
    public String getName(){
    	return name;
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
    
    public String toString(){
    	return getName();
    }

}