package threading;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Message {	//TODO Syncronize threads
	
	private BufferedImage intro = null;
	private BufferedImage over = null;
	
	public Message() {
		
		try {
			intro = ImageIO.read(new File("resources/Intro.jpg"));
			over = ImageIO.read(new File("resources/Over.jpg"));
		} catch (IOException e) {
			System.err.println("Can't load 'resources/Intro.jpg' or 'resources/Over.jpg' " +
					"Check if this directory is available in the same path " +
					"as this launched *.jar file.");
		}
	}
	
	public void draw(Graphics2D g) {
		if(Content.start==4) {
			g.drawImage(over, null, 83, 03);
		} else {
			if(Content.start==0) {
				g.drawImage(intro, null, 83, 03);
			}
		}
	}

	public void update() {
		
	}
}
