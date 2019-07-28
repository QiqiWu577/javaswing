package utility;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SLLNode implements Serializable{

	Object element;
	SLLNode next;
	
	public SLLNode(Object element) {
		
		this.element = element;
	}
}
