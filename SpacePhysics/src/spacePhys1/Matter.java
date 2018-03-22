package spacePhys1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
//test
public class Matter {
	
	final double GRAV = .00000667;
	double xLoc;
	double yLoc;
	double mass;
	double xVel;
	double yVel;
	double centerX;
	double centerY;
	int radius;
	
	
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
	
	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	private double forceGrav(Matter a, Matter b){
		// Calculate the magnitude of force on Matter a
		double force = (GRAV * (a.getMass() * b.getMass()))/ Math.pow(distBetween(a,b),2);
		return force;
	}
	
	private double forceDir(Matter a, Matter b){
		// Calculate the direction the gravity of force will be on Matter a
		double dir = 0;
		
		dir = (Math.atan( Math.abs(a.getxLoc()-b.getxLoc()) / Math.abs(a.getyLoc()-b.getyLoc())));
		
		if (this.getxLoc() >= centerX && this.getyLoc() >= centerY){
			// Upper right
			dir -= Math.PI;
		}
		if (this.getxLoc()<= centerX && this.getyLoc() >= centerY){
			//upper left
			dir = 3/2*Math.PI - dir;
		}
		if (this.getxLoc() < centerX && this.getyLoc() < centerY){
			//lower left
			
		}
		if (this.getxLoc() > centerX && this.getyLoc() < centerY){
			// lower right
			dir = 1/2*Math.PI - dir;
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
		// I might handel both collisions at once so I might need to make a list of secondary
		// objects and continue ignore hasColission for those objects because they will collide
		// with the object that already colieded?
		for (Matter matter : matters) {
			if(this == matter){
				continue;
			} else{
				Force temp = new Force(forceGrav(this,matter), forceDir(this,matter));
				this.move(temp);
				this.reSize();
				if(hasColission(this, matter)) {
					collide(this,matter);
				}
				
			}
		}
	}
	
	private boolean hasColission(Matter matterA, Matter matterB) {
		// if we have a colission return true.
		
		// A collision would be if a's x +- radius includes b's xLoc +- radius
//		if ((matterA.getxLoc() - matterA.radius > matterB.getxLoc() &&
//				matterA.getxLoc() + matterA.radius < matterB.getxLoc()) &&
//				(matterA.getyLoc() - matterA.radius > matterB.getyLoc() &&
//						matterA.getyLoc() + matterA.radius < matterB.getyLoc())) {
//			return true;
//		}
		
		if (( matterB.getxLoc() - matterB.radius < matterA.getxLoc() + matterA.radius &&
			  matterB.getxLoc() - matterB.radius > matterA.getxLoc() - matterA.radius) &&
			  matterB.getyLoc() - matterB.radius < matterA.getyLoc() + matterA.radius &&
			  matterB.getyLoc() - matterB.radius > matterA.getyLoc() - matterA.radius) {
			return true;
		}
			
		return false;
	}
	
	private void collide(Matter matterA, Matter matterB) {
		// speed reduction because physics
		double speedReduce = 1/matterA.getMass()*matterB.getMass();
		matterA.setMass(matterA.getMass()+matterB.getMass());
		matterB = null;
		matterA.setxVel(matterA.getxVel()*speedReduce);
		matterA.setyVel(matterA.getyVel()*speedReduce);
		
	}
	
	private void reSize() {
		this.radius = (int) Math.sqrt(Math.sqrt(mass))/1;
		
		
	}
	private void move(Force a){
		double prevX = this.getxLoc();
		double prevY = this.getyLoc();
		this.setxLoc(xVel + this.getxLoc() + (a.getMagnitude() * Math.sin(a.getDirection()))/this.mass);
		this.setyLoc(yVel + this.getyLoc() +(a.getMagnitude() * Math.cos(a.getDirection()))/this.mass);
		
		xVel = this.getxLoc() - prevX;
		yVel = this.getyLoc() - prevY;
		
		
	}
	
	public void reCenter(ArrayList<Matter> matters) {
		centerX = matters.get(0).xLoc;
		centerY = matters.get(0).yLoc;
	}
	
	// CONSTRUCTORS TO END
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
	
	public Matter(double xLoc,double yLoc, double mass, boolean isSun) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.mass = mass;
		this.xLoc = centerX;
		this.yLoc = centerY;
	}
	
	public void paintMatter(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillOval((int)this.xLoc-this.radius/2,(int)-this.yLoc-this.radius/2,this.radius,this.radius);
		
	}
}
