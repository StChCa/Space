package spacePhys1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class System extends Applet implements Runnable {
	int width = 3840, height = 2160;
	int numberOfObjects = 200;
	int gameTick = 0;
	
	Thread thread;
	ArrayList<Matter> matters = new ArrayList<Matter>();
	
	public void init(){
		this.resize(width, height);
		thread = new Thread(this);
		thread.start();		

	}

	public void paint(Graphics g){
		
		setBackground (Color.black);
		g.translate(width/2, height/2);
		
		
		for(Matter a : matters) {
			a.paintMatter(g);
		}
		
}

	
	public void update() {

		//moveObjs();
		for (Matter matter : matters) {
			matter.moveLoop(matters);
			matter.reCenter(matters);
		}
		

	}

	public void run() {		
		
//here is where you make your initial materials with a for loop}
		
		Matter sun = new Matter(0,0,109*1000000, true);		
		matters.add(sun);
		
//		Matter sun2 = new Matter(1200,900,3*10000, 1,-1);		
//		matters.add(sun2);
//
//		Matter sun3 = new Matter(-300,300,3*10000,-1,-1.3);		
//		matters.add(sun3);
//		
//		Matter sun4 = new Matter(-300,-300,3*10000,1,-1);		
//		matters.add(sun4);
//		
//		Matter sun5 = new Matter(300,-300,3*10000,1,1);		
//		matters.add(sun5);
		
		for(int x = 2 ; x < numberOfObjects; x++) {
			Matter tempObj = new Matter(Math.random()*width - width/2, Math.random()*height - height/2,
					(Math.random()*5)*10000 , Math.random()*1/2-.25,Math.random()*1/2-.25);
			
			matters.add(tempObj);
		}
		
		for(;;){
			//This is what loops the system to simulate motion
			update();
			repaint();

			try {
				//Change sleep time to speed or slow simulation.
				Thread.sleep(20);
				gameTick++;
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
