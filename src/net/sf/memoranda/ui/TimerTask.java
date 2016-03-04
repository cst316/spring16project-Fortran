package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import net.sf.memoranda.ui.StopWatch.TimeClass;
/*
public class TimerTask extends JFrame{
	
	/**
	 * 
	 *
	JLabel promptLabel, timerLabel;
	int counter;
	JLabel hours;
	JLabel minutes;
	JLabel seconds;
	JButton button;
	Timer timer;
	
	public TimerTask(String temp) {
		super(temp);
		setVisible(true);
		setResizable(true);
		closeOperationOnDefault(JFrame.EXIT_ON_CLOSE);
		setSize(300,150);
		
		setLayout(new GridLayout(2, 2, 5, 5));
		
		
		
		hours = new JLabel("00");
		minutes = new JLabel("00");
		seconds = new JLabel("00");
		
		add(hours);
		add(minutes);
		add(seconds);
		
		button = new JButton("Start timing");
		add(button);
		
		timerLabel = new JLabel("Waiting...", SwingConstants.CENTER);
		add(timerLabel);

		event e = new event();
		button.addActionListener(e);
	}
	
	private void closeOperationOnDefault(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
	int h=0,m=0,s=0;
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
*/
public class TimerTask extends JFrame {
	//field variables
	static JFrame mainframe;
	static JLabel title;
	static JPanel controlPanel;
	static JLabel hours, minutes, seconds;
	static JButton startStopButton;
	static JButton reset;
	static boolean ongoing;
	static Timer timer;
	int h, m, s;

	//constructor
	public TimerTask() {
		ongoing = false;
		prepareGUI();
		ActionClick startStopButtonClick = new ActionClick(); //start/stop
		startStopButton.addActionListener(startStopButtonClick);
		//ActionClick resetClick = new ActionClick("reset");
		//startStopButton.addActionListener(resetClick);
		
	}
	//setters & getters
	public static boolean isOngoing() {
		return ongoing;
	}
	//methods
	public static void prepareGUI() {
		mainframe = new JFrame("Timer");
		mainframe.setSize(400, 400);
		GridLayout gridLayout = new GridLayout(3, 1);
		mainframe.setLayout(gridLayout);
		//mainframe.setLayout();
		mainframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		// grid 1
		title = new JLabel("", JLabel.CENTER);
		title.setSize(350, 100);
		title.setText("This is Timer Tool");
		// grid 2
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		hours = new JLabel("00");
		minutes = new JLabel("00");
		seconds = new JLabel("00");
		hours.setSize(100, 100);
		minutes.setSize(100, 100);
		seconds.setSize(100, 100);
		// grid 3
		startStopButton = new JButton("Start");

		controlPanel.add(hours);
		controlPanel.add(minutes);
		controlPanel.add(seconds);

		mainframe.add(title);
		mainframe.add(controlPanel);
		mainframe.add(startStopButton);

		mainframe.setVisible(true);
	}

	/*
	 * public static void showEvent(){
	 * 
	 * }
	 */
	//misc - classes, impListeners
	public class ActionClick implements ActionListener {
/*		public ActionClick(int action){
>>>>>>> a00a0a319d8b3f5aa4d54214e36f262925f9eed8
			
		}
		//use switch
		switch (action) {
            case 1{  
            	monthString = "January";
            }
            case 2{  
            	monthString = "January";
            }
            default: monthString = "Invalid month";
                     break;
        }
	*/
		public void actionPerformed(ActionEvent actionEvent) {
			//start clicked - ongoing
			if (ongoing == false){
				timer = new Timer(1000, new TimeClass());
				timer.start();
				startStopButton.setText("Pause");
				ongoing = true;
			}
			//stop click - not ongoing
			else{
				timer.stop();
				startStopButton.setText("Play");
				ongoing = false;
			}

		}
		
	}

	public class TimeClass implements ActionListener {
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


	public static void main(String[] args) {
		TimerTask s = new TimerTask();

	}

}
