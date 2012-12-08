import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import core.Benchmark;
import engine.Sprite;
import engine.SpritePanel;
import engine.State;
import engine.StateManager;


public class GameState extends State implements KeyListener, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 503713326802063719L;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private Player player;
	private AiRandomPlayer aiPlayer;
	private ControlerView controlerView;
	private double mouseX, mouseY;
	public GameState(){
		SpritePanel sp = getSpritePanel();
		Sprite background = new Sprite();
		background.setImg(sp.loadImage("/resources/background.png").getImage());
		sp.getSprites().add(background);
		
		Planet planet = new Planet();
		planet.setOwner(1);
		planet.setNumber(59);
		planet.setX(100);
		planet.setY(100);
		planet.setRadius(35);
		planets.add(planet);
		planet = new Planet();
		planet.setX(320);
		planet.setY(240);
		planet.setRadius(20);
		planets.add(planet);
		
		
		planet = new Planet();
		planet.setOwner(2);
		planet.setNumber(59);
		planet.setX(450);
		planet.setY(400);
		planet.setRadius(30);
		planets.add(planet);
		
		sp.getSprites().addAll(planets);
		
		controlerView = new ControlerView(this);
		sp.getSprites().add(controlerView);
		
		//flock = new Flock(planets, 1);
		player = new Player(this);
		player.setId(1);
		player.setColor(Color.white);
		
		aiPlayer = new AiRandomPlayer(this);
		//sp.getSprites().add(flock);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void cycle() {
		// TODO Auto-generated method stub
		super.cycle();
		aiPlayer.update();
		player.update();
		controlerView.getLines().clear();
		ArrayList<Planet> planets = player.getSelectedPlanets();
		Iterator<Planet> it = planets.iterator();
		while (it.hasNext()){
			Planet p = it.next();
			Line2D l = new Line2D.Double();
			double px, py;
			px = p.getX();
			py = p.getY();
			double len = Math.sqrt((px-mouseX)*(px-mouseX)+(py-mouseY)*(py-mouseY))+0.0001;
			px += (mouseX-px)/len*(p.getRadius()+8);
			py += (mouseY-py)/len*(p.getRadius()+8);
			if (len > p.getRadius()){
				l.setLine(px, py, mouseX, mouseY);
				controlerView.getLines().add(l);
			}
		}
		
		
		double totalNumberOfCells=0;
		for (Planet planet : getPlanets()) {
			totalNumberOfCells+=planet.getNumber();
		}
		Benchmark.getInstance().addValue("Total number of cells", totalNumberOfCells);
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE)
			StateManager.changeState(new MainMenu());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Iterator<Planet> it = planets.iterator();
		boolean selected = false;
		while (it.hasNext()) {
			Planet p = it.next();
			if (Math.sqrt((p.getX()-e.getX())*(p.getX()-e.getX())+(p.getY()-e.getY())*(p.getY()-e.getY())) < p.getRadius()) {
				selected = true;
				if (p.getOwner() == player.getId())
					player.selectPlanet(p);
				else
					player.attackPlanet(p);
				break;
			}
		}
		if (!selected) {
			player.getSelectedPlanets().clear();
		}
		controlerView.getLines().clear();
		//flock.setTarget(new Point2D.Float(e.getX(), e.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mouseX = e.getX();
		mouseY = e.getY();
		
	}
}
