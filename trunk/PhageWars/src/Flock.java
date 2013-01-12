import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import engine.Sprite;


public class Flock extends Sprite {
	private class FlockGenerator {
		private Planet target;
		private Planet start;
		private Flock flock;
		private int number;
		private int counter;
		private final int DELAY = 2; 
		
		/**
		 * generator
		 * @param flock
		 * @param start
		 * @param target
		 * @param number
		 */
		public FlockGenerator(Flock flock, Planet start, Planet target, int number){
			this.flock = flock;
			this.start = start;
			this.target = target;
			this.number = number;
		}
		
		/**
		 * update
		 */
		public void update(){
			counter++;
			if (counter>DELAY) {
				flock.addElement(start, target);
				number--;
				counter = 0;
			}
		}
		public int getNumber(){
			return number;
		}
	}
	private Color color;
	/**
	 * virusi
	 */
	private ArrayList<Virus> elements = new ArrayList<Virus>();
	
	private ArrayList<FlockGenerator>generators = new ArrayList<Flock.FlockGenerator>();
	//private ArrayList<Float> angles = new ArrayList<Float>();
	//private ArrayList<Planet> targets = new ArrayList<Planet>();
	private int id;

	private double separationDistance = 70;
	private double detectionDistance = 80;

	private float theta = 20;
	private float speed = 4;
	private ArrayList<Planet> planets;
	//private ArrayList<Point2D> aims = new ArrayList<Point2D>();
	
	/**
	 * Flock
	 * @param planets
	 * @param id
	 */
	public Flock(ArrayList<Planet> planets,int id){
		color = new Color(255,255,255,255);
		this.id = id;
		this.planets = planets;
		/*for (int i = 0; i < 40; i++){
			elements.add(new Point2D.Float((float)Math.random()*640,(float)Math.random()* 320));
			angles.add(i, 0.0f);
		}*/
	}
	
	/**
	 * Kreira generator
	 * @param start
	 * @param target
	 * @param number
	 */
	public void createGenerator(Planet start, Planet target, int number) {
		generators.add(new FlockGenerator(this, start, target, number));
	}
	
	/**
	 * 
	 * @param start
	 * @param target
	 */
	public void addElement(Planet start, Planet target){
		Virus v = new Virus();
		Point2D s = new Point2D.Double(target.getX()-start.getX(), target.getY()-start.getY());
		s = normalize(s, start.getRadius());
		double len = (Math.random()-0.5)*start.getRadius()*2;
		s.setLocation(s.getX()-s.getY()/start.getRadius()*len,s.getY()+s.getX()/start.getRadius()*len);
		v.setPosition(s.getX()+start.getX(), s.getY()+start.getY());
		elements.add(v);
		//angles.add(0.0f);
		v.setTarget(target);
	}
	
