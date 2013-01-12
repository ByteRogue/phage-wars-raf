import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import engine.Sprite;


/**
 * @author Stefan Jeremic
 *
 */
public class Planet extends Sprite {
	private final int NUMBER_INCREMENT_STEP = 24;
	private int step=0;
	
	private int owner = 0;
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private int radius;
	private Color color = Color.blue;
	private int number;
	
	/**
	 * paints to g.
	 */
	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(1));
		g.setColor(color);
		g.drawOval((int)(x-radius), (int)(y-radius), radius*2, radius*2);
		g.setStroke(new BasicStroke(4));
		Color c = new Color(color.getRed(),color.getGreen(),color.getBlue(),48);
		g.setColor(c);
		g.drawOval((int)(x-radius), (int)(y-radius), radius*2, radius*2);
		for (int i = 0; i < circles.size(); i++){
			circles.get(i).paint(g);
		}
		g.setColor(color);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(Integer.toString(number), g);
		g.drawString(Integer.toString(number), x-(int)r.getWidth()/2, y+(int)r.getHeight()/2);
		
	}
	/**
	 * update
	 */
	@Override
	public void update(){
		if(owner!=0){
			step++;
			if(step>NUMBER_INCREMENT_STEP){
				step=0;
				number++;
			}
		}
		for (int i = 0; i < circles.size(); i++){
			circles.get(i).update();
		}
	}
	/**
	 * @return radius of the planet.
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * Sets radius.
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
		circles.clear();
		for (int i = 0; i < (int)radius/8; i++){
			circles.add(new Circle(this));
		}
	}
	/**
	 * @return Color of the planet.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Sets color of the planet.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Sets the number.
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return id of the player owing this planet. 0 if no owner.
	 */
	public int getOwner() {
		return owner;
	}
	/**
	 * Sets the id of the owner of the planet.
	 * @param owner
	 */
	public void setOwner(int owner) {
		
		this.owner = owner;
		switch (owner){
		case 1:
			setColor(Color.white);
			break;
		case 2:
			setColor(Color.red);
			break;
		}
	}
	
	
}
