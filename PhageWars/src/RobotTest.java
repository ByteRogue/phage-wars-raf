import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class RobotTest {

    /**

     * @param args
     */
    public static void main(String[] args) {
        try {
            Application someWindow = new Application();
            
            Robot robot = new Robot();
            robot.delay(1000);
            robot.mouseMove(300, 30);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1000);
            robot.mouseMove(300, 200);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }

}
