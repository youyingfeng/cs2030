class Cuboid implements Shape3D {
    final double height;
    final double width;
    final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Cuboid setHeight(double height) {
        return new Cuboid(height, this.width, this.length);
    }

    public Cuboid setWidth(double width) {
        return new Cuboid(this.height, width, this.length);
    }

    public Cuboid setLength(double length) {
        return new Cuboid(this.height, this.width, length);
    }

    public double getVolume() {
        return (height * width * length);
    }

    public double getSurfaceArea() {
        double surfaceArea = 2 * ((height * width) + (width * length) + (length * height));
        return surfaceArea;
    }

    @Override
    public String toString() {
        return ("Cuboid [" + String.format("%.2f", height) + " x "
               + String.format("%.2f", width) + " x "
               + String.format("%.2f", length) + "]");
    }
}
