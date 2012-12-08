package engine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class SpritePanel extends JPanel {
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
	public void paint(Graphics g)
    {
		super.paint(g);

      Graphics2D g2 = (Graphics2D) g;

      RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);

      rh.put(RenderingHints.KEY_RENDERING,
             RenderingHints.VALUE_RENDER_QUALITY);

      g2.setRenderingHints(rh);
      


      for (int i = 0; i < sprites.size(); i++)
    	  sprites.get(i).paint(g2);
    	  
      //g2.dispose();
    }
	
	/*@Override
	public Dimension getMinimumSize() {
		// TODO Auto-generated method stub
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(640, 480);
	}*/
	public ImageIcon loadImage(String name){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(name));
		return ii;
	}
}
