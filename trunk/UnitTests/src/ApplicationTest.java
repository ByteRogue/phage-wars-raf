import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;


public class ApplicationTest {

	@Test
	public void testMain() {
		Application.main(null);
		assertNotNull("App null", Application.getApp());
		
		Application tester=Application.getApp();
		
		assertNotNull("Game thread null",tester.getGameThread());
		
		
		
		try {
            
            Robot robot = new Robot();
            robot.delay(1000);
            //main menu
            robot.mouseMove(300, 220);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //virus selection
            robot.mouseMove(520, 450);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //the game
            
            robot.mouseMove(100, 100);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(10000);
            
            
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.delay(1000);
            //main menu
            robot.mouseMove(300, 260);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //edit panel
            robot.mouseMove(200, 250);//planet create
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseMove(300, 250);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ESCAPE);//esc
            robot.delay(1000);
            //main menu
            robot.mouseMove(300, 300);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);

        } catch (AWTException e) {
            e.printStackTrace();
        }
		
		
		tester.dispose();
	}

}
