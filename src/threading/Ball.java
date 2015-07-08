package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private boolean lost = false;
	private int lostTime = 0;
	private int lostPosX;
	private int lostPosY;
	
	public Ball() {
		
		x = Content.WIDTH / 2;
		y = Content.HEIGHT / 2;
		
		Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;
		dy = ran.nextInt(10)+1;
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
	}
	
	public void update() {
		
		//Moving
		x += dx;
		y += dy;
		
		//Collision check
		if (y >= Content.HEIGHT-30) {	//lower border
			dy = -dy;
			y = Content.HEIGHT-30;
		} else {
			if (y <= 10) {	//upper border
				dy = -dy;
				y = 10;
			} else {
				if (x < 90) {	//left border	//TODO loose ball if it does not collide with block/border
					dx = -dx;
					x = 90;	//in order to prevent the ball crossing the border
					if (y < BlockL.yL-60 && y > 30) {	//above left block
						//System.out.println("Above left Block " + x + "," + y + " Block(-) " + BlockL.yL);
						lost = true;
						lostPosX = x;
						lostPosY = y;
					} else {
						if (y > BlockL.yL+60 && y < Content.HEIGHT-50) {	//under left block
							//System.out.println("Under left Block " + x + "," + y + " Block(+) " + BlockL.yL);
							lost = true;
							lostPosX = x;
							lostPosY = y;
						}
					}
				} else {
					if (x > Content.WIDTH-110) {	//right border
						dx = -dx;
						x = Content.WIDTH-110;
						if (y < BlockR.yR-60 && y > 30) {	//above right Block
							//System.out.println("Above right Block " + x + "," + y + " Block(-) " + BlockR.yR);
							lost = true;
							lostPosX = x;
							lostPosY = y;
						} else {
							if (y > BlockR.yR+60 && y < Content.HEIGHT-50) {	//under right block
								//System.out.println("Under right Block " + x + "," + y + " Block(+) " + BlockR.yR);
								lost = true;
								lostPosX = x;
								lostPosY = y;
							}	
						}
					}	
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		if (lost) {
			g.setColor(Color.RED);
			g.drawOval(lostPosX, lostPosY, 20, 20);
			lostTime++;
			if (lostTime > 50) {
				System.out.println("Lost ball. Terminating");
				System.exit(0);	//TODO relaunching game. counting points. detecting player who lost the ball
			}
		} else {
			g.setColor(Color.GREEN);
			g.fillOval(x-10, y-10, 20, 20);
		}
	}
}
