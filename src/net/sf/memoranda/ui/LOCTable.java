package net.sf.memoranda.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
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

public class LOCTable extends JFrame   {

	/**
	 * 
	 */

	private JPanel contentPane;
	private JTable displayTable;
	private JTextField search_Txt;
	private JLabel lblNewLabel;
	private String errorMsg;
	private JCheckBox contains_Chk;
	private final String EMPTYSTRING = "Please type in a FileName in the Search Bar";
	private final String NONJAVAFILE = "Please type in a java source file name"; //try to research read-only
	private Hashtable<String,String> matches;
	public static final Object[] COLUMNAMES = { "SourceFile", "LOC" };
	private Hashtable<Object,Object> tableData;
	
	/*
	 * Constructor for LOCTable takes in a 2D array of data and array columnNames
	 */
	public LOCTable(Object[][] data,Object[] ColumnNames)
	{
		tableData = new Hashtable<Object,Object>();
		arrayToHash(data);
		System.out.println(tableData);//BOI THIS IS IT WE GOTR IT SEARCH FOR DATA HERE
		errorMsg = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		displayTable = new JTable(data,ColumnNames){
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
		JScrollPane scroll = new JScrollPane(displayTable);
		scroll.setBounds(5, 74, 432, 205);
		contentPane.add(scroll);
		//table.setModel(new CustomTableModel());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	private void arrayToHash(Object[][] data){
		Object[] row = new Object[2];
	
		for (int i = 0; i < data.length; i++) {
			int counter = 0;
			for (int j = 0; j < data[i].length; j++) {
				row[counter] = data[i][j];
				++counter;
				
			}
			tableData.put(row[0],row[1]);	
		}
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

	private boolean searchTable(String s){
		matches = new Hashtable<String,String>();
		boolean isContainsChecked = contains_Chk.isSelected();
		boolean result = false;
		String LOC;
		
			Set<Object> keys = tableData.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				
				String fileName = (String) it.next();
				LOC = (String) tableData.get(fileName);
				if(fileName.equals(s)){
					result = true;
					matches.put(fileName,LOC);
				}
				else if(fileName.contains(s) && isContainsChecked){
					result = true;
					matches.put(fileName,LOC);
					
				}
			}
		
		System.out.println(result);
		return result;
	}
	
	private void displaySearchResults() {
		
		//Display table and actual data table
		//copy and data tables
		if(!matches.isEmpty()){
			System.out.println("we in");
			Set<String> keys = matches.keySet();
			Iterator it = keys.iterator();
			int max_Row = displayTable.getRowCount();
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
			
			System.out.println(displayTable.getValueAt(2,0));
		}
	
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
