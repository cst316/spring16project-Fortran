package checkers;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class CheckerFrame extends JFrame implements ActionListener{
    static JButton stB=new JButton("Start Game");
    JPanel gmP=new StartPanel();
  
    public CheckerFrame(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this); //changing the appearence of the frame
        }
        catch (Exception e) {
           //no need to handle exception as it only affect the appearence
        }
        setupGUI();
        new PlaySound("src//sounds//Start.wav").start();
    }

    private void setupGUI() {
        setLayout(null);
        gmP.setBounds(0,0,508,401);//400,401
        //gmP.imageUpdate(ne, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH)
        add(gmP);
        stB.setHorizontalAlignment(SwingConstants.LEADING);
        stB.setIcon(new ImageIcon(getClass().getResource("/images/checkersIcon.jpg")));
        stB.setBackground(Color.LIGHT_GRAY);
        stB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stB.setBounds(130,415,255,70);
        stB.setFont(new Font("Times new roman",Font.BOLD,20));
        stB.addActionListener(this);
        stB.setFocusPainted(false);
        add(stB);

        this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpg")).getImage());

        setSize(508,520);
        setLocation((int)getToolkit().getScreenSize().getWidth()/2-254,(int)getToolkit().getScreenSize().getHeight()/2-310);
        setResizable(false);
        setVisible(true);
        setTitle("Play Checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Start Game")){
            ((JButton)e.getSource()).setText("New Game");
            new PlaySound("src//sounds//button.wav").start();
            gmP=new Checkers();
            gmP.setBounds(0,0,508,401);
            this.setContentPane(gmP);
        }
    }
}
