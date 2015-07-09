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
		window.add(new Content());
		//window.setContentPane(new Content());
		window.pack();
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
