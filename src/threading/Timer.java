package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Timer {


	public static long left = 0;
	public static long right = 0;
	private BufferedImage heart = null;	//In opposite to 'lives' which contains # of lives this contains the actual jpg
	//public long start = System.currentTimeMillis();
	
	public Timer() {
		try {
			heart = ImageIO.read(new File("resources/LivesSmall.jpg"));
		} catch (IOException e) {
			System.err.println("Can't load 'resources/LivesSmall.jpg' Check if this directory is available in the same path " +
					"as this launched *.jar file.");
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString(""+(left), 10, Content.HEIGHT-8);	//Points: Blocks in a row * Level
		g.drawString(""+(right), Content.WIDTH-120, Content.HEIGHT-8);
		g.drawString("Level " + XY.level, Content.WIDTH/2-10, Content.HEIGHT-8);
		for(int i=XY.lives;i>0;i--) {	//Paints the amount of hearts
			g.drawImage(heart, null, Content.WIDTH/2-30*i, Content.HEIGHT-18);
		}
	}
}
