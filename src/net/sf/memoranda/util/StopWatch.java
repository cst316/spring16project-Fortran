package net.sf.memoranda.util;

import javax.swing.JFrame;

public class StopWatch extends JFrame {

	//JFrame frame = new JFrame();
	
	public StopWatch(){
		super("Timer");
		this.setSize(400,400);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
