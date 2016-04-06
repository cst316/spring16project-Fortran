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
import java.util.Hashtable;
import java.awt.event.KeyAdapter;
import net.sf.memoranda.util.LOCReader;
import javax.swing.JCheckBox;

public class LOCTable extends JFrame  {

	private JPanel contentPane;
	private JTable table;
	private JTextField search_Txt;
	private JLabel lblNewLabel;
	private String errorMsg;
	private JCheckBox contains_Chk;
	private final String EMPTYSTRING = "Please type in a FileName in the Search Bar";
	private final String NONJAVAFILE = "Please type in a java source file name";
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
		table = new JTable(data,ColumnNames){
			private static final long serialVersionUID = 1L;

			//do not allow for cell editing
			//NOTE Source code for this method was found at
			//https://www.daniweb.com/programming/software-development/threads/279060/how-do-you-prevent-editing-of-jtable-cells
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table.setBounds(1, 1, 430, 0);
		contentPane.add(table, BorderLayout.CENTER);
		contentPane.add(table.getTableHeader(),BorderLayout.PAGE_START);
		this.setTitle("Import LOC");
		contentPane.setLayout(null);
		
		ImageIcon ic = new ImageIcon("lib/icons/MagGlass.png");
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(ic);
		lblNewLabel.setBounds(5, 27, 40, 43);
		contentPane.add(lblNewLabel);
		System.out.println(table.getValueAt(1,0));
		System.out.println(table.getValueAt(1,1));
		search_Txt = new JTextField();
		search_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String input = search_Txt.getText();
					boolean temp = checkInput(input);
					if(temp){
						
						boolean searchResult = searchTable(input);
						
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
		JScrollPane scroll = new JScrollPane(table);
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
		if(!input.endsWith(LOCReader.JAVAEXTENSION)){
			result = false;
			errorMsg = NONJAVAFILE;
			//display error message for non java file
		}
		return result;
		
	}
	private boolean searchTable(String s){
		
		
		int max_Row = table.getRowCount();
		int max_Col = table.getColumnCount();
		
		Hashtable<String,String> matches = new Hashtable(); 
		
		int row_Counter = 0;
		int col_Counter = 0;
		boolean isContainsChecked = contains_Chk.isSelected();
		boolean hasBeenFound = false;
		String currentFileName = "";
		String currentLOC = "";
		while(col_Counter <= max_Row && !hasBeenFound){
			//im thinking about searching just using a part of the file as akey too
			currentFileName = (String) table.getValueAt(col_Counter,row_Counter);
			currentLOC = (String) table.getValueAt(col_Counter,row_Counter + 1);
			if(currentFileName.equals(s)){
				hasBeenFound = true;
				//put filename and loc into tabe
				matches.put(currentFileName,currentLOC);
			}
			else if(currentFileName.contains(s) && isContainsChecked){
				
				matches.put(currentFileName,currentLOC);
			}
			
			++col_Counter;
		}

		return hasBeenFound;
		
		
	}
	private void displaySearchResults(String s){
		
		//use setValueAt to rewrite table
		
		//we could override whats displayed in table 
		//becuase all data is still stored in daat
		
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
