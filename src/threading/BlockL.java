
package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockL {	//Default moving keys: VK_Q → UP; VK_A → DOWN

	public static int yL;	//Used to inform Ball collision check about Block's position
	private static int dy;
	
	public BlockL() {
		yL = Content.HEIGHT/2;	//Block initially centered 
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(80, yL-50, 80, yL+50);
		
	}

	public void update(Graphics2D g) {
		
		yL += dy;
		if (yL < 70) {	//Keeps the Block in the visible area
			yL = 70;
		} else {
			if (yL > Content.HEIGHT-90) {
				yL = Content.HEIGHT-90;
			}
		}
		dy = 0;

	}

	public static void setUp() {
		
		dy = -30;
		//System.out.println("dy" + dy);
	}

	public static void setDown() {
		
		dy = 30;
		//System.out.println("dy" + dy);
	}

}
