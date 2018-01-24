package spacePhys1;

import java.awt.Color;
import java.awt.Graphics;

public class Object {

	// xLoc and yLoc will be calculated by move() and set in constructor
	double xLoc;
	double yLoc;
	
	//This is my momentum vector
	double vectorTheta;
	double vectorMagnitude;
	
	public Object() {
		
	}
	
	public Object(double xLoc,double yLoc,double vectorTheta,double vectorMagnitude) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.vectorTheta = vectorTheta;
		this.vectorMagnitude = vectorMagnitude;
	}
	
	
	public void paintObject(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillOval((int)this.xLoc,(int)this.yLoc,20,20);
		
	}
	
	
	public void move(int centerX, int centerY) {
		this.yLoc++;
	}
}
