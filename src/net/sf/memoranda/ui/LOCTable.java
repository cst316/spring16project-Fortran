package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class LOCTable extends JFrame  {

	private JPanel contentPane;
	private JTable table;
	private JTextField search_Txt;
	private JLabel lblNewLabel;

	/*
	 * Constructor for LOCTable takes in a 2D array of data and array columnNames
	 */
	public LOCTable(Object[][] data,Object[] ColumnNames)
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table = new JTable(data,ColumnNames);
		table.setBounds(1, 1, 430, 0);
		contentPane.add(table, BorderLayout.CENTER);
		contentPane.add(table.getTableHeader(),BorderLayout.PAGE_START);
		this.setTitle("Import LOC");
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(10, 43, 46, 14);
		contentPane.add(lblNewLabel);
		
		search_Txt = new JTextField();
		search_Txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String input = search_Txt.getText();
					//boolean temp = searchTable()
				}
				
			}
		});
		search_Txt.setToolTipText("Enter A File Name to be Searched");
		search_Txt.setBounds(46, 40, 108, 20);
		contentPane.add(search_Txt);
		search_Txt.setColumns(10);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(5, 74, 432, 205);
		contentPane.add(scroll);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private boolean checkInput(String input){
		
		
		return true;
		
	}
	private boolean searchTable(String s){
		
		boolean result = false;
		
		
		
		
		
		
		
		
		return result;
		
		
	}
	

	
}
