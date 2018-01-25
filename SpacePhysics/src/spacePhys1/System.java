package spacePhys1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class System extends Applet implements Runnable {
	int width = 1200, height = 900;
	int numberOfObjects = 300;
	
	Thread thread;
	ArrayList<Object> objects = new ArrayList<Object>();
	
	public void init(){
		this.resize(width, height);
		thread = new Thread(this);
		thread.start();		

	}

	public void paint(Graphics g){
		
		setBackground (Color.black);
		g.translate(width/2, height/2);
		
		
		for(Object a : objects) {
			a.paintObject(g);
		}
		
}

	
	public void update() {

		moveObjs();

	}

	public void run() {		
		
//here is where you make your initial materials with a for loop}
		
		Object sun = new Object(0,0,1000);		
		objects.add(sun);
		
		Object sun2 = new Object(100,100,10);		
		objects.add(sun2);
		
//		Object sun3 = new Object(100,200,500);		
//		objects.add(sun3);
		
//		for(int x = 2 ; x < numberOfObjects; x++) {
//			Object tempObj = new Object();
//			
//			objects.add(tempObj);
//		}
		
		for(;;){
			//This is what loops the system to simulate motion
			update();
			repaint();

			try {
				//Change sleep time to speed or slow simulation.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void moveObjs(){
		double gravityMagnitude = 0;
		double gravityDirection = 0;
		double distBetween;
		
		for(Object a : objects) {

			for(Object b : objects){
				if(a == b){
					continue;
				}
				double these2Grav;
				double these2Dir;
				distBetween = a.distanceBetween(a, b);
				these2Grav = Object.gravEquasion(a,b,distBetween);
				these2Dir = Object.gravDirection(a,b);
				gravityMagnitude += these2Grav;
				gravityDirection += these2Dir;
			}
			
			a.move(gravityMagnitude, gravityDirection);
			
		}
		
	}

}
