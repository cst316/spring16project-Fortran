package net.sf.memoranda.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import net.sf.memoranda.ui.StopWatch.TimeClass;

public class TimerTask extends JFrame{
	
	/**
	 * 
	 */
	static JFrame mainframe;
	static JLabel title;
	static JPanel controlPanel;
	static Timer timer;
	static JButton startButton;
	static JLabel hours, minutes, seconds;
	int h=0,m=0,s=0;
	
	public TimerTask(String temp) {
		
		prepareGUI();
		title.setText("This is Timer Tool");
		hours = new JLabel("00");
		minutes = new JLabel("00");
		seconds = new JLabel("00");
		hours.setSize(100,100);
		minutes.setSize(100,100);
		seconds.setSize(100,100);
		controlPanel.add(hours);
		controlPanel.add(minutes);
		controlPanel.add(seconds);
		JButton startButton = new JButton("Start");
		event e = new event();
		startButton.addActionListener(e);
		
		mainframe.add(startButton);
		mainframe.setVisible(true);
		
	}
	public static void prepareGUI(){
		mainframe = new JFrame("Timer");
		mainframe.setSize(400,400);
		mainframe.setLayout(new GridLayout(3,1));
		title = new JLabel("",JLabel.CENTER);
		
		title.setSize(350, 100);
		mainframe.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		mainframe.add(title);
		mainframe.add(controlPanel);
		mainframe.setVisible(true);
	}
	public static void showEvent(){
		
	}
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