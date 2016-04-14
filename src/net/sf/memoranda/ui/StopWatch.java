package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StopWatch extends JFrame { 
	//field variables
	static JFrame mainframe;
	static JPanel north;
	static JPanel center;
	static JPanel south;
	static JLabel title;
	static JLabel hours;
	static JLabel minutes;
	static JLabel seconds;
	static JButton startStopButton;
	static JButton saveButton;
	static JButton reset;
	static boolean ongoing;
	static Timer timer;
	static public String name;
	int h;
	int m;
	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}
	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}
	/**
	 * @return the s
	 */
	public int getS() {
		return s;
	}

	int s;
	/**
	 * This is the default Constructor. Do Not Remove 
	 */
	public StopWatch() {
		ongoing = false;
		prepareGui("Task");
		ActionClick startStopButtonClick = new ActionClick(); //start/stop
		startStopButton.addActionListener(startStopButtonClick);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ActionClick resetClick = new ActionClick("reset");
		//startStopButton.addActionListener(resetClick);
	}
	/**
	 * This is a Constructor with String parameter. Do Not Remove 
	 */
	public StopWatch(String taskname) {
		name = taskname;
		ongoing = false;
		prepareGui(taskname);
		ActionClick startStopButtonClick = new ActionClick(); //start/stop
		startStopButton.addActionListener(startStopButtonClick);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ActionClick resetClick = new ActionClick("reset");
		//startStopButton.addActionListener(resetClick);
	}
	
	public static boolean isOngoing() { 
		return ongoing;
	}
	
	/**
	 * This method starts the gui for the time task
	 * @return void
	 */
	public static void prepareGui(String name) { 
		mainframe = new JFrame(name);
		north = new JPanel();
		north.setBackground((new Color(200,90,90)));
		center = new JPanel();
		south = new JPanel();
		mainframe.setSize(350, 275);
		//mainframe.setLayout();
		mainframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// grid 1
		title = new JLabel("", JLabel.CENTER);
		title.setSize(350, 100);
		title.setForeground(new Color(50,50,50));
		title.setText("This is Timer Tool");
		north.add(title);
		// grid 2
		hours = new JLabel("00");
		minutes = new JLabel("00");
		seconds = new JLabel("00");
		hours.setSize(100, 100);
		minutes.setSize(100, 100);
		seconds.setSize(100, 100);
		center.add(hours);
		center.add(minutes);
		center.add(seconds);
		// grid 3
		startStopButton = new JButton("Start"); ;
		saveButton = new JButton("save");
		south.add(startStopButton);
        south.add(saveButton);
        mainframe.getContentPane().add(BorderLayout.NORTH, north);
        mainframe.getContentPane().add(BorderLayout.CENTER, center);
        mainframe.getContentPane().add(BorderLayout.SOUTH, south);
		mainframe.setVisible(true);
	}

	/*
	 * public static void showEvent(){
	 * 
	 * }
	 */
	//misc - classes, impListeners
	public class ActionClick implements ActionListener {
		/**
		 * This is the method called when the action is performed
		 * @return void
		 */
		public void actionPerformed(ActionEvent actionEvent) {
			//start clicked - ongoing
			if (ongoing == false){
				timer = new Timer(1000, new TimeClass());
				timer.start();
				startStopButton.setText("Pause");
				ongoing = true;
			} else{
				timer.stop();
				startStopButton.setText("Play");
				ongoing = false;
			}
		}
		
	}

	public class TimeClass implements ActionListener {

		/**
		 * This is the method called when the action is performed
		 * @return void
		 */
		public void actionPerformed(ActionEvent actionEvent) {
			// this should only be clicked once.
			if (s < 59) {
				s++;
				String sec = (s < 10 ? "0" : "") + s;
				seconds.setText("" + sec);
			} else {
				s = 0;
				seconds.setText("" + s);
				if (m < 59) {
					m++;
					String min = (m < 10 ? "0" : "") + m;
					minutes.setText("" + min);
				} else {
					m = 0;

					h++;
					String hr = (h < 10 ? "0" : "") + h;
					hours.setText("" + hr);
					hours.setText("" + h);
				  }
			  }
		}
	}
}
