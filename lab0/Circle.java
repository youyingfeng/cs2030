class Circle{
	Point centre;
	double radius = 0;

	public Circle(Point centre, double radius){
		this.centre = centre;
		this.radius = radius;
	}

	public getCircle(Circle circle, double centre){
		
	}

	public createCircle(Point point1, Point point2, double radius){
		//takes in two points
		//find the midpoint
		//then move midpoint using point

		Point mid = point1.midPoint(point2);
		double distance = Math.sqrt(point1.x * point1.x + point2.x + point2.x)/2;
		double angle = Math.asin(distance/radius);
		
		Point newCentre = point1.moveTo(angle, radius);
		return newCentre;
	}

}
