package engine;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;


/**
 * @author Stefan Jeremic
 *
 */
public class Sprite {
	protected float x, y;
	private float ofsetX, ofsetY;
	private Dimension dimension;
	private Image img = null;
	private float rotation;
	
	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		AffineTransform t = new AffineTransform();
		t.translate(x, y);
		t.rotate(rotation);
		t.translate(ofsetX, ofsetY);
		g.drawImage(img, t, null);
	}
	/**
	 * Method to be called in each cycle.
	 */
	public void update(){
	}
	/**
	 * @return x coordinate
	 */
	public float getX() {
		return x;
	}
	/**
	 * Sets x coordinate.
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * @return y coordinate
	 */
	public float getY() {
		return y;
	}
	/**
	 * sets y coordinate.
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * @return dimension of sprite
	 */
	public Dimension getDimension() {
		return dimension;
	}
	/**
	 * Sets dimension of sprite.
	 * @param dimension
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	/**
	 * @return img
	 */
	public Image getImg() {
		return img;
	}
	/**
	 * Sets img.
	 * @param img
	 */
	public void setImg(Image img) {
		this.img = img;
	}
	/**
	 * @return rotation
	 */
	public float getRotation() {
		return rotation;
	}
	/**
	 * Sets rotation.
	 * @param rotation
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
