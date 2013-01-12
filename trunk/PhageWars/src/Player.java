import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Stefan Jeremic
 *
 */
public class Player {
	protected int id;
	protected Color color;
	protected ArrayList<Planet> selectedPlanets = new ArrayList<Planet>();
	protected GameState state;
	/**
	 * @param state Game state this player participates in.
	 */
	public Player(GameState state) {
		this.state = state;
	}
	/**
	 * @param p Selects this planet.
	 */
	public void selectPlanet(Planet p) {
		if (id == p.getOwner()) {
			if (!selectedPlanets.contains(p))
				selectedPlanets.add(p);
			else
				selectedPlanets.remove(p);
		}
	}
	
	/**
	 * @return Players color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Sets players color.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return Planets selected by player.
	 */
	public ArrayList<Planet> getSelectedPlanets() {
		return selectedPlanets;
	}
	/**
	 * Sends the attack to Planet p from selected planets
	 * @param p
	 */
	public void attackPlanet(Planet p) {
		Iterator<Planet> it = selectedPlanets.iterator();
		Flock flock = new Flock(state.getPlanets(), id);
		flock.setColor(color);
		while (it.hasNext()){
			Planet o = it.next();
			int n = o.getNumber()/2;
			o.setNumber(o.getNumber()-n);
			flock.createGenerator(o, p, n);
		}
		state.getSprites().add(flock);
		selectedPlanets.clear();
	}
	/**
	 * @return players id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets players id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Called in each cycle.
	 */
	public void update(){
		Iterator<Planet> it = selectedPlanets.iterator();
		while (it.hasNext()){
			Planet p =it.next();
			if (p.getOwner() != id)
				it.remove();
		}
	}
}
