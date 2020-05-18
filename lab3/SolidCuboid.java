class SolidCuboid extends Cuboid implements SolidShape {
    final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }

    public SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, super.width, super.length, density);
    }

    public SolidCuboid setWidth(double width) {
        return new SolidCuboid(super.height, width, super.length, density);
    }

    public SolidCuboid setLength(double length) {
        return new SolidCuboid(super.height, super.width, length, density);
    }

    public double getMass() {
        return super.getVolume() * density;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return ("SolidCuboid [" + String.format("%.2f", super.height) + " x "
               + String.format("%.2f", super.width) + " x "
               + String.format("%.2f", super.length) + "] with a mass of "
               + String.format("%.2f", getMass()));
    }
}
