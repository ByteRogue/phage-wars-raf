import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import engine.Sprite;
import engine.SpritePanel;


public class VirusViewPanel extends SpritePanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 400619668788232125L;
	private int strengthPoints = 70;
	private int vitalityPoints = 30;
	private int dexterityPoints = 50;
	private boolean editable = true;
	private VirusDefinition definition = null;
	
	public VirusDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(VirusDefinition definition) {
		this.definition = definition;
	}

	public VirusViewPanel(){
		setBackground(Color.black);
		setPreferredSize(new Dimension(240, 240));
		setMaximumSize(new Dimension(240,240));
		
		addMouseListener(this);
		
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getStrengthPoints() {
		return strengthPoints;
	}

	public void setStrengthPoints(int strengthPoints) {
		this.strengthPoints = strengthPoints;
	}

	public int getVitalityPoints() {
		return vitalityPoints;
	}

	public void setVitalityPoints(int vitalityPoints) {
		this.vitalityPoints = vitalityPoints;
	}

	public int getDexterityPoints() {
		return dexterityPoints;
	}

	public void setDexterityPoints(int dexterityPoints) {
		this.dexterityPoints = dexterityPoints;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.gray);
		g2.fillOval(10, 10, 220, 220);
		int cx = 120;
		int cy = 120;
		g2.setColor(Color.red);
		g2.fillArc(cx-strengthPoints, cy-strengthPoints, strengthPoints*2, strengthPoints*2, 0, 120);
		g2.setColor(Color.blue);
		g2.fillArc(cx-dexterityPoints, cy-dexterityPoints, dexterityPoints*2, dexterityPoints*2, 120, 120);
		g2.setColor(Color.green);
		g2.fillArc(cx-vitalityPoints, cy-vitalityPoints, vitalityPoints*2, vitalityPoints*2, 240, 120);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (editable){
			int dx = arg0.getX()-120;
			int dy = arg0.getY()-120;
			double angle = Math.atan2(dx,dy)*180/Math.PI;
			if (angle < 0)
				angle+=360;
			angle-=90;
			double radius = Math.sqrt(dx*dx+dy*dy);
			if (angle < 120 && angle > 0)
				strengthPoints = (int)radius;
			else if (angle < 240 && angle > 0)
				dexterityPoints = (int)radius;
			else
				vitalityPoints = (int)radius;
			
			if (definition != null){
				definition.setStrength(strengthPoints);
				definition.setVitality(vitalityPoints);
				definition.setDexterity(dexterityPoints);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
