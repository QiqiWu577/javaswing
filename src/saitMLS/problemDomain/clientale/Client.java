package saitMLS.problemDomain.clientale;

import javax.swing.JOptionPane;

import saitMLS.exceptions.clientale.InvalidPhoneNumberException;
import saitMLS.exceptions.clientale.InvalidPostalCodeException;

/**
 * Client.java - Class describing all attributes and operations for a Client object.
 * @author Qiqi Wu
 *
 */
public class Client {

	private boolean active;			//1
	private long id;				//8
	private String firstName;		//22
	private String lastName;		//22
	private String address;			//52
	private String postalCode;		//9
	private String phoneNumber;		//14
	private char clientType;		//2
	
	public static final int SIZE = 130;
	
	/**
	 * the default constructor
	 */
	public Client() {
		
	}
	
	/**
	 * Construct a new Client object with specified values for its attributes.
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param postalCode
	 * @param phoneNumber
	 * @param clientType
	 * @throws InvalidPostalCodeException 
	 * @throws InvalidPhoneNumberException 
	 */
	public Client(long id,String firstName,String lastName,String address,
			String postalCode,String phoneNumber,char clientType) throws InvalidPostalCodeException, InvalidPhoneNumberException {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		validatePostalCode(postalCode);
		validatePhoneNumber(phoneNumber);
		this.clientType = clientType;
		active = true;
		
	}
	
	/**
	 * Construct a new Client object with a String.
	 * @param line
	 */
	public Client(String line) {
		
		String fields[] = line.split(";");
		
		try {
			firstName = fields[0];
			lastName = fields[1];
			address = fields[2];
			setPostalCode(fields[3]);
			setPhoneNumber(fields[4]);
			clientType = fields[5].charAt(0);
			active = true;
		} catch (InvalidPhoneNumberException e) {
			
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 *  Method to set the id of the record to the specified value.
	 * @param id the id to set
	 */
	public void setId(long id) {
		
		this.id = id;
	}

	/**
	 *  Method to set the first name of the record to the specified value.
	 * @param firstName 
	 */
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}

	/**
	 * Method to set the last name of the record to the specified value.
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	/**
	 * Method to set the address of the record to the specified value.
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		
		this.address = address;
	}

	/**
	 * Method to set the postal code of the record to the specified value.
	 * @param postalCode the postalCode to set
	 * @throws InvalidPostalCodeException 
	 */
	public void setPostalCode(String postalCode) throws InvalidPostalCodeException {
		
		validatePostalCode(postalCode);
	}

	/**
	 * Method to set the phone number of the record to the specified value.
	 * @param phoneNumber the phoneNumber to set
	 * @throws InvalidPhoneNumberException 
	 */
	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		
		validatePhoneNumber(phoneNumber);
	}

	/**
	 * Method to set the client type of the record to the specified value.
	 * @param clientType the clientType to set
	 */
	public void setClientType(char clientType) {
		
		this.clientType = clientType;
	}

	/**
	 * Method to return id of the record.
	 * @return id
	 */
	public long getId() {
		
		return id;
	}

	/**
	 * Method to return first name of the record.
	 * @return firstName
	 */
	public String getFirstName() {
		
		return firstName;
	}

	/**
	 * Method to return last name of the record.
	 * @return lastName
	 */
	public String getLastName() {
		
		return lastName;
	}

	/**
	 * Method to return client type of the record.
	 * @return clientType
	 */
	public char getClientType() {
		
		return clientType;
	}

	/**
	 * Method to return address of the record.
	 * @return address
	 */
	public String getAddress() {
		
		return address;
	}

	/**
	 * Method to return postal code of the record.
	 * @return postalCode
	 */
	public String getPostalCode() {
		
		return postalCode;
	}

	/**
	 * Method to return phone number of the record.
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		
		return phoneNumber;
	}
	
	/**
	 * Method to return active value of the record.
	 * @return active
	 */
	public boolean isActive() {
		
		return active;
	}
	
	/**
	 * Method to set the active of the record to the specified value.
	 * @param active
	 */
	public void setActive(boolean active) {
		
		this.active = active;
	}

	/**
	 * Method to return a whole record with specified values.
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", active=" + active +", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + ", clientType=" + clientType + "]";
	}
	
	/**
	 * Method to validate the postal code.
	 * @param postalCode
	 * @throws InvalidPostalCodeException
	 */
	private void validatePostalCode(String postalCode) throws InvalidPostalCodeException {
		
		String format = "[A-Z][0-9][A-Z]\\s[0-9][A-Z][0-9]";
				
		if (postalCode.matches(format)) {
			
			this.postalCode = postalCode;
		}else {
			JOptionPane.showMessageDialog(null,"Please re-enter the postal code. e.g.T3R 0V3");
			throw new InvalidPostalCodeException();
		}
	}
	
	/**
	 * Method to validate the phong number.
	 * @param phoneNumber
	 * @throws InvalidPhoneNumberException
	 */
	private void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		
		String format = "\\d{3}-\\d{3}-\\d{4}";
		
		if (phoneNumber.matches(format)) {
			
			this.phoneNumber = phoneNumber;
		}else {
			JOptionPane.showMessageDialog(null,"Please re-enter the phone number. e.g. 587-966-3732");
			throw new InvalidPhoneNumberException();
		}
	}
	
}
