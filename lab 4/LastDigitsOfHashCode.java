import java.lang.Math;
class LastDigitsOfHashCode implements Transformer<Object, Integer>{
    
    private final int digits;
    public LastDigitsOfHashCode(int digits){
        this.digits = digits;
    }

    public Integer transform(Object o){
        return Math.floorMod(Math.abs(o.hashCode()),(int) Math.pow(10,digits));
    }
    
}
