package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;




public class StopWatch  extends JFrame {
	JPanel numbers = new JPanel();
	JPanel buttons = new JPanel();
	JPanel display = new JPanel();
	JLabel timeDisplay = new JLabel("00 : 00 : 00");
	JButton start;
	JButton stop;
	JButton reset;
	JButton contin;
	FlowLayout fl = new FlowLayout();
	JLabel colon = new JLabel(":");
	JLabel colon2 = new JLabel(":");
	EmptyBorder border = new EmptyBorder(10, 0, 0, 0);
	EmptyBorder border2 = new EmptyBorder(0, 0, 10, 0);
	final String[] zeroThroughNine = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	final String[] zeroThroughFive = { "0", "1", "2", "3", "4", "5" };
	JComboBox cb = new JComboBox(zeroThroughFive);
	JComboBox cb2 = new JComboBox(zeroThroughNine);
	JComboBox cb3 = new JComboBox(zeroThroughFive);
	JComboBox cb4 = new JComboBox(zeroThroughNine);
	JComboBox cb5 = new JComboBox(zeroThroughFive);
	JComboBox cb6 = new JComboBox(zeroThroughNine);
	static Integer value;
	static int hour, min, sec;
	Timer timer;
	String temp;

	
	public StopWatch() {

		super("Window");
		closeOperationOnDefault(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		numbers.setLayout(fl);
		setVisible(true);
		setResizable(true);
		setSize(320, 200);
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");
		contin = new JButton("Continue");
		

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
		buttons.add(contin);

		numbers.setBorder(border);
		timeDisplay.setBorder(border);

		display.add(timeDisplay);
		add(display);
		add(numbers);
		add(buttons);

		StartEvent startEvent = new StartEvent();
		StopEvent stopEvent = new StopEvent();
		ResetEvent resetEvent = new ResetEvent();
		ContinEvent continEvent = new ContinEvent();
		
		start.addActionListener(startEvent);
		stop.addActionListener(stopEvent);
		reset.addActionListener(resetEvent);
		contin.addActionListener(continEvent);
		contin.setEnabled(false);

	}

	private void closeOperationOnDefault(int exitOnClose) {
		// TODO Auto-generated method stub

	}
	
	public class StartEvent implements ActionListener {
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			start.setEnabled(false);
			int h1 = Integer.parseInt(cb.getSelectedItem().toString());
			int h2 = Integer.parseInt(cb2.getSelectedItem().toString());
			int m1 = Integer.parseInt(cb3.getSelectedItem().toString());
			int m2 = Integer.parseInt(cb4.getSelectedItem().toString());
			int s1 = Integer.parseInt(cb5.getSelectedItem().toString());
			int s2 = Integer.parseInt(cb6.getSelectedItem().toString());
			hour = initialNumbers(h1, h2);
			min = initialNumbers(m1, m2);
			sec = initialNumbers(s1, s2);
			timeDisplay.setForeground(Color.black);
			int total = hour+min+sec;
			if(total == 0)
			{
				timeDisplay.setText("Please enter a valid time");
				start.setEnabled(true);
			}
			else{
			TimeClass tc = new TimeClass();
			timer = new Timer(1000, tc);
			timer.start();}

		}

		/**
		 * 
		 * @param number1
		 * 			first digit from the combo box
		 * @param number2
		 * 			second digit from the combo box
		 * @return
		 * 			the full number which correlates to a time in minutes seconds or hours
		 */
		public int initialNumbers(int number1, int number2) {
			number1 *= 10;

			return number1 + number2;
		}
	}

	
	public class StopEvent implements ActionListener  {
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			try {
			timer.stop();
			start.setEnabled(false);
			contin.setEnabled(true);
			}
			catch (NullPointerException e1 )
			{
				timeDisplay.setText("Please enter a valid time");
			}
		}
	}
	
	public class ResetEvent implements ActionListener {
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			try{
			timeDisplay.setText("00 : 00 : 00");
			timeDisplay.setForeground(Color.black);
			hour = 0;
			min = 0;
			sec = 0;
			timer.stop();
			start.setEnabled(true);
			contin.setEnabled(false);
			}
			catch(NullPointerException e1 ){
				timeDisplay.setText("Please enter a valid time");
			}
		}
	}
	public class ContinEvent implements ActionListener {
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			TimeClass tc = new TimeClass();
			timer = new Timer(1000, tc);
			timer.start();
			contin.setEnabled(false);
		}
	}
	

	public class TimeClass implements ActionListener {
		
		

		public TimeClass() {
		}
		

		/**
		 * This is the heart of the program that counts down and sets the time based on the 
		 * input from the combo boxes
		 */
		public void actionPerformed(ActionEvent e) {

			timeDisplay.setText(logic());
			if(sec == 0 && hour ==0 && min ==0)
				timeDisplay.setForeground(Color.red);

			
		}

		public String logic (){
			if (hour > 0) {
				if (min > 0) {
					if (sec > 0)
						sec--;
					else {
						min--;
						sec = 59;
					}
				} else if (min == 0) {
					if (sec > 0)
						sec--;
					else {
						hour--;
						min = 59;
						sec = 59;
					}
				}
				String formatHour = String.format("%02d", hour);
				String formatMin = String.format("%02d", min);
				String formatSec = String.format("%02d", sec);
				return (formatHour +" : "+ formatMin +" : "   + formatSec);
			} else if (min > 0) {
				if (sec > 0) {
					sec--;

				} else {
					min--;
					sec = 59;
				}
				String formatHour = String.format("%02d", hour);
				String formatMin = String.format("%02d", min);
				String formatSec = String.format("%02d", sec);
				return (formatHour+" : "+formatMin+" : "+formatSec);
			} else if (sec > 0) {
				sec--;
				String formatHour = String.format("%02d", hour);
				String formatMin = String.format("%02d", min);
				String formatSec = String.format("%02d", sec);
				return (formatHour+" : "+formatMin+" : "+formatSec);
			} 

			else {
				timer.stop();
				return "Done";	
			}
			
		}
	}

}

