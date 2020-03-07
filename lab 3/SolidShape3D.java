class SolidShape3D{
    final Material material;
    final Shape3D shape;
    
    public SolidShape3D(Shape3D shape, Material material){
        this.material = material;
        this.shape = shape;
    }

    public double getMass(){
        return shape.getVolume() * material.getDensity();
    }
    public double getDensity(){
        return material.getDensity();
    }

    @Override
    public String toString(){
        return String.format("Solid%s with a mass of %.2f", shape.toString(), getMass());
    }
}
