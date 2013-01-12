package components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * @author Stefan Jeremic
 *
 */
public class GameScrollBarUI extends BasicScrollBarUI {
	/**
	 * Main constructor.
	 */
	public GameScrollBarUI() {
		// TODO Auto-generated constructor stub
		//trackColor = new Color(0x555555);
		//trackHighlightColor = new Color(0x555555);
	}
	@Override
	protected void configureScrollBarColors()
    {
        LookAndFeel.installColors(scrollbar, "ScrollBar.background",
                                  "ScrollBar.foreground");
	    thumbHighlightColor = new Color(0x555555);//UIManager.getColor("ScrollBar.thumbHighlight");
	    thumbLightShadowColor =new Color(0x555555);// UIManager.getColor("ScrollBar.thumbShadow");
	    thumbDarkShadowColor = new Color(0x222222);//UIManager.getColor("ScrollBar.thumbDarkShadow");
	    thumbColor = new Color(0x555555);//UIManager.getColor("ScrollBar.thumb");
	    trackColor = new Color(0x77aa77);//UIManager.getColor("ScrollBar.track");
	    trackHighlightColor = new Color(0x669966);//UIManager.getColor("ScrollBar.trackHighlight");
    }
	@Override
	protected JButton createDecreaseButton(int orientation)  {
        return new BasicArrowButton(orientation,
                   	new Color(0x555555),
                   	new Color(0x222222),
                   	new Color(0x00ff00),
                    new Color(0x222222));
    }
	@Override
    protected JButton createIncreaseButton(int orientation)  {
        return new BasicArrowButton(orientation,
        		new Color(0x555555),
               	new Color(0x222222),
               	new Color(0x00ff00),
                new Color(0x222222));
    }
      
}
