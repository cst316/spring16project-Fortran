package checkers;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import junit.extensions.abbot.*;
import abbot.finder.Matcher;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.*;

public class AbbotTesting extends ComponentTestFixture{

	private Checkers checker;
	private CheckerFrame frame;
	
	private JButton startGame;
	private JButton newGame;
	private JButton sound;
	private JRadioButton player1;
	private JRadioButton player2;
	
	private JButtonTester mouse;

	protected void setUp() throws Exception {
		frame = new CheckerFrame();
		startGame = (JButton) getFinder().find(new ClassMatcher(JButton.class));
		
		mouse = new JButtonTester();
		mouse.actionClick(startGame);
		
		checker = new Checkers();
	/*	sound = (JButton) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JButton && ((JButton)c).equals.();
			}
		});   */
		
		player2 = (JRadioButton) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JRadioButton && ((JRadioButton)c).getText().equals("2-Player");
			}
		});
		
		player1 = (JRadioButton) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JRadioButton && ((JRadioButton)c).getText().equals("1-Player");
			}
		});
		
		newGame = (JButton) getFinder().find(new Matcher() {
			public boolean matches(Component c) {
				return c instanceof JButton && ((JButton)c).getText().equals("New Game");
			}
		});
		
		mouse = new JButtonTester();
	}
	
	public void testNewGame() throws Exception {
		mouse.actionClick(player2);
		mouse.actionClick(player1);
		mouse.actionClick(newGame);
	}
	
    public static void main(String[] args) {
        TestHelper.runTests(args, AbbotTesting.class);
        
    }
}