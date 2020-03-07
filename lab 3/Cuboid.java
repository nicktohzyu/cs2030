class Cuboid implements Shape3D{
    final double height, width, length;

    public Cuboid(double height, double width, double length){
        this.height = height;
        this.width = width;
        this.length = length;
    }
    
    public Cuboid setHeight(double height){
        return new Cuboid(height, width, length);
    }

    public Cuboid setWidth(double width){
        return new Cuboid(height, width, length);
    }

    public Cuboid setLength(double length){
        return new Cuboid(height, width, length);
    }


    @Override
    public double getVolume(){
        return height*width*length;
    }

    public double getSurfaceArea(){
        return 2*(length*width + width*height + length*height);
    }
    
    @Override
    public String toString(){
        return String.format("Cuboid [%.2f x %.2f x %.2f]", height, width, length);
    }
}
