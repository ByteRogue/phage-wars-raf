import java.awt.geom.Point2D;


public class Virus {
	private Point2D position = new Point2D.Double();
	private double angle;
	private Planet target;
	

	public Planet getTarget() {
		return target;
	}
	public void setTarget(Planet target) {
		this.target = target;
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(double x, double y) {
		this.position.setLocation(x,y);
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
