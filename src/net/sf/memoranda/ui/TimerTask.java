package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import net.sf.memoranda.ui.StopWatch.TimeClass;

public class TimerTask extends JFrame{
	
	/**
	 * 
	 */
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
			
			
			timer = new Timer(1000, new TimeClass());
			timer.start();
		}
	}
	
	public class TimeClass implements ActionListener {
	
		
		public void actionPerformed(ActionEvent e) {
				if(s<59){
					s++;
					String sec= (s<10?"0":"")+s;
					seconds.setText(""+sec);
				}else{
					s=0;
					seconds.setText(""+s);
					if(m<59){
						m++;
						String min = (m<10?"0":"")+m;
						minutes.setText(""+min);
					}else{
						m = 0;
						
						h++;
						String hr = (h<10?"0":"")+h;
						hours.setText(""+hr);
						hours.setText(""+h);
					}
				}
		}
	}
}