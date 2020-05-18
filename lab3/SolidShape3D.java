class SolidShape3D {
    protected final Shape3D shape3D;
    protected final Material material;

    SolidShape3D(Shape3D shape3D, Material material) {
        this.shape3D = shape3D;
        this.material = material;
    }

    double getMass() {
        return shape3D.getVolume() * material.getDensity();
    }

    double getDensity() {
        return material.getDensity();
    }

    @Override
    public String toString() {
        return "Solid" + shape3D + " with a mass of " + String.format("%.2f", getMass());
    }
}
