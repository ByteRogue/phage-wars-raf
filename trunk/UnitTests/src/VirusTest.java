

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.BeforeClass;
import org.junit.Test;

public class VirusTest {
	

	@Test
	public void testGetTarget() {
		Virus tester=new Virus();
		Planet testplanet=new Planet();
		tester.setAngle(5);
		tester.setPosition(1, 2);
		tester.setTarget(testplanet);
		
		assertEquals("Result",testplanet,tester.getTarget());
	}

	@Test
	public void testGetPosition() {
		Virus tester=new Virus();
		Planet testplanet=new Planet();
		tester.setAngle(5);
		tester.setPosition(1, 2);
		tester.setTarget(testplanet);
		
		Point2D position = new Point2D.Double();
		position.setLocation(1,2);
		assertEquals("Result",position,tester.getPosition());
	}

	@Test
	public void testGetAngle() {
		Virus tester=new Virus();
		Planet testplanet=new Planet();
		tester.setAngle(5);
		tester.setPosition(1, 2);
		tester.setTarget(testplanet);
		
		assertEquals("Result",5,tester.getAngle(),0.0001);
	}

}
