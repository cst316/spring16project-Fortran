package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.KeyAdapter;
import net.sf.memoranda.util.LOCReader;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LOCTable extends JFrame   {

	/**
	 * 
	 */

	private JPanel contentPane;
	private JTable displayTable;
	private JTextField search_Txt;
	private JLabel lblNewLabel;
	JLabel lbl_Msg;
	private String errorMsg;
	private JCheckBox contains_Chk;
	private final static String EMPTYSTRING = "Please type in a FileName";
	private final static String FNF = "Sorry Could not Find File";
	private Hashtable<String,String> matches;
	static final Object[] COLUMNAMES = { "SourceFile", "LOC" };
	private Hashtable<Object,Object> tableData;
	private DefaultTableModel dt;
	
	/*
	 * Constructor for LOCTable takes in a 2D array of data and array columnNames
	 */
	public LOCTable(Object[][] data,Object[] ColumnNames)
	{
		
		tableData = new Hashtable<Object,Object>();
		Object[][] filteredData = markSecondOcc(data);
		arrayToHash(filteredData);
		dt = new DefaultTableModel(filteredData,ColumnNames);
		errorMsg = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		displayTable = new JTable(dt){//filteredData,ColumnNames
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
						if(!searchResult){
							errorMsg = FNF;
							DisplayMsg();
						}
						else{
							lbl_Msg.setText("");
							displaySearchResults();
						}
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
		contains_Chk.setBounds(194, 46, 117, 23);
		contentPane.add(contains_Chk);
		
		JButton btnRefresh = new JButton("Refresh Table");
		btnRefresh.setToolTipText("Click to show All Files");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable();
			}
		});
		btnRefresh.setBounds(317, 46, 113, 23);
		contentPane.add(btnRefresh);
		
		lbl_Msg = new JLabel("New label");
		lbl_Msg.setBounds(48, 27, 178, 14);
		contentPane.add(lbl_Msg);
		JScrollPane scroll = new JScrollPane(displayTable);
		scroll.setBounds(5, 74, 432, 205);
		contentPane.add(scroll);
	
		removeEmptyRows();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		lbl_Msg.setVisible(false);
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
	private void DisplayMsg(){
		
		lbl_Msg.setVisible(true);
		lbl_Msg.setText(errorMsg);
		lbl_Msg.setForeground(Color.RED);
		
	}
	private boolean checkInput(String input){
		boolean result = true;
		if(input.isEmpty()){
			result = false;
			errorMsg = EMPTYSTRING;
			DisplayMsg();
		}
		return result;
		
	}
	private void refreshTable(){
		
		int row_Counter = 0;
		int col_Counter = 0;
		
		
		int max_Row = displayTable.getRowCount();
		System.out.println(max_Row);
		//get iterator for hashtable
		Set<Map.Entry<Object,Object>> keys = tableData.entrySet();//Map.entry<Object,Object>
		Iterator it = keys.iterator();
		String LOC;
		String fileName;
		for(Entry entry : keys){
			
			fileName = (String) entry.getKey();
			LOC = (String) tableData.get(fileName);
			
			displayTable.setValueAt(fileName,row_Counter,col_Counter);
			displayTable.setValueAt(LOC,row_Counter,col_Counter + 1);
			++row_Counter;
			
		}
		
	}
	private void removeEmptyRows(){
		
		
		final int col_Counter = 0;
		
		int max_Row = displayTable.getRowCount();
		int row_Counter = max_Row - 1;
		//System.out.println(max_Row);
		while(row_Counter >= 0){
			//System.out.print(row_Counter + " ");
			String temp = (String) displayTable.getValueAt(row_Counter,col_Counter);
			//System.out.println(temp + " " + displayTable.getValueAt(row_Counter,col_Counter + 1));
			if(temp.equals("-")){
				//System.out.println("come on");
				dt.removeRow(row_Counter);
				tableData.remove(temp);
			}
			--row_Counter;
		}

		//deelte empty string in hashTable too
		
	}
	/*
	 *Marks Second Occurence of an Object in a 2D array with a string "-"   
	 */
	public Object[][] markSecondOcc(Object[][] data){
		
		 Object pointer;
		 final int COL_POS = 0;
		 int counter = 0;
		 int counter2 = 0;
		 boolean flag = false;
		 
		 while(counter <= data.length - 1){
			 
			 pointer = data[counter][COL_POS];
			 counter2 = counter + 1;
			 while(counter2 <= data.length - 1){
				 
				 if(pointer.equals(data[counter2][COL_POS])){
					 data[counter2][COL_POS] = "-";
					 data[counter2][COL_POS + 1] = "-";
					 flag = true;
				 }
				 ++counter2;
			 }
			
			 ++counter; 
		 }
		return data;
	}

	public boolean searchTable(String s) {
		matches = new Hashtable<String,String>();
		boolean isContainsChecked = contains_Chk.isSelected();
		boolean result = false;
		boolean isInTable = tableData.get(s) != null;
		String LOC;
		if(!isContainsChecked && isInTable){
			
			String loc = (String) tableData.get(s);
			matches.put(s,loc);
			result = true;
		}
		else if(isContainsChecked){
			
			Set<Map.Entry<Object,Object>> keys = tableData.entrySet();//Map.entry<Object,Object>
			Iterator it = keys.iterator();
			for(Entry entry : keys){
				
				String fileName = (String) entry.getKey();
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
		}
		return result;
	}
	
	private void displaySearchResults() {
		
		//Display table and actual data table
		//copy and data tables
		if(!matches.isEmpty()){
			//System.out.println("we in");
			Set<Map.Entry<String,String>> keys = matches.entrySet();
			Iterator it = keys.iterator();
			int max_Row = displayTable.getRowCount();
			int row_Counter = 0;
			int col_Counter = 0;
			//modify data table
			//going to need to go in constructor and modify isEditable to copy
		    for(Entry entry : keys){
		    	
		    	String key = (String) entry.getKey();
				String LOC = (String) matches.get(key);
				System.out.println(key + "," + LOC);
			
				displayTable.setValueAt(key,row_Counter,col_Counter);
				displayTable.setValueAt(LOC,row_Counter,col_Counter + 1);
				++row_Counter;
		    }
			while(row_Counter <= max_Row - 1){
				
				//set everything empty
				displayTable.setValueAt("-",row_Counter,col_Counter);
				displayTable.setValueAt("-",row_Counter,col_Counter + 1);
				
				++row_Counter;
			}
			
		}
	
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
