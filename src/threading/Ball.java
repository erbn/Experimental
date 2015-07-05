package threading;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	public Ball() {
		
		x = Content.WIDTH / 2;
		y = Content.HEIGHT / 2;
	}
	
	public void update() {
		//TODO User input
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillOval(x-20, y-20, 20, 20);
	}

}
