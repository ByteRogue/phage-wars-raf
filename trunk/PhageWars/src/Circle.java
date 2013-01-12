import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import engine.Sprite;


/**
 * @author Stefan Jeremic
 *
 */
public class Circle extends Sprite{
	private float radius;
	private Planet planet;
	private float velX, velY;
	/**
	 * @param planet The planet this circle belongs to.
	 */
	public Circle(Planet planet){
		this.planet = planet;
		velX = (float)(Math.random()-0.5)*5;
		velY = (float)(Math.random()-0.5)*5;
		radius =(float)Math.random()*planet.getRadius()/5+4;
		double a = Math.random()*Math.PI*2;
		float sin = (float)Math.sin(a);
		float cos = (float)Math.cos(a);
		float d = (float)Math.random()*planet.getRadius();
		x = planet.getX()+cos*d;
		y = planet.getY()+sin*d;
	}
	
	@Override
	public void paint(Graphics2D g) {
		Color c = planet.getColor();
		g.setColor(c);
		g.setStroke(new BasicStroke(1));
		g.drawOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
		c = new Color(c.getRed(),c.getGreen(),c.getBlue(),48);
		g.setColor(c);
		g.setStroke(new BasicStroke(4));
		g.drawOval((int)(x-radius), (int)(y-radius), (int)(radius*2), (int)(radius*2));
	}
	
	@Override
	public void update(){
		x+=velX;
		y+=velY;
		float pX = planet.getX();
		float pY = planet.getY();
		float area = planet.getRadius();
		
		float dx = x - pX;
		float dy = y - pY;
		float diff = (float)Math.sqrt(dx*dx+dy*dy)+radius-area;
		if (diff > 0) {
			float length = (float)Math.sqrt(dx*dx+dy*dy);
			dx/=length;
			dy/=length;
			dx*=diff;
			dy*=diff;
			x-=dx;
			y-=dy;
			velX=(float)(Math.random()-0.5)*5;
			velY=(float)(Math.random()-0.5)*5;
		}
	}

}
