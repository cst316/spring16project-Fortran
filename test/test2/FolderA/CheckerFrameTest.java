package checkers;

import java.awt.Component;

import javax.swing.JButton;

import junit.extensions.abbot.*;
import abbot.finder.Matcher;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.*;


public class CheckerFrameTest extends ComponentTestFixture {
	
	private CheckerFrame test;
	private JButton startGame;
	private JButton newGame;
	
	private JButtonTester button;
	
	protected void setUp() throws Exception{
		test = new CheckerFrame();
		startGame = (JButton) getFinder().find(new ClassMatcher(JButton.class));
		
		button = new JButtonTester();
	}
    
	public void testStartGame() throws Exception{
		button.actionClick(startGame);
	}
	
    public static void main(String[] args) {
        TestHelper.runTests(args, CheckerFrameTest.class);
    }
}

