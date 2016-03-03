package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StopWatch extends JFrame{
	
	JPanel numbers = new JPanel();
	JPanel buttons = new JPanel();
	JPanel display = new JPanel();
	JLabel jta = new JLabel("00:00:00");
	JButton start;
	JButton stop;
	FlowLayout fl = new FlowLayout();
	JLabel colon = new JLabel(":");
	JLabel colon2 = new JLabel(":");
	JButton reset;
	EmptyBorder border = new EmptyBorder(10,0,0,0);
	EmptyBorder border2 = new EmptyBorder(0,0,10,0);
	String [] minString = {"0","1","2","3","4","5","6","7","8","9"};
	JComboBox cb = new JComboBox(minString);
	JComboBox cb2 = new JComboBox(minString);
	JComboBox cb3 = new JComboBox(minString);
	JComboBox cb4 = new JComboBox(minString);
	JComboBox cb5 = new JComboBox(minString);
	JComboBox cb6 = new JComboBox(minString);
	static int count = 100;
	static Integer value;
	Timer timer;
	String temp;
	String stri = new String();
	
	
	public Gui ()
	{
		
		super("Window");
		closeOperationOnDefault(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		numbers.setLayout(fl);
		setVisible(true);
		setResizable(true);
		setSize(300,200);
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");
		
		
		
		
		numbers.add(cb);
		numbers.add(cb2);
		numbers.add(colon);
		
		
		numbers.add(cb3);
		numbers.add(cb4);
		numbers.add(colon2);
		
		
		numbers.add(cb5);
		numbers.add(cb6);
		
		
		buttons.setLayout(fl);
		buttons.add(start);
		buttons.add(stop);
		buttons.add(reset);
		
		numbers.setBorder(border);
		jta.setBorder(border);
		
		display.add(jta);
		add(display);
		add(numbers);
		add(buttons);
		
		StartEvent startEvent = new StartEvent();
		StopEvent stopEvent = new StopEvent();
		start.addActionListener(startEvent);
		stop.addActionListener(stopEvent);
		
		
		
	}
	private void closeOperationOnDefault(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
	
	
	public class StartEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			jta.setText( Integer.toString(count));
			String stri = new String();
			stri = (String) cb.getSelectedItem();
			count = Integer.getInteger(stri);
			TimeClass tc = new TimeClass(count);
			timer = new Timer(1000, tc);
			timer.start();
			
		}
	}
	
	public class StopEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			timer.stop();
		}
	}
	
	
	public class TimeClass implements ActionListener {
		int counter;
		
		public TimeClass(int counter){
			this.counter = counter;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			counter--;
			
			if(counter >= 1){
				jta.setText("Time left: " + counter);
				count--;
				
			}else{
				timer.stop();
				jta.setText("Done!");
			}
		}
	}
}
