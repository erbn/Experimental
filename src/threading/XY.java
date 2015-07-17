package threading;

import java.util.Random;

public class XY {

	public static int level = 1;
	
	public void random() {
		
		if(Ball.blocked == 3) {	//upgrade after # successfull blocks
			level++;
			Ball.blocked=0;
		}
		
		Random ran = new Random();	//Initial direction of ball
		int sum = 0;
		sum = ran.nextInt(level*7) + level*5;
		System.out.println(sum+", level: "+level);
		
		Ball.dx = ran.nextInt(sum-5+level)+1;
		Ball.dy = sum-Ball.dx;
		
		if(ran.nextBoolean()) {
			Ball.dx = -Ball.dx;
		}
		
		Ball.x = Content.WIDTH / 2;
		Ball.y = Content.HEIGHT / 2;
	}

}
