package frame;
import java.io.IOException;

import saitMLS.persistance.CommercialPropertyBroker;
import saitMLS.persistance.ResidentialPropertyBroker;
import saitMLS.persistence.clientale.ClientBroker;

/**
 * AppDriver.java - Application Driver class for MyFrame class exercise.
 * @author Qiqi Wu
 *
 */

public class AppDriver {

	/**
	 * call the MtFrame class.
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		new MyFrame();
		
	}

}
