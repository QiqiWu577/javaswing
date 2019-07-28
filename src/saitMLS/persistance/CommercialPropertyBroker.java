/**
 * 
 */
package saitMLS.persistance;

import java.io.*;
import java.util.*;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.persistence.Broker;
import saitMLS.problemDomain.CommercialProperty;
import utility.*;
import utility.List;


/**
 * @author Qiqi Wu
 *
 */
public class CommercialPropertyBroker implements Broker{

	private static final String INPUT_FILE = "res/comprop.txt";
	private static final String SERIALIZED_FILE = "res/comprop.ser";
	private long nextId;
	private static CommercialPropertyBroker propertyBroker;
	private ArrayList<CommercialProperty> propertyList = new ArrayList<>();
	
	private CommercialPropertyBroker() {
		
		
	}
	
	/**
	 * Method to create an instance of CommercialPropertyBroker.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException
	 * @throws InvalidLegalDescriptionException
	 */
	public static CommercialPropertyBroker getBroker() throws FileNotFoundException, IOException, ClassNotFoundException, NumberFormatException, InvalidLegalDescriptionException {
		
		File file = new File(SERIALIZED_FILE);
		
		if(file.exists()){
			
			if(propertyBroker == null){
				
				propertyBroker = new CommercialPropertyBroker();
				propertyBroker.loadSerializedFile();
			}
		}else{
			
			if(propertyBroker == null){
				
				propertyBroker = new CommercialPropertyBroker();
				propertyBroker.loadCommercialPropertyLinkedList();
				propertyBroker.loadSerializedFile();
			}
			
		}
		return propertyBroker;
		
	}
	
	/**
	 * Method fills a Singly Linked List as a data structure to store commercial property information in a object format. 
	 * This method is then used to create a serialized file if no serialized file for the commercial property currently exists.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws InvalidLegalDescriptionException
	 */
	private void loadCommercialPropertyLinkedList() throws FileNotFoundException, IOException, NumberFormatException, InvalidLegalDescriptionException {
		
		BufferedReader input = new BufferedReader(new FileReader(INPUT_FILE));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		SLL loadList = new SLL();

		String line = input.readLine();
		nextId = 1;
		
		while(line != null) {
		
			String[] tokens;
			tokens = line.split(";");
			CommercialProperty commercialProperty = null;
			
			commercialProperty = new CommercialProperty(nextId,tokens[0],tokens[1],tokens[2],
								tokens[3],Double.parseDouble(tokens[4]),tokens[5],tokens[6],
								Integer.parseInt(tokens[7]));
			
			loadList.append(commercialProperty);
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
			propertyList.add((CommercialProperty) list.get(i));
			System.out.println(propertyList.get(i));
		}
		ois.close();
	}
	
	/**
	 * Method saves the propertyList to a serialized linked list file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void saveSerializedFile() throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		
		SLL saveList = new SLL();
		
		for(int i = 0; i < propertyList.size(); i++)
		{
			saveList.append((CommercialProperty) propertyList.get(i));
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
	 * Method to add commercial properties to the Linked List database. 
	 * If the property's member id is zero then the commercial property is assigned the next id in the sequence of id's. 
	 * If the commercial property already exists then the old record is overwritten with the new information.
	 */
	@Override
	public boolean persist(Object o) {
		
		boolean flag = false;
		int index;
		
		CommercialProperty commercialProperty = (CommercialProperty)o;
		
		if(commercialProperty.getId()==0) {
				
			long maxID = propertyList.size();
				 
			commercialProperty.setId(maxID+1);
				 
			propertyList.add(commercialProperty);
				
			flag = true;
				 
		}else if(commercialProperty.getId()>0) {
				
			for(int i=0;i<propertyList.size();i++) {
					
				if(propertyList.get(i).getId()==commercialProperty.getId()) {
					index = i;
					propertyList.set(index, commercialProperty);
				}
			}
			
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Method to remove a CommercialProperty from the data base. 
	 * The method returns true if CommercialProperty was removed and false if no CommercialProperty was removed.
	 */
	@Override
	public boolean remove(Object o) {

		boolean flag = false;
		CommercialProperty commercialProperty = (CommercialProperty)o;
		
		for(int i=0;i<propertyList.size();i++) {
			
			if(propertyList.get(i).getId()==commercialProperty.getId()) {
	
				propertyList.remove(propertyList.get(i));
				flag = true;
			}
		}
		
		return flag;
	}
	
	/**
	 * Method to search the database for matching items. The search will search based on legal description, id, 
	 * quadrant or price. The search using price will return any records with a price equal to or less than the one provided to 
	 * the search. The value of item should be the value that is being searched for i.e. . "2519S1128-30", "legal description". 
	 * The type should be the type of search required and the valid types are "legal description", "id", "quadrant" and "price".
	 */
	@Override
	public java.util.List search(String searchItem, String type) {
		
		ArrayList<CommercialProperty> searchList = new ArrayList<CommercialProperty>();
		
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
