package spacePhys1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class System extends Applet implements Runnable {
	//int width = 3840, height = 2160;
	int width = 1800, height = 1000;
	int numberOfObjects = 30;
	int gameTick = 0;
	double yy = 0;
	ArrayList<Label> labels = new ArrayList<Label>();
	
	Thread thread;
	ArrayList<Matter> matters = new ArrayList<Matter>();
	
	public void init(){
		this.resize(width, height);
		thread = new Thread(this);
		thread.start();		
		
		Label l1 = new Label();
		l1.setLocation(500, 500);
		l1.setBackground(Color.red);
		l1.setForeground(Color.BLUE);
		l1.hasFocus();
		l1.setVisible(true);
		l1.setSize(300, 300);
		add(l1);
		labels.add(l1);
		
		int topX = height;
		int left = width/2;
		for(Matter a : matters) {
			Label x = new Label();
			x.setLocation(topX, left);
		}

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
		labels.get(0).setText("Mass of sun = " + matters.get(0).getMass());
		labels.get(0).setSize(250, 30);

	}

	public void run() {		
		
//here is where you make your initial materials with a for loop}
		Matter sun = new Matter(0,0,100*1000000, true);		
		matters.add(sun);
		
//		Matter sun2 = new Matter(151,150,10*1000000, 0,0);		
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
		
		for(int x = 1 ; x < numberOfObjects; x++) {
			Matter tempObj = new Matter(Math.random()*width - width/2, Math.random()*height - height/2,
					(Math.random()*2)*10000 , Math.random()*1/2-.15,Math.random()*1/2-.15);
			
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
