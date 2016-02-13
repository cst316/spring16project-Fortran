/**
 * 
 */
package net.sf.memoranda.util;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
/**
 * @author Mike
 * Writes LOCReader object to File
 */
public class LOCWriter {
	
	
	private String configPath = System.getProperty("user.home") + File.separator 
	     	+ ".memoranda" + File.separator;
	
	public LOCWriter(LOCReader reader){
		 File savedLOC = new File(configPath + "savedLOC.txt");
		try {
			
			if(!savedLOC.exists()){
			
				savedLOC.createNewFile();//create file in Users.memoranda
				FileWriter writer = new FileWriter(savedLOC);
				writer.write(reader.getFileName() + " ");
				String loc_Str = Integer.toString(reader.getLOC());
				writer.write(loc_Str);
				writer.close();
			
			}
			else{
				
				FileWriter writer = new FileWriter(savedLOC,true);
				writer.append(reader.getFileName() + " ");
				String loc_Str = Integer.toString(reader.getLOC());
				writer.append(loc_Str);
				writer.close();
			
			}
		
		}
		catch(Exception e){
			
			JFrame frame = new JFrame();
		    JOptionPane.showMessageDialog(null,"Error",
		    		"Oops something went wrong try importing Source Code again",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	

}
