package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


public class LOCTable extends JFrame  {

	private JPanel contentPane;
	private JTable table;

	/*
	 * Constructor for LOCTable takes in a 2D array of data and array columnNames
	 */
	public LOCTable(Object[][] data,Object[] ColumnNames)
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		table = new JTable(data,ColumnNames);
		contentPane.add(table, BorderLayout.CENTER);
		contentPane.add(table.getTableHeader(),BorderLayout.PAGE_START);
		this.setTitle("Import LOC");
		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
