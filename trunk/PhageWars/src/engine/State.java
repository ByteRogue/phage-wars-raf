package engine;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLayeredPane;


public abstract class State extends JLayeredPane{
	//protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected boolean paintSpritePanel = true;
	protected SpritePanel spritePanel = new SpritePanel();

	private static final long serialVersionUID = -5167592681331823221L;
	
	public State(){
		setLayout(new FlowLayout());
	}
	
	public boolean isPaintSpritePanel() {
		return paintSpritePanel;
	}

	public void setPaintSpritePanel(boolean paintSpritePanel) {
		this.paintSpritePanel = paintSpritePanel;
	}

	public void paint(Graphics g)
    {
		if (paintSpritePanel)
			spritePanel.paint(g);
		super.paint(g);
    }
	public SpritePanel getSpritePanel(){
		return spritePanel;
	}
	@Override
	public Dimension getMinimumSize() {
		// TODO Auto-generated method stub
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(640, 480);
	}
	public ArrayList<Sprite> getSprites(){
		return spritePanel.getSprites();
	}
	public void cycle() {
		ArrayList<Sprite> sprites = spritePanel.getSprites();
		for (int i = 0; i < sprites.size(); i++) {
	    	  sprites.get(i).update();
		}
    }


}