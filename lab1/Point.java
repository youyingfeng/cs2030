class Point{
    protected double x;
    protected double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point midPoint(Point point){
        double newX = (this.x + point.x)/2;
        double newY = (this.y + point.y)/2;
        return new Point(newX, newY);
    }
    
    public double angleTo(Point point){
        double opp = point.y - this.y;
        double adj = point.x - this.x;
        

        return Math.atan2(opp, adj);
    } //this does not return the proper angle for the 3rd test case

    public Point moveTo(double angle, double distance){
        double newX = this.x + distance * Math.cos(angle);
        double newY = this.y + distance * Math.sin(angle);
        return new Point(newX, newY);
    }

    public double distanceTo(Point point){
        double newX = point.x - this.x;
        double newY = point.y - this.y;
        return Math.sqrt(newX * newX + newY * newY);
    }

    
    @Override
    public String toString(){
        return ("point (" + String.format("%.3f", x) + ", " + String.format("%.3f", y) + ")");
    }

}

