package threading;

import java.awt.Graphics2D;

public class Timer {

	/*public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}*/
	
	public void draw(Graphics2D g) {
		g.drawString(""+Ball.secsL, 80, Content.HEIGHT-20);
		g.drawString(""+Ball.secsR, Content.WIDTH-60, Content.HEIGHT-20);
	}
}
