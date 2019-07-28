/**
 * 
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import saitMLS.exceptions.InvalidLegalDescriptionException;

/**
 * @author Qiq Wu
 *
 */
public class CommercialProperty extends Property implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8173359382236529177L;
	private int noFloors;
	private String type;
	
	/**
	 * 
	 * @param id	unique value calculated or enter zero if new property.
	 * @param legalDescription	property description of max. 20 characters.
	 * @param address	property address of max. 80 characters.
	 * @param quadrant	city location with valid entries NE,NW,SE,SW.
	 * @param zone	applied zoning with valid entries R1,R2,R3,R4,I1,I2,I3,I4.
	 * @param askingPrice	price property is listed for.
	 * @param comments	general comments on the property.
	 * @param type	property type as either 'm' manufacturing or 'o' office.
	 * @param noFloors	number of floors in building.
	 * @throws InvalidLegalDescriptionException  when legal description does not follow the pattern where [0-9999][A-Z][0-9999][-][0-99]
	 */
	public CommercialProperty(long id,String legalDescription,String address,String quadrant, 
			String zone, double askingPrice, String comments,String type,int noFloors) throws InvalidLegalDescriptionException {
		
		super(id, legalDescription, address, quadrant, zone, askingPrice, comments);
		this.noFloors = noFloors;
		this.type = type;
	}

	/**
	 * @return the noFloors
	 */
	public int getNoFloors() {
		return noFloors;
	}

	/**
	 * @param noFloors the noFloors to set
	 */
	public void setNoFloors(int noFloors) {
		this.noFloors = noFloors;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  getId()+";"+getLegalDescription() + ";"+ getAddress()+ ";"+ getQuadrant() + ";" + 
				getZone() + ";"+getAskingPrice() + ";" + getComments() + ";"+
				getType()+";"+getNoFloors();
	}
	
	
}
