import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**
 * This ai is very fast, and does not chooses its targets randomly. 
 * It calculates the best moves and does them.
 * @author Djordje Jovanovic
 *
 */
public class HardAiPlayer extends Player {
	/**
	 * Planet comparator that compares by the number.
	 * @author Djordje Jovanovic
	 *
	 */
	private class customComparator implements Comparator<Planet> {

		@Override
		public int compare(Planet o1, Planet o2) {
			if( o1.getNumber()==o2.getNumber()) return 0;
			
			if(o1.getNumber()>o2.getNumber()) return -1;
			return 1;
		}
	    
	}
	
	
	private final int DELAY = 40;
	private int counter;
	/**
	 * @param state Game state this ai participates in.
	 */
	public HardAiPlayer(GameState state) {
		super(state);
		setId(2);
		setColor(Color.red);
	}
	
	/**
	 * The greater the value, the better the move is.
	 * @param from Planet attack from
	 * @param target Target planet
	 * @return double value of how good this move is.
	 */
	public double planetAttackValue(Planet from, Planet target){
		double distScore=1000/Math.sqrt((from.getX()-target.getX())*(from.getX()-target.getX()) +(from.getY()-target.getY())  *(from.getY()-target.getY()));
		double ownerScore=target.getOwner()==0?1:1.5;
		
		return distScore*ownerScore;
		
	}
	
	@Override
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
		Collections.sort(ownedPlanets, new customComparator());
		
		for (Planet planet : ownedPlanets) {
			double max=-1;
			Planet bestTarget=null;
			for (Planet target : planets) {
				//if(planet.getNumber()>target.getNumber()+5){
					double targetValue=planetAttackValue(planet,target);
					if(targetValue>max)
					{
						max=targetValue;
						bestTarget=target;
					}
				//}
			}
			if(bestTarget!=null)
			{
				selectPlanet(planet);
				attackPlanet(bestTarget);
				bestTarget.setNumber(bestTarget.getNumber()-planet.getNumber()/2);
			}
		}
		
	}

}
