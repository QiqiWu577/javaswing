/**
 * 
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import javax.swing.JOptionPane;

import saitMLS.exceptions.InvalidLegalDescriptionException;

/**
 * @author 745319
 *
 */
public class Property implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 954077763989696517L;
	private long id;
	private String legalDescription;
	private String address;
	private String quadrant;
	private String zone;
	private double askingPrice;
	private String comments;
	
	/**
	 * @param id	unique value calculated or enter zero if new property.
	 * @param legalDescription	property description of max. 20 characters.
	 * @param address	property address of max. 80 characters.
	 * @param quadrant	city location with valid entries NE,NW,SE,SW.
	 * @param zone	applied zoning with valid entries R1,R2,R3,R4,I1,I2,I3,I4.
	 * @param askingPrice	price property is listed for.
	 * @param comments	general comments on the property.
	 * @throws InvalidLegalDescriptionException
	 */
	public Property (long id,String legalDescription,String address,String quadrant, 
			String zone, double askingPrice, String comments) throws InvalidLegalDescriptionException {
		
		this.id = id;
		validateLegalDescription(legalDescription);
		this.address = address;
		this.quadrant = quadrant;
		this.zone = zone;
		this.askingPrice =askingPrice;
		this.comments = comments;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the legalDescription
	 */
	public String getLegalDescription() {
		return legalDescription;
	}

	/**
	 * @param legalDescription the legalDescription to set
	 * @throws InvalidLegalDescriptionException	when legal description does not follow the pattern where [0-9999][A-Z][0-9999][-][0-99]
	 */
	public void setLegalDescription(String legalDescription) throws InvalidLegalDescriptionException {
		validateLegalDescription(legalDescription);
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the quadrant
	 */
	public String getQuadrant() {
		return quadrant;
	}

	/**
	 * @param quadrant the quadrant to set
	 */
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return the askingPrice
	 */
	public double getAskingPrice() {
		return askingPrice;
	}

	/**
	 * @param askingPrice the askingPrice to set
	 */
	public void setAskingPrice(double askingPrice) {
		this.askingPrice = askingPrice;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Property [id=" + id + ", legalDescription=" + legalDescription + ", address=" + address + ", quadrant="
				+ quadrant + ", zone=" + zone + ", askingPrice=" + askingPrice + ", comments=" + comments + "]";
	}
	
	/**
	 * A method which validates a legal description of a property. 
	 * The legal description is the following: 8674X2034-61. 
	 * The first part will contain a value between 0 and 9999 then a character 
	 * followed by another value between 0 and 9999 then a "-" and finally a 
	 * value between 0 and 99.
	 * @param desc
	 * @throws InvalidLegalDescriptionException
	 */
	private void validateLegalDescription(String desc) throws InvalidLegalDescriptionException {
		
		String format = "(\\d{0,4})[A-Z](\\d{0,4})-(\\d{0,2})";
		
		if(desc.matches(format)) {
			
			this.legalDescription = desc;
		}else {
			JOptionPane.showMessageDialog(null,"Please re-enter the valid description. e.g. 8674X2034-61");
			throw new InvalidLegalDescriptionException();
		}
	}
	
}
