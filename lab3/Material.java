class Material {
    protected final String material;
    protected final double density;

    Material(String material, double density) {
        this.material = material;
        this.density = density;
    }

    double getDensity() {
        return density;
    }

}
