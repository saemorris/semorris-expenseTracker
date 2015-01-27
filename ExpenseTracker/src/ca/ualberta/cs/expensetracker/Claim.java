package ca.ualberta.cs.expensetracker;

import java.io.Serializable;


public class Claim implements Serializable {

	/**
	 * Claim serialization ID
	 */
	private static final long serialVersionUID = -4886379463491912930L;
	protected String name;
    protected String startDate;
    protected String endDate;
    protected String descrpition;

    public Claim(){
    	
    }
    
    public Claim(String name){
    	this.name = name;
    }
    
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
    
    public boolean equals (Object compareClaim){
    	if (compareClaim != null && compareClaim.getClass()==this.getClass()) {
    		return this.equals((Claim)compareClaim);
    	} else {
    		return false;
    	}
    }
    
    public boolean equals(Claim compareClaim){
    	if (compareClaim == null){
    		return false;
    	}
    	return getName().equals(compareClaim.getName());
    }
    
    public int hashcode(){
    	return ("Claim: " + getName()).hashCode();
    }

}