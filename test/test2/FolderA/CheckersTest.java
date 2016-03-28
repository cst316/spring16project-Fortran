package checkers;

import static org.junit.Assert.*;

import org.junit.Test;

import example.CelsiusConverter;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import junit.extensions.abbot.*;
import abbot.finder.Matcher;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.*;

public class CheckersTest extends ComponentTestFixture{

	private Checkers test;
	private CheckerFrame frame;
	private JButton startGame;
	private JButton newGame;
	
	private JButtonTester button1;
	private JButtonTester button2;
	
	protected void setUp() throws Exception{
		frame = new CheckerFrame();
		startGame = (JButton) getFinder().find(new ClassMatcher(JButton.class));
		
		button1 = new JButtonTester();
		button1.actionClick(startGame);
		
		
		test = new Checkers();
		newGame = (JButton) getFinder().find(new Matcher(){
			public boolean matches(Component c) {
				return c instanceof JButton && ((JButton)c).getText().equals("New Game");
			}
		});
		
		button2 = new JButtonTester();
	}
    
	public void testNewGame() throws Exception{
		button2.actionClick(newGame);
		
	}
	
    public static void main(String[] args) {
        TestHelper.runTests(args, CheckersTest.class);
    }
}
