package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StopWatch extends JFrame{
	//field variables
	JPanel numbers = new JPanel();
	JPanel buttons = new JPanel();
	JPanel display = new JPanel();
//<<<<<<< HEAD
	
	JLabel timeDisplay = new JLabel("00:00:00");
	//JButton start;
	//JButton stop;
	//FlowLayout fl = new FlowLayout();
	
//=======
	JLabel jta = new JLabel("00:00:00");
//>>>>>>> a00a0a319d8b3f5aa4d54214e36f262925f9eed8
	JLabel colon = new JLabel(":");
	JLabel colon2 = new JLabel(":");
	
	JButton start;
	JButton stop;
	JButton reset;
//<<<<<<< HEAD
	/*
	EmptyBorder border = new EmptyBorder(10,0,0,0);
	EmptyBorder border2 = new EmptyBorder(0,0,10,0);
	final String [] zeroThroughNine = {"0","1","2","3","4","5","6","7","8","9"};
	final String [] zeroThroughFive = {"0","1","2","3","4","5"};
	JComboBox cb = new JComboBox(zeroThroughFive);
	JComboBox cb2 = new JComboBox(zeroThroughNine);
	JComboBox cb3 = new JComboBox(zeroThroughFive );
	JComboBox cb4 = new JComboBox(zeroThroughNine);
	JComboBox cb5 = new JComboBox(zeroThroughFive);
	JComboBox cb6 = new JComboBox(zeroThroughNine);
	*/
//=======
	
	FlowLayout fl = new FlowLayout();
	
	//EmptyBorder border = new EmptyBorder(10,0,0,0);
	//EmptyBorder border2 = new EmptyBorder(0,0,10,0);
	
	String [] minString = {"0","1","2","3","4","5","6","7","8","9"};
	
	JComboBox cb = new JComboBox(minString);
	JComboBox cb2 = new JComboBox(minString);
	JComboBox cb3 = new JComboBox(minString);
	JComboBox cb4 = new JComboBox(minString);
	JComboBox cb5 = new JComboBox(minString);
	JComboBox cb6 = new JComboBox(minString);
	
//>>>>>>> a00a0a319d8b3f5aa4d54214e36f262925f9eed8
	static int count;
	static Integer value;
	Timer timer;
	String temp;
	String stri = new String();
	
	//constructor
	public StopWatch() {
		gui();	
	}
	
	
	//setters
	//getters
	
	//methods
	public void gui(){
		//super("Window");
		closeOperationOnDefault(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3,1));
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
		
//<<<<<<< HEAD
/*
		numbers.setBorder(border);
		timeDisplay.setBorder(border);
		
		display.add(timeDisplay);
		add(display);
		add(numbers);
	    add(buttons);
	    */
//=======
		//numbers.setBorder(border);
		//jta.setBorder(border);
		
		display.add(jta);
		getContentPane().add(display);
		getContentPane().add(numbers);
		getContentPane().add(buttons);
//>>>>>>> a00a0a319d8b3f5aa4d54214e36f262925f9eed8
		
		StartEvent startEvent = new StartEvent();
		StopEvent stopEvent = new StopEvent();
		start.addActionListener(startEvent);
		stop.addActionListener(stopEvent);
		
		
		
	}

	private void closeOperationOnDefault(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
	
	//misc-OtherClasses
	public class StartEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			stri = (String) cb.getSelectedItem();
			count = Integer.parseInt(stri);
			timeDisplay.setText( Integer.toString(count));
			String stri = new String();
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
				timeDisplay.setText("Time left: " + counter);
				count--;
				
			}else{
				timer.stop();
				timeDisplay.setText("Done!");
			}
		}
	}
	public static void main(String [] args){
		StopWatch s = new StopWatch();
		s.gui();
	}
}
