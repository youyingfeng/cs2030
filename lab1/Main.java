import java.util.Scanner;

class Main{
	public static Circle createCircle(Point point1, Point point2, double radius){
        double opp = point1.distanceTo(point2)/2;
        double hyp = radius;
        double arcAngle = Math.asin(opp/hyp);
		double distance = radius * Math.cos(arcAngle);
		double angleNormal = point1.angleTo(point2) + Math.PI/2; //corrected for normal
		Point midPoint = point1.midPoint(point2);
		
		if (opp > radius || opp == 0){
			return null;
		}
			
		
        return Circle.getCircle(midPoint.moveTo(angleNormal, distance), radius);
        
    }
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int numberOfPoints = input.nextInt();
		Point[] points = new Point[numberOfPoints];
		
		for (int i = 0; i < numberOfPoints; i++){
			double x = input.nextDouble();
			double y = input.nextDouble();
			points[i] = new Point(x, y);
		}
		
		int max = 0;
		
		for (int i = 0; i < numberOfPoints; i++){
			for (int j = 0; j < numberOfPoints; j++){
				if (points[i].distanceTo(points[j]) > 2){
					continue;
				}
				Circle circle = createCircle(points[i], points[j], 1.0);
				if (circle == null){
					continue;
				}
				int coveredPoints = circle.contains(points);
				
				if (coveredPoints > max){
					max = coveredPoints; 
				}
			}
		}
		
		System.out.println("Maximum Disc Coverage: " + max);
	}
	
}