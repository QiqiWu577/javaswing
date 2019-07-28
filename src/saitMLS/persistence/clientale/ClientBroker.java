package saitMLS.persistence.clientale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import formatColumns.Column;
import saitMLS.exceptions.clientale.InvalidPhoneNumberException;
import saitMLS.exceptions.clientale.InvalidPostalCodeException;
import saitMLS.persistence.Broker;
import saitMLS.problemDomain.clientale.*;

/**
 * ClientBroker.java - Class describing all attributes and operations for a ClientBroker object.
 * @author Qiqi Wu
 *
 */
public class ClientBroker implements Broker{

	private static ClientBroker clientBroker;
	private RandomAccessFile raf;
	private ArrayList<Client> clientList;
	private static final String INPUT_FILE = "res/clients.txt";
	private static final String RANDOM_FILE = "res/client.bin";
	
	/**
	 * Constructor is called only when the getBroker method is called.
	 * @throws FileNotFoundException
	 */
	private ClientBroker() throws FileNotFoundException {
		
		raf = new RandomAccessFile(RANDOM_FILE,"rw");
	}
	
	/**
	 * Method controls the generation of the CustomerBroker objects.
	 * @return
	 * @throws IOException
	 */
	public static ClientBroker getBroker() throws IOException {
		
		File file = new File(RANDOM_FILE);
		
		if(file.exists())
		{
			if(clientBroker == null)
			{
				clientBroker = new ClientBroker();
			}
		}
		else
		{
			if(clientBroker == null)
			{
				clientBroker = new ClientBroker();
				clientBroker.loadBinaryFile();
			}
			
		}
		
		return clientBroker;
	}
	
	/**
	 * Creates a RandomAccessFile as a data structure to store client information in a binary format.
	 * @throws IOException
	 */
	private void loadBinaryFile() throws IOException {
		
		BufferedReader input = new BufferedReader(new FileReader(INPUT_FILE));
		
		String line = input.readLine();
		long i = 1;
		
		while(line != null) {
			
			boolean active = true;
			Client client = new Client(line);
			client.setId(i);
			client.setActive(active);
			
			writeRecord(client);
			
			line = input.readLine();
			i++;
		}
		input.close();
	}
	
	/**
	 * Method to write the record to the binary file.
	 * @param client
	 * @throws IOException
	 */
	private void writeRecord(Client client) throws IOException {
		
		raf.writeBoolean(client.isActive());//1
		raf.writeLong(client.getId());//8
		raf.writeUTF(Column.leftJustify(client.getFirstName(),20));//22
		raf.writeUTF(Column.leftJustify(client.getLastName(),20));//22
		raf.writeUTF(Column.leftJustify(client.getAddress(),50));//52
		raf.writeUTF(Column.leftJustify(client.getPostalCode(),7));//9
		raf.writeUTF(Column.leftJustify(client.getPhoneNumber(),12));//14
		raf.writeChar(client.getClientType());//2
		
	}
	
	/**
	 * Method loads the current binary file being used as the ongoing database file.
	 * @param i
	 * @return
	 * @throws IOException
	 */
	private Client readRecord(long i) throws IOException {
		
		Client client = new Client();
		
		try {
			raf.seek(i);
			
			client.setActive(raf.readBoolean());
			client.setId(raf.readLong());
			client.setFirstName(raf.readUTF().trim());
			client.setLastName(raf.readUTF().trim());
			client.setAddress(raf.readUTF().trim());
			client.setPostalCode(raf.readUTF().trim());
			client.setPhoneNumber(raf.readUTF().trim());
			client.setClientType(raf.readChar());
			
		} catch (InvalidPostalCodeException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			
			e.printStackTrace();
		}
		return client;
	}
	
