package threading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Content extends JPanel implements Runnable, KeyListener, MouseListener {

	public static int WIDTH = 800;
	public static int HEIGHT = 400;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private double averageFPS;
	
	private Ball ball;
	private BlockL blockL;
	private BlockR blockR;
	private Timer timer;
	
	public static int start = 0;

	public Content () {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	//Function
	public void addNotify() {
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
		addMouseListener(this);
	}
	public void run() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		long targetTime = 1000 / FPS;
		
		
		int frameCount = 0;
		int maxFrameCount = 30;
		
		ball = new Ball();
		blockR = new BlockR();
		blockL = new BlockL();
		
		while(running) {
			
			startTime = System.nanoTime();
			
			update();
			render();
			draw();
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			
			waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
				//TODO
			}
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if(frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000.0);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	private void draw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	private void render() {
		
		int[] xcooUL = {80,80,WIDTH-100,WIDTH-100};
		int[] ycooU = {20, 0, 0, 20};
		int[] ycooL = {HEIGHT-40,HEIGHT-20,HEIGHT-20,HEIGHT-40};
		
		/*BufferedImage intro = null;
		try {
			intro = ImageIO.read(new File("resources/Intro.jpg"));
		} catch (IOException e) {
			//TODO
		}*/
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		
		g.drawPolyline(xcooUL, ycooU, 4);	//upper border
		g.drawPolyline(xcooUL, ycooL, 4);	//lower border
		g.drawString("FPS "+ averageFPS, WIDTH / 2, HEIGHT-1);
		//g.drawImage(intro, null, 83, 03);
		
		ball.draw(g);
		blockL.draw(g);
		blockR.draw(g);;
	}

	private void update() {
		
		blockL.update(g);
		blockR.update(g);
		ball.update();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("key event.");
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP) {	//Checking key events for up/down of blocker
			BlockR.setUp();
			//System.out.println("up.");
		} else {
			if(keyCode == KeyEvent.VK_DOWN) {
				BlockR.setDown();
				//System.out.println("down.");
			} else {
				if(keyCode == KeyEvent.VK_Q) {
					BlockL.setUp();
					//System.out.println("q.");
				} else {
					if(keyCode == KeyEvent.VK_A) {
						BlockL.setDown();
						//System.out.println("a.");
					} else {
						if(keyCode == KeyEvent.VK_S) {
							start=1;
						} else {
							System.out.println("No usable Keyboard input detected. Ignoring.");
						}
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("key event.");
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP) {	//Checking key events for up/down of blocker
			BlockR.setUp();
			//System.out.println("up.");
		} else {
			if(keyCode == KeyEvent.VK_DOWN) {
				BlockR.setDown();
				//System.out.println("down.");
			} else {
				if(keyCode == KeyEvent.VK_Q) {
					BlockL.setUp();
					//System.out.println("q.");
				} else {
					if(keyCode == KeyEvent.VK_A) {
						BlockL.setDown();
						//System.out.println("a.");
					} else {
						if(keyCode == KeyEvent.VK_S) {
							start=1;
						} else {
							System.out.println("No usable Keyboard input detected. Ignoring.");
						}
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {	//TODO Mouse wheel event never occurs. Alternatives for multiple player mode???
		System.out.println("MouseWheelEvent");
		int notches = e.getWheelRotation();
		if (notches < 0) {
			BlockR.setDown();
		} else {
			BlockR.setUp();
		}
	/*       if (notches < 0) {
	           message = "Mouse wheel moved UP "
	                        + -notches + " notch(es)" + newline;
	       } else {
	           message = "Mouse wheel moved DOWN "
	                        + notches + " notch(es)" + newline;
	       }
	       if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	           message += "    Scroll type: WHEEL_UNIT_SCROLL" + newline;
	           message += "    Scroll amount: " + e.getScrollAmount()
	                   + " unit increments per notch" + newline;
	           message += "    Units to scroll: " + e.getUnitsToScroll()
	                   + " unit increments" + newline;
	           message += "    Vertical unit increment: "
	               + scrollPane.getVerticalScrollBar().getUnitIncrement(1)
	               + " pixels" + newline;
	       } else { //scroll type == MouseWheelEvent.WHEEL_BLOCK_SCROLL
	           message += "    Scroll type: WHEEL_BLOCK_SCROLL" + newline;
	           message += "    Vertical block increment: "
	               + scrollPane.getVerticalScrollBar().getBlockIncrement(1)
	               + " pixels" + newline;
	       }
	       saySomething(message, e);*/
	    }
}
