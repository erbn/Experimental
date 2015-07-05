package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockR {	//Default moving keys: VK_UP → UP; VK_DOWN → DOWN

	private int y;
	private static int dy;
	
	public BlockR() {
		y = Content.HEIGHT/2;	//Block initially centered 
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(700, y-50, 700, y+50);
	}

	public void update(Graphics2D g) {
		
		y += dy;
		if (y < 40) {	//Keeps the Block in the visible area
			y = 40;
		} else {
			if (y > Content.HEIGHT-70) {	//Why is the border to frame end 70?
				y = Content.HEIGHT-70;
			}
		}
		dy = 0;
	}

	public static void setUp() {
		
		dy = -10;
		//System.out.println("dy" + dy);
	}

	public static void setDown() {
		
		dy = 10;
		//System.out.println("dy" + dy);
	}

}
