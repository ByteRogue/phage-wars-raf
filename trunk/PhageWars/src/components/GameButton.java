package components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;


public class GameButton extends JButton implements MouseListener {
	private Color outlineColor;
	private boolean isPressed = false;
	public GameButton(String title) {
		super(title);
		addMouseListener(this);
		outlineColor = Color.black;
		setForeground(Color.green);
		setOpaque(false);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintBorder(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		Dimension size = getSize();
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setRenderingHints(rh);
		g2.setColor(Color.black);
		GradientPaint gp;
		if (!isPressed)
			gp = new GradientPaint(0,0, new Color(0x222222), 0, size.height/2, new Color(0x555555), true);
		else
			gp = new GradientPaint(0,0, new Color(0x555555), 0, size.height/2, new Color(0x888888), true);
		g2.setPaint(gp);
		g2.fillRoundRect(0, 0, size.width-1, size.height-1, 15, 15);
		g2.setColor(outlineColor);
		g2.drawRoundRect(0, 0, size.width-1, size.height-1, 15, 15);
		float tx = size.width/2, ty=size.height/2;
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(getText(), g2);
		g2.setColor(getForeground());
		g2.drawString(getText(), tx-(float)r.getWidth()/2, ty+(float)r.getHeight()/3.4f);
		
		g2.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		outlineColor = Color.green;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		outlineColor = Color.black;
		isPressed = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed = false;
	}

}