	/**
	 * Method to add a new or modified record to the clients binary file.
	 * return flag
	 */
	@Override
	public boolean persist(Object o) {
		boolean flag = false;
		
		try
		{
			Client client = (Client)o;
			
				if(client.getId()==0) {
					
					 long lastBeginningLength = raf.length() - Client.SIZE;
					 Client lastClient = readRecord(lastBeginningLength);
					 
					 client.setId(lastClient.getId()+1);
					 
					 writeRecord(client);
					 flag = true;
					 
				}else if(client.getId()>0) {
					
					long offSet = searchClientId(client.getId());
					
					if(offSet >= 0) {
						
						raf.seek(offSet);
						
						writeRecord(client);
						flag = true;
					}
				}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * Method to delete a record from the client binary file.
	 * return flag
	 */
	@Override
	public boolean remove(Object o) {
		
		boolean flag = false;
		Client client = (Client)o;
		
		try {
			long offSet = searchClientId(client.getId());
			
			if(offSet >= 0) {
				
				raf.seek(offSet);
				
				raf.writeBoolean(false);
				flag = true;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Method to search for the client id number and return the beginning position of the searched record to the calling method.
	 * @param id
	 * @return i the beginning position of a record.
	 * @throws IOException
	 */
	private long searchClientId(long id) throws IOException {
		
		
		for(long i = 0L; i < raf.length(); i += Client.SIZE) {
			
			Client client = readRecord(i);
			
			if(client.getId() == id && client.isActive()) {
				
				return i;
			}
		}
		return -1L;
	}

	/**
	 * Method to search the Client RandomAccessFile back end allowing a search to be carried out based on the following criteria: membership id, name, or client type.
	 * return clientList the ArrayList of the client
	 */
	@Override
	public List search(String searchItem, String type) {
		
		clientList = new ArrayList <Client>();
		
		try {
			if(type.equalsIgnoreCase("id")) {
				
				clientList = searchId(searchItem);
			}else if(type.equalsIgnoreCase("name")) {
				
				clientList = searchName(searchItem);
			}else if(type.equalsIgnoreCase("type")){
				
				
					clientList = searchType(searchItem);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return clientList;
	}

	/**
	 * Method to search for the client id number and return all matching results as a list to the calling method.
	 * @param idString
	 * @return ArrayList<Client>
	 * @throws IOException
	 */
	private ArrayList<Client> searchId(String idString) throws IOException {
		
		ArrayList <Client> searchResult = new ArrayList<Client>();
		long id = Long.parseLong(idString);
		
		
		for(long i = 0L; i < raf.length(); i += Client.SIZE) {
			
			raf.seek(i);
			Client client = readRecord(i);
			
			if(client.getId()==id && client.isActive()) {
				
				searchResult.add(client);
			}
		}
		
		return searchResult;
	}
	
	/**
	 * Method to search for the last name of client and return all matching results as a list to the calling method.
	 * @param name
	 * @return ArrayList<Client>
	 * @throws IOException
	 */
	private ArrayList<Client> searchName(String name) throws IOException {

		ArrayList <Client> searchResult = new ArrayList<Client>();
		
		for(long i = 0L; i < raf.length(); i += Client.SIZE) {
			
			Client client = readRecord(i);
			
			if(client.getLastName().equalsIgnoreCase(name) && client.isActive()) {
				
				searchResult.add(client);
			}
		}
		return searchResult;
	}

	/**
	 * Method to search for the type of client and return all matching results as a list to the calling method where client type is 'R' for residential and 'C' for commercial.
	 * @param type
	 * @return ArrayList<Client>
	 * @throws IOException
	 */
	private ArrayList<Client> searchType(String type) throws IOException {
		
		ArrayList<Client> searchResult = new ArrayList<Client>();
		
		for(long i = 0L; i < raf.length(); i += Client.SIZE) {
			
			Client client = readRecord(i);
					
			if(Character.toString(client.getClientType()).equalsIgnoreCase(type)) {
				if(client.isActive()) {
					
					searchResult.add(client);
				}
			}
		}
		return searchResult;
	}

	/**
	 * Method to release resources allocated to the broker and save all modified data.
	 */
	public void closeBroker() {
		
		try {
			
			raf.close();
			clientBroker = null;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
