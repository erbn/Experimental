package threading;

//import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Content extends JPanel implements Runnable {

	public static int WIDTH = 700;
	public static int HEIGHT = 300;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private double averageFPS;
	
	private Ball ball;

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
		g.drawString("FPS "+ averageFPS, 10, 60);
		//g.drawOval(20, 20, 20, 20);
		
		ball.draw(g);
	}

	private void update() {
		ball.update();
		// TODO Auto-generated method stub
		/*Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();*/
	}
}

/*	public void paintComponent(Graphics g) {
//super.paintComponent(g);
g.drawRect(5, 5, 690, 290); //Boarders
g.drawString("Example", 10, 20);
g.drawOval(20, 20, 20, 20);
}*/
