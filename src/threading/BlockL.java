
package threading;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockL {	//Default moving keys: VK_Q → UP; VK_A → DOWN

	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3));
		g.drawLine(80, 270, 80, 360);	//TODO Find y position 
		
	}

	public void update(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	public static void setUp(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public static void setDown(boolean b) {
		// TODO Auto-generated method stub
		
	}

}