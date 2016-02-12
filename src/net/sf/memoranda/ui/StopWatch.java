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
	
	public StopWatch(String temp) {
		super(temp);
		setVisible(true);
		setResizable(true);
		closeOperationOnDefault(JFrame.EXIT_ON_CLOSE);
		setSize(300,150);
		
		setLayout(new GridLayout(2, 2, 5, 5));
		
		promptLabel = new JLabel("Enter seconds:", SwingConstants.CENTER);
		add(promptLabel);
		
		tf = new JTextField(5);
		add(tf);
		
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