	/**
	 * 
	 * @param point
	 * @param n
	 * @return
	 */
	public Point2D normalize(Point2D point, double n){
		
		double l = Math.sqrt(point.getX()*point.getX()+point.getY()*point.getY());
		if (l == 0){
			return point;
		}
		return new Point2D.Double((point.getX()*n/l), (point.getY()*n/l));
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		
		Iterator<FlockGenerator> fit = generators.iterator();
		while (fit.hasNext()){
			FlockGenerator fg = fit.next();
			fg.update();
			if (fg.getNumber() <= 0)
				fit.remove();
		}
		
		//aims.clear();
		//ArrayList<Integer> indicesToRemove = new ArrayList<Integer>();
		Iterator<Virus> it = elements.iterator();
		int i = -1;
		//for (int i = 0; i < elements.size(); i++) {
		while (it.hasNext()){
			i++;
			//Point2D p1 = elements.get(i);
			Virus v = it.next();
			Point2D p1 = v.getPosition();
			double angle1 = v.getAngle();
			//Point2D heading = new Point2D.Float((float)Math.cos(angle1), (float)Math.sin(angle1));
			Point2D aim = new Point2D.Double(0,0);//new Point2D.Float(0, 0);
			int numElements = 0;
			
			
			for (int j = 0; j < elements.size(); j++) {
				Point2D p2 = (Point2D) elements.get(j).getPosition();
				if (i != j) {
					double d = getDistance(p1, p2);
					double dx = (p2.getX()-p1.getX());
					double dy =(p2.getY()-p1.getY());
					if (d < detectionDistance) {
						numElements++;
						double weight = 200;
						//double w2 = 100;
						double angle2 = elements.get(j).getAngle();
						if (d < separationDistance) {
							weight *= Math.pow(1 -  d / separationDistance, 2);
							//w2 = 500;
//							dx = (float)(p2.getX()-p1.getX())*(separationDistance-d)*(separationDistance-d);
//							dy = (float)(p2.getY()-p1.getY())*(separationDistance-d)*(separationDistance-d);
//							p2.setLocation(p1.getX()-dx, p1.getY()-dy);
						}
						else {
							
							weight *=  -Math.pow(( d - separationDistance ) / ( detectionDistance - separationDistance ), 2);
//							p2.setLocation(p1.getX()+Math.cos(angle2)*100, p1.getY()+Math.sin(angle2)*100);
						}
						Point2D align = new Point2D.Double(Math.cos(angle2)*100, Math.sin(angle2)*100);
	                    Point2D attract = new Point2D.Double(-dx,-dy);
	                    attract = normalize(attract, weight); // weight is variable
	                    Point2D dist = new Point2D.Double(align.getX()+attract.getX(), align.getY()+attract.getY());
	                    dist = normalize(dist, 100); // final weight is 100
	
						aim.setLocation(aim.getX()+dist.getX(), aim.getY()+dist.getY());
					}
					
				}
			}
			int index = -1;
			for (int j = 0; j < planets.size(); j++) {
				Planet p = planets.get(j);
				//if (p != targets.get(i)){
				if (v.getTarget() != p){
					double d = getDistance(p1, new Point2D.Float(p.getX(), p.getY()));
	
					if (d < (separationDistance + p.getRadius())){// && heading.getX()*(p.getX()-p1.getX())+heading.getY()*(p.getY()-p1.getY()) > 0) {
						double weight =p.getRadius()*p.getRadius()*Math.pow(1 -  (d ) / (separationDistance+p.getRadius()), 2); 
						double dx = (p1.getX()-p.getX())/d*5*weight;
						double dy = (p1.getY()-p.getY())/d*5*weight;
						aim.setLocation(aim.getX()+dx, aim.getY()+dy);
	//					aim.setLocation(aim.getX()-dx+p.getX(), aim.getY()-dy+p.getY());
						numElements++;
					}
				} else {
					double d = getDistance(p1, new Point2D.Float(p.getX(), p.getY()));
					if (d < p.getRadius()) {
						index = i;
					}
				}
			}
			
			
			Planet target = v.getTarget();
			Point2D targetDist = new Point2D.Double(target.getX()-p1.getX(), target.getY()-p1.getY());
			targetDist = normalize(targetDist, 1000);
			aim.setLocation(aim.getX()+targetDist.getX(), aim.getY()+targetDist.getY());
			numElements++;

			aim.setLocation(aim.getX()/numElements, aim.getY()/numElements);
//			aims.add(aim);
			
			int angle = ((int)(angle1*180/Math.PI) + 360) % 360;
			int dirAngle = ((int)(Math.atan2(aim.getY(), aim.getX())*180/Math.PI) + 360) % 360;
			if (Math.abs(angle-dirAngle) < theta)
				angle = dirAngle;
			else if (dirAngle - angle < 0 || dirAngle - angle > 180) {
				angle-=theta;
			}
			else
				angle+=theta;
			
			angle = (angle+360) % 360;
			float a = (float)(angle*Math.PI/180);
			v.setAngle(a);
			//angles.set(i, a);
			if (index != -1){
				it.remove();
				Planet t = v.getTarget();
				if (t.getOwner() == id) 
					t.setNumber(t.getNumber()+1);
				else {
					t.setNumber(t.getNumber()-1);
					if (t.getNumber() < 0)
						t.setOwner(id);
				}

			}
//			aims.remove(index);
			//p1.setLocation(p1.getX()+Math.cos(angle1)*speed, p1.getY()+Math.sin(angle1)*speed);
			
		}

		/*for (int i = 0; i < indicesToRemove.size(); i++){
			int index = indicesToRemove.get(i);
			elements.remove(index);
			angles.remove(index);
			Planet t = targets.get(index);
			if (t.getOwner() == id) 
				t.setNumber(t.getNumber()+1);
			else {
				t.setNumber(t.getNumber()-1);
				if (t.getNumber() < 0)
					t.setOwner(id);
			}
			targets.remove(index);
//			aims.remove(index);
		}*/
		for (i = 0; i < elements.size(); i++) {
			Virus v = elements.get(i);
			/*if (p1.getX() > 640)
				p1.setLocation(p1.getX()-640, p1.getY());
			else if (p1.getX() < 0)
				p1.setLocation(p1.getX()+640, p1.getY());
			if (p1.getY() > 480)
				p1.setLocation(p1.getX(), p1.getY()-480);
			else if (p1.getY() < 0)
				p1.setLocation(p1.getX(), p1.getY()+480);*/
			double angle1 = v.getAngle();
			Point2D pos = v.getPosition();
			pos.setLocation(pos.getX()+Math.cos(angle1)*speed, pos.getY()+Math.sin(angle1)*speed);
			//v.setLocation(p1.getX()+Math.cos(angle1)*speed, p1.getY()+Math.sin(angle1)*speed);
		}
		
	}
	
	/**
	 * Vraca rastojanje
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double getDistance(Point2D p1, Point2D p2) {
		return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
	}
	
	/**
	 * Geter za boju
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Seter za boju
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Iterator <Virus> it = elements.iterator();
		g.setColor(color);
		while (it.hasNext()) {
			Virus v = it.next();
			Point2D p = v.getPosition();
			
			AffineTransform t = g.getTransform();
			g.translate(p.getX(), p.getY());
			g.rotate(v.getAngle());
			int xCoords[] = {-4,6,-4};
			int yCoords[] = {-4,0,4};
			//Blur
			for (int i = 0; i<3;i++) {
				Color c = new Color(color.getRed(),color.getGreen(),color.getBlue(), (i+1)*32);
				g.setColor(c);
				g.setStroke(new BasicStroke(4.5f-i*1.5f));
				g.drawPolygon(xCoords,yCoords, 3);
			}
			g.setStroke(new BasicStroke(1));
			g.setColor(color);
			g.drawPolygon(xCoords, yCoords, 3);
			//g.fillOval((int)p.getX()-4, (int)p.getY()-4, 8, 8);
			g.setTransform(t);
		}
		
		/*for (i = 0; i < aims.size();i++) {
			Point2D p = elements.get(i);
			Point2D a = aims.get(i);
			g.drawLine((int)p.getX(), (int)p.getY(), (int)p.getX()+(int)a.getX(), (int)p.getY()+(int)a.getY());
		}*/
	}
	/*public Point2D.Float getTarget() {
		return target;
	}
	public void setTarget(Point2D.Float target) {
		this.target = target;
	}*/
	
	

}
