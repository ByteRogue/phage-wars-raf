import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This Ai sends random attacks from random planets. Easy Ai.
 * @author Stefan Jeremic
 *
 */
public class AiRandomPlayer extends Player {
	/**
	 * Number of skipped cycles. The more it is, the slower the ai is.
	 */
	private final int DELAY = 80;
	/**
	 * count of skipped cycles.
	 */
	private int counter;
	/**
	 * @param state The state this ai participates in.
	 */
	public AiRandomPlayer(GameState state) {
		super(state);
		// TODO Auto-generated constructor stub
		setId(2);
		setColor(Color.red);
	}
	/** 
	 * this is called in each cycle.
	 */
	@SuppressWarnings("unchecked")
	public void update(){
		super.update();
		counter++;
		if (counter < DELAY)
			return;
		counter = 0;
		ArrayList<Planet> planets = (ArrayList<Planet>) state.getPlanets().clone();
		ArrayList<Planet> ownedPlanets = new ArrayList<Planet>();
		
		Iterator<Planet> it = planets.iterator();
		while (it.hasNext()) {
			Planet p = it.next();
			if (p.getOwner() == id) {
				ownedPlanets.add(p);
				it.remove();
			}
		}
		if (ownedPlanets.size()>0){
			int selectCount = (int)(Math.random()*ownedPlanets.size());
			
			for (int i = 0; i <= selectCount; i++){
				selectPlanet(ownedPlanets.get((int)(Math.random()*ownedPlanets.size())));
			}
			if (planets.size() > 0)
				attackPlanet(planets.get((int)(Math.random()*planets.size())));
		}
	}
}
