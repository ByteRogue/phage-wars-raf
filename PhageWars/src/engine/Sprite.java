package engine;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;


public class Sprite {
	protected float x, y;
	private float ofsetX, ofsetY;
	private Dimension dimension;
	private Image img = null;
	private float rotation;
	
	public void paint(Graphics2D g) {
		AffineTransform t = new AffineTransform();
		t.translate(x, y);
		t.rotate(rotation);
		t.translate(ofsetX, ofsetY);
		g.drawImage(img, t, null);
	}
	public void update(){
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
