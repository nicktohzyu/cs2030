class SmallCruise extends Cruise{
    private static final int loadersNeeded = 1;
    private static final int serviceTime = 30;
    public SmallCruise(String identifier, int arrivalTime){
        super(identifier, arrivalTime, loadersNeeded, serviceTime);
    }
}
