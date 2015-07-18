package threading;

import java.util.Random;

public class XY {

	public static int level = 1;
	public static int lives = 3;
	
	public void random(int blocked) {
		
		if(blocked >= level*3) {	//upgrade after # successfull blocks
			level++;
			if (lives!=3){
				lives++;
			}
			if(BlockL.extend > 40) {	//Reduces size of blocks by level upgrade
				BlockL.extend = 80-level*4;
				BlockR.extend = BlockL.extend;
			}
		}
		
		Random ran = new Random();	//Initial direction of ball
		int sum = 0;
		sum = ran.nextInt(level*7) + level*5;
		System.out.println(sum+", level: "+level);
		
		Ball.dy = ran.nextInt(level+3)+1;
		Ball.dx = sum-Ball.dy;
		
		if(ran.nextBoolean()) {
			Ball.dx = -Ball.dx;
		}
		
		Ball.x = Content.WIDTH / 2;
		Ball.y = Content.HEIGHT / 2;
	}

}
