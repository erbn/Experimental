
package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockL {	//Default moving keys: VK_Q → UP; VK_A → DOWN

	public static int yL;	//Used to inform Ball collision check about Block's position
	private static int dy;
	public static int extend=80;
	
	public BlockL() {
		yL = Content.HEIGHT/2;	//Block initially centered 
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(80, yL, 80, yL+extend);
	}

	public void update() {
		
		yL += dy;
		//Keeps the Block in the visible area
		if (yL < 20) {	//upper border
			yL = 20;
		} else {
			if (yL > Content.HEIGHT-40-extend) {	//lower border
				yL = Content.HEIGHT-40-extend;
			}
		}
		dy = 0;
	}

	public static void setUp() {
		
		dy = -60;
		//System.out.println("dy" + dy);
	}

	public static void setDown() {
		
		dy = 60;
		//System.out.println("dy" + dy);
	}

}
