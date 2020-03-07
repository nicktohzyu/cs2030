class SolidSphere{
    final double radius, density;; 

    public SolidSphere(double radius, double density){
        this.radius = radius;
        this.density = density;
    }
    
    public SolidSphere setRadius(double radius){
        return new SolidSphere(radius, density);
    }
    
    public double getDensity(){
        return density;
    }

    public double getVolume(){
        return Math.PI * radius * radius * radius * 4.0 / 3.0;
    }

    public double getSurfaceArea(){
        return Math.PI * radius * radius * 4.0;
    }

    public double getMass(){
        return density*getVolume();
    }
    
    public String toString(){
        return String.format("SolidSphere [%.2f] with a mass of %.2f", radius, getMass());
    }
}
