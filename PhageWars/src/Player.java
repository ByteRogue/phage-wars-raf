import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


public class Player {
	protected int id;
	protected Color color;
	protected ArrayList<Planet> selectedPlanets = new ArrayList<Planet>();
	protected GameState state;
	public Player(GameState state) {
		this.state = state;
	}
	public void selectPlanet(Planet p) {
		if (id == p.getOwner()) {
			if (!selectedPlanets.contains(p))
				selectedPlanets.add(p);
			else
				selectedPlanets.remove(p);
		}
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ArrayList<Planet> getSelectedPlanets() {
		return selectedPlanets;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void update(){
		Iterator<Planet> it = selectedPlanets.iterator();
		while (it.hasNext()){
			Planet p =it.next();
			if (p.getOwner() != id)
				it.remove();
		}
	}
}
