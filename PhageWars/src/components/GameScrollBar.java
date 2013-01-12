package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JScrollBar;

/**
 * @author Stefan Jeremic
 *
 */
public class GameScrollBar extends JScrollBar {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7653444710146813576L;
	/**
	 * Constructor, calls super(orientation).
	 * @param orientation
	 */
	public GameScrollBar(int orientation){
		super(orientation);
		setOpaque(false);
		setBackground(new Color(0xaaffaa));
		Component[] components = getComponents();
		setUI(new GameScrollBarUI());
		//System.out.println(components[1]);
		
	}
	

	/*@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Dimension size = getSize();
		System.out.println(g.getClipBounds());
		g.setClip(0, 0, size.width, size.height);
		super.paint(g);
	}*/


	@Override
	public void repaint(long tm, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		super.repaint(tm, 0, 0, getSize().width, getSize().height);
	}



	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		
		g2.setRenderingHints(rh);
		//Shape s = g2.getClip();
		Dimension size = getSize();
		g2.setClip(0, 0, size.width, size.height);
		g2.setColor(getBackground());
		g2.fillRect(0, 0, size.width, size.height);
		g2.setColor(new Color(0x222222));
		g2.drawRect(0, 0, size.width-1, size.height-1);
		int y = (int)((float)(size.height-32)/getMaximum()*getValue())+16;
		int h = (int)((float)(size.height-32)/getMaximum()*size.height);
		GradientPaint gp = new GradientPaint(0,0, new Color(0x222222), size.width/2, 0, new Color(0x555555), true);
		g2.setPaint(gp);
		g2.fillRoundRect(1, y, size.width-3, h, 8, 8);
		g2.setColor(Color.black);
		g2.drawRoundRect(1, y, size.width-3, h, 8, 8);
		
	}
	
}
