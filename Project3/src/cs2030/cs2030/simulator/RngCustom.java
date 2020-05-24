package cs2030.simulator;

public class RngCustom {
    private final RandomGenerator randomGenerator;
    public RngCustom(int seed, double lambda, double mu, double rho) {
        this.randomGenerator = new RandomGenerator( seed,  lambda,  mu,  rho);
    }

    public double genInterArrivalTime() {
        return randomGenerator.genInterArrivalTime();
    }

    public double genServiceTime() {
        return randomGenerator.genServiceTime();
    }

    public double genRandomRest() {
        return randomGenerator.genRandomRest();
    }

    public double genRestPeriod() {
        return randomGenerator.genRestPeriod();
    }

    public double genCustomerType() {
        return randomGenerator.genCustomerType();
    }
}
