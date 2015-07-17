package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ball {
	
	private XY xy;
	
	public static int x;
	public static int y;
	
	public static int dx;
	public static int dy;
	
	private boolean lost = false;
	//private int lostTime = 0;
	private int lostPosX;
	private int lostPosY;
	public static int blocked = 0;	//successfull blocks in a row	//TODO Implement detection of successfull blocks
	
	public static long secsL = 0;
	public static long secsR = 0;
	private int dispose = 0;	//Used to mesure time before game has to be relaunched
	//private long secsLast = System.currentTimeMillis();
	
	private BufferedImage intro = null;
	
	public Ball() {
		
		xy = new XY();
		xy.random();
		
		/*Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;	//TODO adapt relation of both directions //TODO Add levels 
		dy = ran.nextInt(10)+1;*/
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
		
		try {
			intro = ImageIO.read(new File("resources/Intro.jpg"));
		} catch (IOException e) {
			System.err.println("Can't load 'resources/Intro.jpg' Check if this directory is available in the same path " +
					"as this launched *.jar file.");
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
					if (x < 90) {	//left border
						dx = -dx;
						x = 90;	//in order to prevent the ball crossing the border
							
						if (y-10 > BlockL.yL && y+10 < BlockL.yL+BlockL.extend) {
							blocked++;
						}
							
						if (y+10 < BlockL.yL && y > 30) {	//above left block
							//System.out.println("Above left Block " + x + "," + y + " Block(-) " + BlockL.yL);
							Timer.right += secsR - System.currentTimeMillis();
							lost = true;
							lostPosX = x;
							lostPosY = y;
							//Adds elapsed time since last lost to the other player
						} else {
							if (y-10 > BlockL.yL+BlockL.extend && y < Content.HEIGHT-50) {	//under left block
								//System.out.println("Under left Block " + x + "," + y + " Block(+) " + BlockL.yL);
								Timer.right += secsR - System.currentTimeMillis();
								lost = true;
								lostPosX = x;
								lostPosY = y;
								//Adds elapsed time since last lost to the other player
							} 
						}
					} else {
						if (x > Content.WIDTH-110) {	//right border
							dx = -dx;
							x = Content.WIDTH-110;
							if (y-10 > BlockR.yR && y+10 < BlockR.yR+BlockR.extend) {
								blocked++;
							}
							if (y+10 < BlockR.yR && y > 30) {	//above right Block
								//System.out.println("Above right Block " + x + "," + y + " Block(-) " + BlockR.yR);
								Timer.left += secsL - System.currentTimeMillis();
								lost = true;
								lostPosX = x;
								lostPosY = y;
								//Adds elapsed time since last lost to the other player
							} else {
								if (y-10 > BlockR.yR+BlockR.extend && y < Content.HEIGHT-50) {	//under right block
									//System.out.println("Under right Block " + x + "," + y + " Block(+) " + BlockR.yR);
									Timer.left += secsL - System.currentTimeMillis();
									lost = true;
									lostPosX = x;
									lostPosY = y;
									//Adds elapsed time since last lost to the other player
								}
							}
						}	
					}
				}
			}
		}
		System.out.println(blocked);
	}

	public void draw(Graphics2D g) {
		
		if (lost) {
			Content.start=3;
			dispose++;
			g.setColor(Color.RED);
			g.fillOval(lostPosX-10, lostPosY-10, 20, 20);
			if(dispose==20) {
				dispose=0;
				Content.start=2;
				lost=false;
				restart();
			}
		} else {
			if(Content.start==0) {
				g.drawImage(intro, null, 83, 03);
			} else {
				if(Content.start==1) {
					g.fillRect(83, 03, intro.getWidth(), intro.getHeight());
					Content.start=2;
				} else {
					if (Content.start!=3) {
						g.setColor(Color.GREEN);
						g.fillOval(x-10, y-10, 20, 20);
					}
				}
			}
		}
	}

	private void restart() {
		
		xy.random();
		
		/*Random ran = new Random();	//Initial direction	//In general, one axis cannot be above 10
		dx = ran.nextInt(10)+1;
		dy = ran.nextInt(10)+1;
		x = Content.WIDTH / 2;
		y = Content.HEIGHT / 2;*/
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
	}
}
 