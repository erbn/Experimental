package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ball {
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private boolean lost = false;
	private int lostTime = 0;
	private int lostPosX;
	private int lostPosY;
	
	public static long secsL = 0;
	public static long secsR = 0;
	private long secsLast = System.currentTimeMillis();
	
	private BufferedImage intro = null;
	
	public Ball() {
		
		x = Content.WIDTH / 2;
		y = Content.HEIGHT / 2;
		
		Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;
		dy = ran.nextInt(10)+1;
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
		
		//image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		try {
			intro = ImageIO.read(new File("resources/Intro.jpg"));
		} catch (IOException e) {
			//TODO
		}
		
		
	}
	
	public void update() {
		
		if(Content.start!=0) {
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
							secsR = System.currentTimeMillis() - secsLast;
							secsLast = System.currentTimeMillis();	//Adds elapsed time since last lost to the other player
						} else {
							if (y > BlockL.yL+60 && y < Content.HEIGHT-50) {	//under left block
								//System.out.println("Under left Block " + x + "," + y + " Block(+) " + BlockL.yL);
								lost = true;
								lostPosX = x;
								lostPosY = y;
								secsR = System.currentTimeMillis() - secsLast;
								secsLast = System.currentTimeMillis();	//Adds elapsed time since last lost to the other player
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
								secsL += System.currentTimeMillis() - secsLast;
								secsLast = System.currentTimeMillis();	//Adds elapsed time since last lost to the other player
							} else {
								if (y > BlockR.yR+60 && y < Content.HEIGHT-50) {	//under right block
									//System.out.println("Under right Block " + x + "," + y + " Block(+) " + BlockR.yR);
									lost = true;
									lostPosX = x;
									lostPosY = y;
									secsL += System.currentTimeMillis() - secsLast;
									secsLast = System.currentTimeMillis();	//Adds elapsed time since last lost to the other player
									//TODO Millis / secs?? Verify points
								}	
							}
						}	
					}
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		while (lost) {
			g.setColor(Color.RED);
			g.drawOval(lostPosX, lostPosY, 20, 20);
			System.out.println("Lost ball.");
			g.drawString(""+Ball.secsL, 80, Content.HEIGHT-10);
			g.drawString(""+Ball.secsR, Content.WIDTH-60, Content.HEIGHT-10);
			if (System.currentTimeMillis()-secsLast> 50) {	//TODO Adapt useful time before relaunching
				restart();
				lost = false;
			}
		}
		if(Content.start==0) {
			g.drawImage(intro, null, 83, 03);
		} else {
			if(Content.start==1) {
				g.fillRect(83, 03, intro.getWidth(), intro.getHeight());
				Content.start=2;
			} else {
				g.setColor(Color.GREEN);
				g.fillOval(x-10, y-10, 20, 20);
			}
		}
	}

	private void restart() {
		Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;
		dy = ran.nextInt(10)+1;
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
	}
}
