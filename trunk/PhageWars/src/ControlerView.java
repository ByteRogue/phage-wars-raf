import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import engine.Sprite;


/**
 * @author Stefan Jeremic
 *
 */
public class ControlerView extends Sprite{
	private GameState gameState;
	private ArrayList<Line2D> lines = new ArrayList<Line2D>();
	/**
	 * @param state Game state this belongs to.
	 */
	public ControlerView(GameState state){
		gameState = state;
	}
	
	/**
	 * @return lines
	 */
	public ArrayList<Line2D> getLines() {
		return lines;
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		Player p = gameState.getPlayer();
		ArrayList<Planet> planets = p.getSelectedPlanets();
		Iterator<Planet> it = planets.iterator();
		Color c = new Color(255,255,255,150);
		g.setColor(c);
		g.setStroke(new BasicStroke(3));
		
		while (it.hasNext()) {
			Planet planet = it.next();
			int r = (int)planet.getRadius()+5;
			int x = (int)planet.getX();
			int y = (int)planet.getY();
			
			g.drawOval(x-r, y-r, r*2, r*2);
		}
		
		Iterator<Line2D> lit = lines.iterator();
		
		while (lit.hasNext()) {
			Line2D l = lit.next();
			g.drawLine((int)l.getX1(), (int)l.getY1(), (int)l.getX2(), (int)l.getY2());
		}
	}
	
}
