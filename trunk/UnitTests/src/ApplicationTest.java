import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

import engine.GameThread;
import engine.StateManager;


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
            if(!(StateManager.getInstance().getCurrentState() instanceof MainMenu))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(300, 220);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //virus selection
            if(!(StateManager.getInstance().getCurrentState() instanceof ConfigState))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(520, 450);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //the game
            if(!(StateManager.getInstance().getCurrentState() instanceof GameState))
            {
            	fail("Wrong state");
            }
            
            
            robot.mouseMove(100, 100);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            
            robot.mouseMove(310, 270);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(10000);
            
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.delay(1000);
            //main menu
            if(!(StateManager.getInstance().getCurrentState() instanceof MainMenu))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(300, 260);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //edit panel
            if(!(StateManager.getInstance().getCurrentState() instanceof EditorState))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(200, 250);//planet create
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseMove(250, 250);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            
            robot.mouseMove(200, 250);//planet move
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseMove(250, 250);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            
            robot.mouseMove(250, 250);//planet copy
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON2_MASK);
            robot.delay(1000);
            robot.mouseMove(250, 150);
            robot.delay(1000);
            robot.mouseRelease(InputEvent.BUTTON2_MASK);
            robot.delay(1000);
            
            
            robot.mouseMove(250, 150);//planet delete
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            robot.delay(1000);
            
            
            robot.keyPress(KeyEvent.VK_ESCAPE);//esc
            robot.delay(1000);
            //main menu
            if(!(StateManager.getInstance().getCurrentState() instanceof MainMenu))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(300, 300);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            //help
            if(!(StateManager.getInstance().getCurrentState() instanceof HelpState))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(300, 40);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            
            //main menu
            if(!(StateManager.getInstance().getCurrentState() instanceof MainMenu))
            {
            	fail("Wrong state");
            }
            
            robot.mouseMove(300, 330);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            
            

        } catch (AWTException e) {
            e.printStackTrace();
        }
	}

}
