package threading;

import java.util.Random;

public class XY {

	public static int level = 1;
	
	public void random(int blocked) {
		
		if(blocked >= 3) {	//upgrade after # successfull blocks
			level++;
			if(BlockL.extend > 40) {	//Reduces size of blocks by level upgrade
				BlockL.extend = 80-level*4;
				BlockR.extend = BlockL.extend;
			}
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
