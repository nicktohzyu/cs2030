class Sphere{
    final double radius; 

    public Sphere(double radius){
        this.radius = radius;
    }
    
    public Sphere setRadius(double radius){
        return new Sphere(radius);
    }

    public double getVolume(){
        return Math.PI * radius * radius * radius * 4.0 / 3.0;
    }

    public double getSurfaceArea(){
        return Math.PI * radius * radius * 4.0;
    }
    
    public String toString(){
        return String.format("Sphere [%.2f]", radius);
    }
}
