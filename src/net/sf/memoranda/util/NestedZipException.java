package net.sf.memoranda.util;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class NestedZipException extends Exception {

	public NestedZipException() {	
	}
	
	public void DisplayErrorMsg(){
		JOptionPane.showMessageDialog(null,"Nested Zip Found in Zip Aborting Import",
	    		"NestedZip",JOptionPane.ERROR_MESSAGE);
	}

}
