package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockR {	//Default moving keys: VK_UP → UP; VK_DOWN → DOWN

	public static int yR;	//Used to inform Ball collision check about Block's position
	private static int dy;
	public static int extend = 40;	//pixels above (under) center of the block
	
	public BlockR() {
		yR = Content.HEIGHT/2;	//Block initially centered 
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(Content.WIDTH-100, yR, Content.WIDTH-100, yR+extend);
	}

	public void update() {
		
		yR += dy;
		if (yR < 20) {	//Keeps the Block in the visible area
			yR = 20;
		} else {
			if (yR > Content.HEIGHT-40-extend) {
				yR = Content.HEIGHT-40-extend;
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
