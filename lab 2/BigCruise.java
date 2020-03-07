class BigCruise extends Cruise{
    private static final int LENGTH_PER_SERVER = 40;
    private static final int PASSENGERS_PER_TIME = 50;
    public BigCruise(String identifier, int arrivalTime, int length, int passengers){
        super(identifier, arrivalTime, (int) Math.ceil( (double) length/LENGTH_PER_SERVER), (int) Math.ceil( (double) passengers/PASSENGERS_PER_TIME));
    }
}
