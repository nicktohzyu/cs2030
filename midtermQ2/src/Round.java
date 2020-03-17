class Round implements TypeCaster<Double, Integer>{

    public Integer cast(Double d){

        return (int) Math.round(d);

    }

}