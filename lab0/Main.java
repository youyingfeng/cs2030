import java.util.Scanner;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double x1 = sc.nextDouble();
		double y1 = sc.nextDouble();
		double x2 = sc.nextDouble();
		double y2 = sc.nextDouble();
		double radius = sc.nextDouble();
		
		return createCircle(new Point(x1, y1), new Point(x2, y2), radius);
	}
}