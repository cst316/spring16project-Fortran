/**
 * 
 */
package net.sf.memoranda.util;
import java.io.*;
import java.util.Hashtable;
import java.util.Set;
import java.util.TimerTask;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import net.sf.memoranda.ui.StopWatch;

import javax.xml.transform.OutputKeys;
/**
 * @author Mike
 * Writes LOCReader object to File
 */
public class LOCWriter {
	
	//Constants for XML tags
	 private final String LOC_STR = "LOCLIST";
	 private final String LOCF_STR = "LOCFILE";
	 private final String SOURCEFILE = "SOURCEFILE";
	 private final String LOCINT = "LOC";
	 
	private String configPath = System.getProperty("user.home") + File.separator 
	     	+ ".memoranda" + File.separator;
	/**
	 * Creates nodes and attaches it to a root node
	 * @param document document to be wrote to 
	 * @param reader reader object that contains LOC and fileName from Source File
	 * @param root the root node to append to 
	 * @return an Element containing LOC and fileName from LOCReader
	 */
	private Element toElement(Document document,LOCReader reader,Node root){
		
		
		Element locFile = null; //= document.createElement(LOCF_STR);
		//root.appendChild(locFile);
		
		
		Hashtable<String,Integer> temp = reader.getLocTable();
		System.out.println("HashTable " + temp.toString());
		//insert while loop enumeration in here
		Set<String> keys = temp.keySet();
		System.out.println(keys);
		Iterator it = keys.iterator(); 
		while(it.hasNext()){
			
			//grab key from set 
			String fileNameKey = (String) it.next();
		
			//grab value from table
			Integer valueLOC = temp.get(fileNameKey);
			
			//create sourcefile node 
			locFile = document.createElement(LOCF_STR);
			root.appendChild(locFile);
			Element sourceName = document.createElement(SOURCEFILE); 
			sourceName.appendChild(document.createTextNode(fileNameKey));//reader.getFileName())
			locFile.appendChild(sourceName);
	
			//create LOC node
			Element locInt = document.createElement(LOCINT);
			String LOC_str = Integer.toString(valueLOC);//reader.getLOC()
			locInt.appendChild(document.createTextNode(LOC_str));
			locFile.appendChild(locInt);
		}
		return locFile;
		
	}
	
	/**
	 * Writes to xml storing sourcefileName and LOC Count from LOCReader
	 * @param reader
	 */
	private void writeToXML(LOCReader reader){
		
		try {

			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(configPath + "SavedLOC.xml");
			if(file.exists()){
				
				Document doc = db.parse(file);
				doc.getDocumentElement().normalize();
				
				Node root = (Node)doc.getElementsByTagName(LOC_STR).item(0);	
			
				Element locFile = toElement(doc,reader,root);
				
				root.appendChild(locFile);
				
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource dom = new DOMSource(root);
				StreamResult stream = new StreamResult(file);
				t.transform(dom,stream);
				
				
			}
			else{
				
				//refactor this to createnewXMLFile(root, )
				Document document = db.newDocument();
			
				Node root = document.createElement(LOC_STR);
				document.appendChild(root);
			   
				Node fullDoc = toElement(document,reader,root);
				root.appendChild(fullDoc);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource dom = new DOMSource(root);
				StreamResult stream = new StreamResult(file);
				t.transform(dom,stream);
			}
			
		} 
		catch(FileNotFoundException f){
			
			
		    JOptionPane.showMessageDialog(null,"File Not Found Error",
		    		"File could not be found",JOptionPane.ERROR_MESSAGE);
			
		}
		catch (Exception e) {
		
		    JOptionPane.showMessageDialog(null,"Error",
		    		"Oops something went wrong try importing Source Code again",JOptionPane.ERROR_MESSAGE);
		}
			
			
	}
	
	private void writeToXML(StopWatch st){
		
		
		
		
		
		
	}
	
	/**
	 * Constrcutor for source java file
	 * @param reader
	 */

	public LOCWriter(LOCReader reader){
		 
		try {
			writeToXML(reader);	
		}
		catch(Exception e){
			
		    JOptionPane.showMessageDialog(null,"Error",
		    		"Oops something went wrong try importing Source Code again",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	public LOCWriter(StopWatch sw){
		
		
		writeToXML(sw);
		
		
	}
	public LOCWriter(TimerTask tsk){
		
		
	}
	
	

}
