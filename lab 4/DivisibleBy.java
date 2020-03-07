class DivisibleBy implements BooleanCondition<Integer>{
    private final int divisor;
    public DivisibleBy(int i){
        divisor = i;
    }
    public boolean test(Integer i){
        return i%divisor == 0;
    }
}
