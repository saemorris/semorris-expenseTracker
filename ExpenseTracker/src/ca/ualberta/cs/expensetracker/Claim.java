package ca.ualberta.cs.expensetracker;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Claim implements Serializable, Parcelable {

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
    
    public Claim (Parcel in){
    	String[] data = new String[4];
    	
    	in.readStringArray(data);
    	this.name = data[0];
    	this.startDate = data[1];
    	this.endDate = data[2];
    	this.descrpition = data[3];
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[]{this.name, this.startDate, this.endDate, this.descrpition});
	}
	
	public static final Parcelable.Creator<Claim> CREATOR = new Parcelable.Creator<Claim>() {

		@Override
		public Claim createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Claim(source);
		}

		@Override
		public Claim[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Claim[size];
		}
	};

}