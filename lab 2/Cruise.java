class Cruise{
    private final String identifier;
    private final int arrivalTime;
    private final int nLoaders;
    private final int serviceTime;

    public Cruise(String identifier, int arrivalTime, int nLoaders, int serviceTime){
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.nLoaders = nLoaders;
        this.serviceTime = serviceTime;
    }
    
    public int getServiceCompletionTime(){
        return getArrivalTime() + serviceTime;
    }

    public int getArrivalTime(){
        return arrivalTime%100 + arrivalTime/100 * 60;
    }

    public int getNumOfLoadersRequired(){
        return nLoaders;
    }

    public String toString(){
        return identifier + String.format("@%04d", arrivalTime);
    }
}
