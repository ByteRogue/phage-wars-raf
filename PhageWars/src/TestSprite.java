import engine.Sprite;


public class TestSprite extends Sprite {
	public TestSprite(){
		
	}
	public void update(){
		x+=3;
		y+=3;
		if (x > 640)
			x = 0;
		if (y > 480)
			y = 0;
	}
}
