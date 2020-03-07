class LongerThan implements BooleanCondition<String>{
    private final int len;
    public LongerThan(int i){
        len = i;
    }
    public boolean test(String s){
        return s.length() > len;
    }
}
    
