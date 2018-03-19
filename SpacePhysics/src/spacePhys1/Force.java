package spacePhys1;

public class Force {

	double magnitude;
	double direction;
	
	public double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	Force(double mag, double dir){
		magnitude = mag;
		direction = dir;
	}
}
