class Point{
	double x = 0;
	double y = 0;
	
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	midPoint(Point point){
		double newX = (this.x + point.x)/2;
		double newY = (this.y + point.y)/2;
		return new Point(newX, newY);
	}

	angleTo(Point point){
		double opp = point.y - this.y;
		double adj = point.x - this.x;
		double angle = Math.atan(opp/adj);
		return angle;
	}

	moveTo(double angle, double d){
		newX = this.x + d*Math.cos(angle);
		newY = this.y + d*Math.sin(angle);
		return new Point(newX, newY);
	}

}