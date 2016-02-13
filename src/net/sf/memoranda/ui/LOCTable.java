package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

public class LOCTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private final int COLUMN = 2;
	private final Object[] COLUMNNAMES	= {"Source File","LOC"};
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOCTable frame = new LOCTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public LOCTable(int row) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(row,COLUMN);
		contentPane.add(table, BorderLayout.CENTER);
		
		JLabel lblImportedLocTable = new JLabel("Imported LOC Table");
		contentPane.add(lblImportedLocTable, BorderLayout.NORTH);
	}
	public LOCTable(Object[][] data,Object[] ColumnNmaes){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(data,COLUMNNAMES);
		contentPane.add(table, BorderLayout.CENTER);
		
		JLabel lblImportedLocTable = new JLabel("Imported LOC Table");
		contentPane.add(lblImportedLocTable, BorderLayout.NORTH);
		
	}

}
