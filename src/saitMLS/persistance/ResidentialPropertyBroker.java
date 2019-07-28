/**
 * 
 */
package saitMLS.persistance;

import java.io.*;
import java.util.*;

import saitMLS.exceptions.*;
import saitMLS.persistence.Broker;
import saitMLS.problemDomain.ResidentialProperty;
import utility.*;

/**
 * @author Qiqi Wu
 *
 */
public class ResidentialPropertyBroker implements Broker{

	private static final String INPUT_FILE = "res/resprop.txt";
	private static final String SERIALIZED_FILE = "res/resprop.ser";
	private long nextId;
	private static ResidentialPropertyBroker propertyBroker;
	private ArrayList<ResidentialProperty> propertyList = new ArrayList<>();
	
	private ResidentialPropertyBroker() {
		
	}
	
	/**
	 * Method to create an instance of ResidentialPropertyBroker.
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException
	 * @throws InvalidLegalDescriptionException
	 * @throws InvalidNumberOfBathroomsException
	 */
	public static ResidentialPropertyBroker getBroker() throws IOException, ClassNotFoundException, NumberFormatException, InvalidLegalDescriptionException, InvalidNumberOfBathroomsException {
		
		File file = new File(SERIALIZED_FILE);
		
		if(file.exists()){
			
			if(propertyBroker == null){
				
				propertyBroker = new ResidentialPropertyBroker();
				propertyBroker.loadSerializedFile();
			}
		}else{
			
			if(propertyBroker == null){
				
				propertyBroker = new ResidentialPropertyBroker();
				propertyBroker.loadResidentialPropertyLinkedList();
				propertyBroker.loadSerializedFile();
			}
			
		}
		return propertyBroker;
		
	}
	
	/**
	 * Method fills a Singly Linked List as a data structure to store residential property information in a object format. 
	 * This method is then used to create a serialized file if no serialized file for the residential property currently exists.
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws InvalidLegalDescriptionException
	 * @throws InvalidNumberOfBathroomsException
	 */
	private void loadResidentialPropertyLinkedList() throws IOException, NumberFormatException, InvalidLegalDescriptionException, InvalidNumberOfBathroomsException {
		
		BufferedReader input = new BufferedReader(new FileReader(INPUT_FILE));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		SLL loadList = new SLL();
		
		String line = input.readLine();
		nextId = 1;
		
		while(line != null) {
		
			String[] tokens;
			tokens = line.split(";");
			ResidentialProperty residentialProperty = null;
			
			residentialProperty = new ResidentialProperty(nextId,tokens[0],tokens[1],tokens[2],
								tokens[3],Double.parseDouble(tokens[4]),tokens[5],Double.parseDouble(tokens[6]),
								Double.parseDouble(tokens[7]),Integer.parseInt(tokens[8]),tokens[9].charAt(0));
			
			loadList.append(residentialProperty);
			nextId++;
			line = input.readLine();
		}
		input.close();
		oos.writeObject(loadList);
		oos.close();
	}
	
	/**
	 * Method loads the serialized linked list file to propertyList attribute.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void loadSerializedFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
		
		SLL list = (SLL) ois.readObject();
		
		for(int i = 0; i < list.size(); i++)
		{
			propertyList.add((ResidentialProperty) list.get(i));
			System.out.println(propertyList.get(i));
		}
		ois.close();
	}
	
	/**
	 * Method saves the propertyList to a serialized linked list file.
	 * @throws IOException
	 */
	private void saveSerializedFile() throws IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		
		SLL saveList = new SLL();
		
		for(int i = 0; i < propertyList.size(); i++)
		{
			saveList.append((ResidentialProperty) propertyList.get(i));
		}
		oos.writeObject(saveList);
		oos.close();
	}
	
	/**
	 * Method to release resources allocated to the broker and save all modified data. 
	 * NOTE if this method is not called modified data may be lost.
	 */
	@Override
	public void closeBroker() {

		try {
			saveSerializedFile();
			propertyBroker = null;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to add residential properties to the ArrayList database. 
	 * If the property's member id is zero then the residential property is assigned the next id in the sequence of id's. 
	 * If the residential property already exists then the old record is overwritten with the new information.
	 */
	@Override
	public boolean persist(Object o) {
		
		boolean flag = false;
		int index;
		
		ResidentialProperty residentialProperty = (ResidentialProperty)o;

		if(residentialProperty.getId()==0) {
			
			long maxID = propertyList.size();
				 
			residentialProperty.setId(maxID+1);
				 
			propertyList.add(residentialProperty);
				
			flag = true;
				 
		}else if(residentialProperty.getId()>0) {
				
			for(int i=0;i<propertyList.size();i++) {
					
				if(propertyList.get(i).getId()==residentialProperty.getId()) {
					index = i;
					propertyList.set(index, residentialProperty);
				}
			}
			
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Method to remove a ResidentialProperty from the data base. 
	 * The method returns true if ResidentialProperty was removed and false if no ResidentialProperty was removed.
	 */
	@Override
	public boolean remove(Object o) {
		
		boolean flag = false;
		ResidentialProperty residentialProperty = (ResidentialProperty)o;
		
		for(int i=0;i<propertyList.size();i++) {
			
			if(propertyList.get(i).getId()==residentialProperty.getId()) {
	
				propertyList.remove(propertyList.get(i));
				flag = true;
			}
		}
		
		return flag;
	}
	
	/**
	 * Method to search the database for matching items. The search will search based on legal description, id, quadrant or price. 
	 * The search using price will return any records with a price equal to or less than the one provided to the search. 
	 * The value of item should be the value that is being searched for i.e. . "2519S1128-30", "legal description". 
	 * The type should be the type of search required and the valid types are "legal description", "id", "quadrant" and "price".
	 */
	@Override
	public java.util.List search(String searchItem, String type) {
		
		ArrayList<ResidentialProperty> searchList = new ArrayList<ResidentialProperty>();
		
		if(type.equalsIgnoreCase("id")) {
			
			long item = Long.parseLong(searchItem);
			
			for(int i=0;i<propertyList.size();i++) {
				
				if(propertyList.get(i).getId()==item) {
		
					searchList.add(propertyList.get(i));
				}
			}
		}else if(type.equalsIgnoreCase("legal description")) {
			
			for(int i=0;i<propertyList.size();i++) {
				
				if(propertyList.get(i).getLegalDescription().equals(searchItem)) {
		
					searchList.add(propertyList.get(i));
				}
			}
		}else if(type.equalsIgnoreCase("quadrant")){
			
			for(int i=0;i<propertyList.size();i++) {
				
				if(propertyList.get(i).getQuadrant().equals(searchItem)) {
		
					searchList.add(propertyList.get(i));
				}
			}
		}else if(type.equalsIgnoreCase("price")) {
			
			double price = Double.parseDouble(searchItem);
			
			for(int i=0;i<propertyList.size();i++) {
				
				if(propertyList.get(i).getAskingPrice()==price||propertyList.get(i).getAskingPrice()<price) {
		
					searchList.add(propertyList.get(i));
				}
			}
		}
		
		return searchList;
	}
	
}
