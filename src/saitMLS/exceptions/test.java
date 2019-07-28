package saitMLS.exceptions;

import javax.swing.JOptionPane;

public class test {

	public static void main(String[] args) throws InvalidLegalDescriptionException {

		String format = "(\\d{0,4})[A-Z](\\d{0,4})-(\\d{0,2})";
		String test = "8674X2034-6";
		
		
		if(test.matches(format)) {
			
			System.out.println(true);
		}else {
			JOptionPane.showMessageDialog(null,"Please re-enter the valid description. e.g. 8674X2034-61");
			throw new InvalidLegalDescriptionException();
		}
		
	}

}
