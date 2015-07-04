package threading;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Principal {

	static String title = "Jail";
	
	public static void main(String[] args) {
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	             guiCreation();
	          }
	       });
	}
	public static void guiCreation() {
		SwingUtilities.isEventDispatchThread();
		JFrame f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Ball());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}

class Ball extends JPanel {
	public Dimension getPreferredSize(){
		return new Dimension(700,300);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(5, 5, 690, 290); //Boarders
		g.drawString("Example", 10, 20);
		g.drawOval(20, 20, 20, 20);
	}
}