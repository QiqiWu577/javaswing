/**
 * 
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import javax.swing.JOptionPane;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;

/**
 * @author 745319
 *
 */
public class ResidentialProperty extends Property implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1571286566412938018L;
	private double area;
	private double bathrooms;
	private int bedrooms;
	private char garage;
	
	/**
	 * 
	 *  @param id	unique value calculated or enter zero if new property.
	 * @param legalDescription	property description of max. 20 characters.
	 * @param address	property address of max. 80 characters.
	 * @param quadrant	city location with valid entries NE,NW,SE,SW.
	 * @param zone	applied zoning with valid entries R1,R2,R3,R4,I1,I2,I3,I4.
	 * @param askingPrice	price property is listed for.
	 * @param comments	general comments on the property.
	 * @param area	area of property in square metres.
	 * @param bathrooms	number of bathrooms respresented as #.0 or #.5.
	 * @param bedrooms	number of bedrooms located in home.
	 * @param garage	a value of 'a', 'd' or 'n' representing attached, detached or no garage.
	 * @throws InvalidLegalDescriptionException	when legal description does not follow the pattern where [0-9999][A-Z][0-9999][-][0-99]
	 * @throws InvalidNumberOfBathroomsException	when the number of bathrooms does not end in either .0 or .5 1127V4712-24;639 Royal Crest Place;NW;R2;389591.09;Lots of space;4160.8;1.0;4;D
	 */
	public ResidentialProperty(long id,String legalDescription,String address,String quadrant, 
			String zone, double askingPrice, String comments,
			double area, double bathrooms, int bedrooms, char garage) throws InvalidLegalDescriptionException, InvalidNumberOfBathroomsException {
		
		super(id, legalDescription, address, quadrant, zone, askingPrice, comments);
		this.area = area;
		validateNumberOfBathrooms(bathrooms);
		this.bedrooms = bedrooms;
		this.garage = garage;
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * @return the bathrooms
	 */
	public double getBathrooms() {
		return bathrooms;
	}

	/**
	 * @param bathrooms the bathrooms to set
	 * @throws InvalidNumberOfBathroomsException 
	 */
	public void setBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException {
		validateNumberOfBathrooms(bathrooms);
	}

	/**
	 * @return the bedrooms
	 */
	public int getBedrooms() {
		return bedrooms;
	}

	/**
	 * @param bedrooms the bedrooms to set
	 */
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	/**
	 * @return the garage
	 */
	public char getGarage() {
		return garage;
	}

	/**
	 * @param garage the garage to set
	 */
	public void setGarage(char garage) {
		this.garage = garage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  getId() + ";" + getLegalDescription()
				+ ";" + getAddress() + ";" + getQuadrant() + ";" + getZone()
				+ ";" + getAskingPrice() + ";" + getComments()+";"+getArea()+";"+getBathrooms()+";"+getBedrooms()+
				";"+getGarage();
	}
	
	/**
	 * A method which validates a property for the correct value for the 
	 * number of bathrooms in the residence. Valid numbers for bathrooms must 
	 * end in either ".0" or ".5".
	 * @param nob	the number of bathrooms to be validated.
	 * @throws InvalidNumberOfBathroomsException	when the number of bathrooms does not end in either .0 or .5
	 */
	private void validateNumberOfBathrooms(double nob) throws InvalidNumberOfBathroomsException {
		
		String format1 = "\\d{1,}\\.[0]";
		String format2 = "\\d{1,}\\.[5]";
		String number = Double.toString(nob);
		
		if(number.matches(format1)||number.matches(format2)) {
			this.bathrooms = nob;
		}else {
			JOptionPane.showMessageDialog(null,"The number of bathrooms must end in either .0 or .5.");
			throw new InvalidNumberOfBathroomsException();
		}
	}
	
}
