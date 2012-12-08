import engine.Sprite;
import engine.SpritePanel;


public class MenuSpritePanel extends SpritePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665320743252854435L;

	public MenuSpritePanel(){
		Sprite background = new Sprite();
		background.setImg(loadImage("background.png").getImage());
		sprites.add(background);
	}

}
