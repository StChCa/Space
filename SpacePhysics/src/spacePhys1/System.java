package spacePhys1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class System extends Applet implements Runnable {
	int width = 3840, height = 2160;
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
		
		for(Object x : objects) {
			x.paintObject(g);
		}
		
}

	
	public void update() {
		//calculate the weighted center point of gravity
		int centerX = 0;
		int centerY = 0;
		//apply the acceleratoin to each object toward the central gravity point and move
		for(Object x : objects) {
			x.move(centerX, centerY);
		}
	}

	public void run() {		
		
//here is where you make your initial materials with a for loop}
		//Object sun = new Object(0,0,0,0);		
		//objects.add(sun);
		
		Object testObj = new Object(300,300,20,20);
		objects.add(testObj);
		
		for(int x = 2 ; x < numberOfObjects; x++) {
			//Use this space to create my list of objects
			
		}
		
		for(;;){
			//This is what loops the system to simulate motion
			update();
			repaint();

			try {
				//Change sleep time to speed or slow simulation.
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
