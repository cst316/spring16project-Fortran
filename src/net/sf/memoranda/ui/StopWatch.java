package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StopWatch extends JFrame{
	
	/**
	 * 
	 */
	JLabel promptLabel, timerLabel;
	int counter;
	JTextField tf;
	JButton button;
	Timer timer;
	
	public StopWatch() {
		setLayout(new GridLayout(2, 2, 5, 5));
		
		promptLabel = new JLabel("Enter seconds:", SwingConstants.CENTER);
		add(promptLabel);
		
		tf = new JTextField(5);
		add(tf);
		
		timerLabel = new JLabel("Waiting...", SwingConstants.CENTER);
		add(timerLabel);
		
		event e = new event();
		button.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int count = (int)(Double.parseDouble(tf.getText()));
			timerLabel.setText("Time left: " + count);
			
			TimeClass tc = new TimeClass(count);
			timer = new Timer(1000, tc);
			timer.start();
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
				timerLabel.setText("Time left: " + counter);
				
			}else{
				timer.stop();
				timerLabel.setText("Done!");
			}
		}
	}
}
