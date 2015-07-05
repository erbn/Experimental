package threading;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Principal extends JFrame {

	static String title = "Jail";
	
	private Principal() {
		JFrame window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setSize(700, 300);
		window.setResizable(false);
		System.out.println(System.nanoTime() + " Started basic window. Loading GUI.");
		window.setContentPane(new Content());
		window.pack();	//TODO This one or window.setSize?
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		/*
		SwingUtilities.isEventDispatchThread();
		JFrame f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Ball());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);*/
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Principal();
			}
		});
	}
	
}

/*class Ball extends JPanel {
	public Dimension getPreferredSize(){
		return new Dimension(700,300);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(5, 5, 690, 290); //Boarders
		g.drawString("Example", 10, 20);
		g.drawOval(20, 20, 20, 20);
	}
}*/