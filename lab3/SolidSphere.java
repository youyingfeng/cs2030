class SolidSphere extends Sphere implements SolidShape {
    protected final double density;
    
    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    @Override
    SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, this.density);
    }

    public double getDensity() {
        return density;
    }

    public double getMass() {
        return super.getVolume() * density;
    }

    @Override
    public String toString() {
        return ("SolidSphere [" + String.format("%.2f", super.radius) + "] with a mass of "
                + String.format("%.2f", getMass()));
    }
}
