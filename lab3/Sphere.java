class Sphere implements Shape3D {
    protected double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    Sphere setRadius(double radius) {
        return new Sphere(radius);
    }

    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    public double getSurfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Sphere [" + String.format("%.2f", radius) + "]";
    }
}
