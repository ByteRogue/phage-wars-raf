import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Normal dificulty ai, who is faster than easy, and does not make too stupid moves.
 * @author Djordje Jovanovic
 *
 */
public class NormalAiPlayer extends Player {
	private final int DELAY = 60;
	private int counter;
	/**
	 * @param state Game state this ai participates in.
	 */
	public NormalAiPlayer(GameState state) {
		super(state);
		// TODO Auto-generated constructor stub
		setId(2);
		setColor(Color.red);
	}
	/**
	 * Called each cycle
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
			{
				Planet target;
				int i=0;
				do{
					target=planets.get((int)(Math.random()*planets.size()));
					i++;
				}while(isStupidTarget(target) && i<planets.size());
				
				attackPlanet(target);
			}
		}
	}
	/**
	 * @param target
	 * @return If target is really bad target to be attacked from currently selected planets, returns true.
	 */
	private boolean isStupidTarget(Planet target) {
		int sum=0;
		for (Planet p : selectedPlanets) {
			sum+=p.getNumber();
		}
		if(sum<target.getNumber()) return true;
		return false;
	}
}
