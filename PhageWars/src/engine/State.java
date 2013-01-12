package engine;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLayeredPane;


/**
 * @author Stefan Jeremic
 *
 */
public abstract class State extends JLayeredPane{
	//protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	/**
	 * indicates whether to or not paint sprite panel.
	 */
	protected boolean paintSpritePanel = true;
	/**
	 * sprite panel to be painted.
	 */
	protected SpritePanel spritePanel = new SpritePanel();

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5167592681331823221L;
	
	/**
	 * Main constructor, sets layout to flowlayout.
	 */
	public State(){
		setLayout(new FlowLayout());
	}
	
	/**
	 * @return paintSpritePanel
	 */
	public boolean isPaintSpritePanel() {
		return paintSpritePanel;
	}

	/**
	 * sets paintSpritePanel
	 * @param paintSpritePanel
	 */
	public void setPaintSpritePanel(boolean paintSpritePanel) {
		this.paintSpritePanel = paintSpritePanel;
	}
	
	/**
     * Paints to g
     * @param Graphics
     */
	public void paint(Graphics g)
    {
		if (paintSpritePanel)
			spritePanel.paint(g);
		super.paint(g);
    }
	/**
	 * @return spritePanel
	 */
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
	/**
	 * @return all the sprites
	 */
	public ArrayList<Sprite> getSprites(){
		return spritePanel.getSprites();
	}
	/**
	 * cals update for all sprites
	 * 
	 */
	public void cycle() {
		ArrayList<Sprite> sprites = spritePanel.getSprites();
		for (int i = 0; i < sprites.size(); i++) {
	    	  sprites.get(i).update();
		}
    }


}