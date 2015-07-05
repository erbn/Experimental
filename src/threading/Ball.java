package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	/*private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;*/
	
	public Ball() {
		
		x = Content.WIDTH / 2;
		y = Content.HEIGHT / 2;
		
		Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;
		dy = ran.nextInt(10)+1;
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
	}
	
	public void update() {
		
		//Border Collision check
		if (x >= Content.WIDTH) {	//Right Border
			dx = -dx;
		}
		
		if (x <= 0) {	//Left Border
			dx = -dx;
		}
		
		if (y >= Content.HEIGHT) {	//Lower Border
			dy = -dy;
		}
		if (y <= 0) {	//Upper Border
			dy = -dy;
		}
		
		//Moving
		x += dx;
		y += dy;
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillOval(x-20, y-20, 20, 20);
	}

}
