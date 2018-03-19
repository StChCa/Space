package spacePhys1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
//test
public class Matter {
	
	final double GRAV = .00667;
	double xLoc;
	double yLoc;
	double mass;
	double xVel;
	double yVel;
	
	
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
	
	public double forceGrav(Matter a, Matter b){
		// Calculate teh magnitude of force on Matter a
		double force = (GRAV * (a.getMass() * b.getMass()))/ Math.pow(distBetween(a,b),2);
		return force;
	}
	
	public double forceDir(Matter a, Matter b){
		// Calculate the direction the gravity of force will be on Matter a
		double dir = 0;
		
		dir = (Math.atan( Math.abs(a.getxLoc()-b.getxLoc()) / Math.abs(a.getyLoc()-b.getyLoc())));
		
		if (this.getxLoc() >= 0 && this.getyLoc() >= 0){
			dir -= Math.PI;
		}
		if (this.getxLoc()<= 0 && this.getyLoc() >= 0){
			dir += Math.PI/2;
		}
		if (this.getxLoc() < 0 && this.getyLoc() < 0){
			
		}
		if (this.getxLoc() > 0 && this.getyLoc() < 0){
			dir -= Math.PI/2;
		}
		
		
		return dir;
	}

	
	private double distBetween(Matter a, Matter b) {
		// Calculate the distance between 2 matter objects using...
		// a sqrd + b sqrd = c sqrd
		double dist = Math.abs(
								Math.sqrt(
										Math.pow(a.getyLoc()-b.getyLoc(), 2) + 
										Math.pow(a.getxLoc()-b.getxLoc(), 2) )
								);
		return dist;
	}
	
	public void moveLoop(ArrayList<Matter> matters){
		
		for (Matter matter : matters) {
			if(this == matter){
				continue;
			} else{
				Force temp = new Force(forceGrav(this,matter), forceDir(this,matter));
				this.move(temp);
			}
		}
	}
	
	public void move(Force a){
		this.setxLoc(xVel + this.getxLoc() + (a.getMagnitude() * Math.sin(a.getDirection()))/this.mass);
		this.setyLoc(yVel + this.getyLoc() +(a.getMagnitude() * Math.cos(a.getDirection()))/this.mass); 
	}
	
	public Matter() {
		
	}
	
	public Matter(double xLoc,double yLoc, double mass) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.mass = mass;
	}
	
	public Matter(double xLoc,double yLoc, double mass, double xVel, double yVel) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.mass = mass;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void paintMatter(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillOval((int)this.xLoc,(int)-this.yLoc,20,20);
		
	}

}
