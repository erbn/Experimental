package threading;

import java.awt.Color;
import java.awt.Graphics2D;

public class Timer {

	public static long left = 0;
	public static long right = 0;
	public long start = System.currentTimeMillis();
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString(""+(-left), 10, Content.HEIGHT-8);
		g.drawString(""+(-right), Content.WIDTH-120, Content.HEIGHT-8);
		g.drawString("Level " + XY.level, Content.WIDTH/2-20, Content.HEIGHT-8);
	}
}
