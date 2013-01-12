import java.awt.geom.Point2D;


/**
 * @author Stefan Jeremic
 *
 */
public class Virus {
	private Point2D position = new Point2D.Double();
	private double angle;
	private Planet target;
	

	/**
	 * @return target Planet
	 */
	public Planet getTarget() {
		return target;
	}
	/**
	 * Sets target planet.
	 * @param target Planet
	 */
	public void setTarget(Planet target) {
		this.target = target;
	}
	/**
	 * @return position
	 */
	public Point2D getPosition() {
		return position;
	}
	/**
	 * Sets Position.
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void setPosition(double x, double y) {
		this.position.setLocation(x,y);
	}
	/**
	 * @return angle
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * Sets angle.
	 * @param angle Angle to be set.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
