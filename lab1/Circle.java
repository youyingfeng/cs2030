class Circle{
    protected Point center;
    protected double radius;

    private Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public static Circle getCircle(Point center, double radius){
        if (radius <= 0){
            return null;
        } else {
            return new Circle(center, radius);
        }
    }

    public int contains(Point[] pointArray){
		int len = pointArray.length;
		int counter = 0;
		for (int i = 0; i < len; i++){
			if (center.distanceTo(pointArray[i]) <= radius){
				counter = counter + 1;
			}
		}
		return counter;
	}

    @Override
    public String toString(){
        return ("circle of radius " + String.format("%.1f", radius) + " centered at " + center);
    }



}
