package threading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Content extends JPanel implements Runnable, KeyListener {

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
		// TODO Auto-generated method stub
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	private void render() {
		// TODO Auto-generated method stub
		//System.out.println(System.nanoTime() + " Rendering.");
		g.setColor(Color.BLACK);
		//g.drawRect(5, 5, 690, 290); //Boarders
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.drawRect(80, 0, WIDTH-180, HEIGHT-20);
		g.drawString("FPS "+ averageFPS, WIDTH / 2, HEIGHT-1);
		//g.drawOval(20, 20, 20, 20);
		
		ball.draw(g);
		blockL.draw(g);
		blockR.draw(g);
	}

	private void update() {
		ball.update();
		blockL.update(g);
		blockR.update(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP) {	//Checking key events for up/down of blocker
			BlockR.setUp(true);
		} else {
			if(keyCode == KeyEvent.VK_DOWN) {
				BlockR.setDown(true); 
			} else {
				if(keyCode == KeyEvent.VK_Q) {
					BlockL.setUp(true); 
				} else {
					if(keyCode == KeyEvent.VK_A) {
						BlockL.setDown(true); 
					} else {
						System.out.println("No usable Keyboard input detected. Ignoring.");
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
