package spacePhys1;

import java.awt.Color;
import java.awt.Graphics;

public class Matter {
	//MOVEMENT VARS
	// xLoc and yLoc will be calculated by move() and set in constructor
	double xLoc;
	double yLoc;
	double xSpeed;
	double ySpeed;
	double angle;

	//OTHER VARS
	double mass;
	
	public Matter() {
		
	}
	
	public Matter(double xLoc,double yLoc, double mass) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.mass = mass;
	}
	
	
	public double getxLoc() {
		return xLoc;
	}

	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
	}

	public double getyLoc() {
		return yLoc;
	}

	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void paintMatter(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillOval((int)this.xLoc,(int)-this.yLoc,20,20);
		
		
	}
	
	// Movement methods
	public void move(double gravMag, double gravDir) {
		
		this.xLoc += (gravMag * Math.cos(gravDir));
		this.yLoc += (gravMag * Math.sin(gravDir));
	}
	
	public double distanceBetween(Matter a, Matter b){
		
		return Math.sqrt(  (a.getxLoc() + b.getxLoc())*(a.getxLoc() + b.getxLoc()) 
				+ (a.getyLoc() + b.getyLoc())*(a.getyLoc() + b.getyLoc()));
	}
	
	public static double gravEquasion(Matter a, Matter b, double r){
		double force;
		
		force = ( 9.8*(a.getMass()*b.getMass()) / (r*r) );
		return force;
	}
	
	public static double gravDirection(Matter a, Matter b){
		double degrees;
		double cXLoc;
		double cYLoc;
		
		cXLoc = b.getxLoc() - a.getxLoc();
		cYLoc = b.getyLoc() - a.getyLoc();
		
		degrees = Math.toDegrees(Math.atan(cXLoc/cYLoc));
		
		return degrees;
	}

}
