package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockR {	//Default moving keys: VK_UP → UP; VK_DOWN → DOWN

	public static int yR;	//Used to inform Ball collision check about Block's position
	private static int dy;
	
	public BlockR() {
		yR = Content.HEIGHT/2;	//Block initially centered 
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(Content.WIDTH-100, yR-50, Content.WIDTH-100, yR+50);
	}

	public void update(Graphics2D g) {
		
		yR += dy;
		if (yR < 70) {	//Keeps the Block in the visible area
			yR = 70;
		} else {
			if (yR > Content.HEIGHT-90) {
				yR = Content.HEIGHT-90;
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
