class SolidCuboid{
    final double height, width, length, density;

    public SolidCuboid(double height, double width, double length, double density){
        this.height = height;
        this.width = width;
        this.length = length;
        this.density = density;
    }
    
    public SolidCuboid setHeight(double height){
        return new SolidCuboid(height, width, length, density);
    }

    public SolidCuboid setWidth(double width){
        return new SolidCuboid(height, width, length, density);
    }

    public SolidCuboid setLength(double length){
        return new SolidCuboid(height, width, length, density);
    }


    public double getVolume(){
        return height*width*length;
    }

    public double getSurfaceArea(){
        return 2*(length*width + width*height + length*height);
    }
    
    public double getDensity(){
        return density;
    }
    
    public double getMass(){
        return density*getVolume();
    }

    public String toString(){
        return String.format("SolidCuboid [%.2f x %.2f x %.2f] with a mass of %.2f", height, width, length, getMass());
    }
}
