package threading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	
	private XY xy;
	
	public static int x;
	public static int y;
	
	public static int dx;
	public static int dy;
	
	private boolean lost = false;
	private int lostPosX;
	private int lostPosY;
	private int blocked = 0;	//successfull blocks in a row	//TODO Implement detection of successfull blocks
	
	public static long secsL = 0;
	public static long secsR = 0;
	private int dispose = 0;	//Used to mesure time before game has to be relaunched
	
	private BufferedImage intro = null;
	private BufferedImage over = null;
	
	public Ball() {
		
		xy = new XY();
		xy.random(blocked);
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
		
		try {
			intro = ImageIO.read(new File("resources/Intro.jpg"));
			over = ImageIO.read(new File("resources/Over.jpg"));
		} catch (IOException e) {
			System.err.println("Can't load 'resources/Intro.jpg' or 'resources/Over.jpg' " +
					"Check if this directory is available in the same path " +
					"as this launched *.jar file.");
		}
	}
	
	/*public Ball(int version) {
		// TODO Auto-generated constructor stub
	}*/

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
						if (y+10 < BlockL.yL && y > 30) {	//above left block
							//Timer.right += secsR - System.currentTimeMillis();
							Timer.right += blocked*XY.level;
							lost = true;
							lostPosX = x;
							lostPosY = y;
							//Adds elapsed time since last lost to the other player
						} else {
							if (y-10 > BlockL.yL+BlockL.extend && y < Content.HEIGHT-50) {	//under left block
								//Timer.right += secsR - System.currentTimeMillis();
								Timer.right += blocked*XY.level;
								lost = true;
								lostPosX = x;
								lostPosY = y;
								//Adds elapsed time since last lost to the other player
							} else { //Block successfull? → Upgrade Level after # successfull blocks
								if (y-10 >= 20 && y+10 <= Content.HEIGHT-40) {
									blocked++;
									//System.out.println("blocked++ " + blocked);
								}
							}
						}
					} else {
						if (x > Content.WIDTH-110) {	//right border
							dx = -dx;
							x = Content.WIDTH-110;
							if (y+10 < BlockR.yR && y > 30) {	//above right Block
								//Timer.left += secsL - System.currentTimeMillis();
								Timer.left += blocked*XY.level;
								lost = true;
								lostPosX = x;
								lostPosY = y;
								//Adds elapsed time since last lost to the other player
							} else {
								if (y-10 > BlockR.yR+BlockR.extend && y < Content.HEIGHT-50) {	//under right block
									//Timer.left += secsL - System.currentTimeMillis();
									Timer.left += blocked*XY.level;
									lost = true;
									lostPosX = x;
									lostPosY = y;
									//Adds elapsed time since last lost to the other player
								} else {	//Block successfull? → Upgrade Level after # successfull blocks
									if(y-10 >= 20 && y+10 <= Content.HEIGHT-40) {
										blocked++;
										//System.out.println("blocked++ " + blocked);
									}
								}
							}
						}	
					}
				}
			}
		}
		//System.out.println(blocked);
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
				XY.lives--;
				if(XY.lives > 0) {
					restart();
				} else {
					System.out.println("Sorry. Both of you lost.");
					Content.start=4;
				}
			}
		} else {	//0: intro; 1: launching after intro; 2: operational mode; 4: game over
			if(Content.start==0) {
				g.drawImage(intro, null, 83, 03);
			} else {
				if(Content.start==1) {
					g.fillRect(83, 03, intro.getWidth(), intro.getHeight());
					Content.start=2;
				} else {
					if (Content.start==2) {
						g.setColor(Color.GREEN);
						g.fillOval(x-10, y-10, 20, 20);
					} else {
						if (Content.start == 4) {	//TODO Game has to suspend for the time of the game over screen
							g.drawImage(over, null, 83, 03);
						}
					}
				}
			}
		}
	}

	private void restart() {
		
		xy.random(blocked);
		blocked=0;
		System.out.println(System.nanoTime() + " dx: " + dx + " dy: " + dy);
	}
}
