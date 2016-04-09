package net.sf.memoranda.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.KeyAdapter;
import net.sf.memoranda.util.LOCReader;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;

public class LOCTable extends JFrame  {

	private JPanel contentPane;
	private JTable displayTable;
	private JTextField search_Txt;
	private JLabel lblNewLabel;
	private String errorMsg;
	private JCheckBox contains_Chk;
	private final String EMPTYSTRING = "Please type in a FileName in the Search Bar";
	private final String NONJAVAFILE = "Please type in a java source file name";
	private final JTable copyTable; //try to research read-only
	private Hashtable<String,String> matches;
	public static final Object[] COLUMNAMES = { "SourceFile", "LOC" };
	
	
	/*
	 * Constructor for LOCTable takes in a 2D array of data and array columnNames
	 */
	public LOCTable(Object[][] data,Object[] ColumnNames)
	{
		 
		errorMsg = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		displayTable = new JTable(data,ColumnNames){
			///private static final long serialVersionUID = 1L;

			//do not allow for cell editing
			//NOTE Source code for this method was found at
			//https://www.daniweb.com/programming/software-development/threads/279060/how-do-you-prevent-editing-of-jtable-cells
			//@Override
			//public boolean isCellEditable(int row, int column){
				//return false;
			//}
		};
		copyTable = new JTable(data,ColumnNames){
			private static final long serialVersionUID = 1L;

			//do not allow for cell editing
			//NOTE Source code for this method was found at
			//https://www.daniweb.com/programming/software-development/threads/279060/how-do-you-prevent-editing-of-jtable-cells
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		displayTable.setBounds(1, 1, 430, 0);
		contentPane.add(displayTable, BorderLayout.CENTER);
		contentPane.add(displayTable.getTableHeader(),BorderLayout.PAGE_START);
		this.setTitle("Import LOC");
		contentPane.setLayout(null);
		
		ImageIcon ic = new ImageIcon("lib/icons/MagGlass.png");
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(ic);
		lblNewLabel.setBounds(5, 27, 40, 43);
		contentPane.add(lblNewLabel);
		
		search_Txt = new JTextField();
		search_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String input = search_Txt.getText();
					boolean temp = checkInput(input);
					if(temp){
						
						boolean searchResult = searchTable(input);
						//System.out.println(searchResult);			
						displaySearchResults();
					}
				}
				
			}
		});
		search_Txt.setToolTipText("Enter A File Name to be Searched");
		search_Txt.setBounds(48, 47, 108, 20);
		contentPane.add(search_Txt);
		search_Txt.setColumns(10);
		
		contains_Chk = new JCheckBox("Contains Name");
		contains_Chk.setToolTipText("Searches for Any File that contains this String");
		contains_Chk.setBounds(241, 47, 119, 23);
		contentPane.add(contains_Chk);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(241, 27, 49, 13);
		contentPane.add(tabbedPane);
		JScrollPane scroll = new JScrollPane(displayTable);
		scroll.setBounds(5, 74, 432, 205);
		contentPane.add(scroll);
		//table.setModel(new CustomTableModel());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private boolean checkInput(String input){
		boolean result = true;
		if(input.isEmpty()){
			result = false;
			errorMsg = EMPTYSTRING;
			
			
			
			//display message string empty
		}
		/*
		if(!(input.endsWith(LOCReader.JAVAEXTENSION)) && contains_Chk.isSelected()){
			result = false;
			errorMsg = NONJAVAFILE;
			//display error message for non java file
		}
		*/
		return result;
		
	}
	private boolean searchTable(String s) {
		
		matches = new Hashtable<String,String>();
		int max_Row = copyTable.getRowCount();
		int max_Col = copyTable.getColumnCount();
		System.out.println(max_Row);
		System.out.println(max_Col);

		int row_Counter = 0;
		int col_Counter = 0;
		boolean isContainsChecked = contains_Chk.isSelected();
		boolean hasBeenFound = false;
		String currentFileName = "";
		String currentLOC = "";
		/*
		while(row_Counter <= max_Row - 1) { //&& !hasBeenFound){///used to be col_Counter
			
			//Im thinking about searching just using a part of the file as a key too
			System.out.println(col_Counter +"-"+ row_Counter);
			currentFileName = (String) copyTable.getValueAt(col_Counter,row_Counter);
			currentLOC = (String) copyTable.getValueAt(col_Counter,row_Counter + 1);
			System.out.println(currentFileName + ", boi " + currentLOC);
			if(currentFileName.equals(s)){
				hasBeenFound = true;
				//put filename and loc into table
				matches.put(currentFileName,currentLOC);
			}
			else if(currentFileName.contains(s) && isContainsChecked){
				
				matches.put(currentFileName,currentLOC);
			}
			++row_Counter;
		}
	*/
		
		while(row_Counter <= max_Row - 1) { //&& !hasBeenFound){///used to be col_Counter
			
			//Im thinking about searching just using a part of the file as a key too
			System.out.println(row_Counter +"-"+ col_Counter);
			currentFileName = (String) copyTable.getValueAt(row_Counter,col_Counter);
			currentLOC = (String) copyTable.getValueAt(row_Counter,col_Counter + 1);
			System.out.println(currentFileName + ", " + currentLOC);
			if(currentFileName.equals(s)){
				hasBeenFound = true;
				//put filename and loc into table
				matches.put(currentFileName,currentLOC);
			}
			else if(currentFileName.contains(s) && isContainsChecked){
				
				matches.put(currentFileName,currentLOC);
			}
			++row_Counter;
		}
		//System.out.println(copyTable.getValueAt(2,0));
		
		System.out.println(matches);
		return hasBeenFound;
		
		
	}
	private void displaySearchResults(){
		
		//Display table and actual data table
		//copy and data tables
		if(!matches.isEmpty()){
			System.out.println("we in");
			Set<String> keys = matches.keySet();
			Iterator it = keys.iterator();
			int max_Row = copyTable.getRowCount();
			int row_Counter = 0;
			int col_Counter = 0;
			//modify data table
			//going to need to go in constructor and modify isEditable to copy
			while(it.hasNext()){
				
				String key = (String) it.next();
				String LOC = (String) matches.get(key);
				System.out.println(key + "," + LOC);
			
				displayTable.setValueAt(key,row_Counter,col_Counter);
				displayTable.setValueAt(LOC,row_Counter,col_Counter + 1);
				++row_Counter;
				//edit Table
				
			}

			//++row_Coun//perhaps set to invsisble??
			//for some reason still soverwrite copyTable
			//ter;
			//while all we got to do now is this 
			//iterate through rest of table reset everything
			while(row_Counter <= max_Row - 1){
				
				//set everything empty
				displayTable.setValueAt("-",row_Counter,col_Counter);
				displayTable.setValueAt("-",row_Counter,col_Counter + 1);
				
				++row_Counter;
			}
			
			System.out.println(copyTable.getValueAt(2,0));
		}
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
