import java.util.EnumSet;

enum Toppings {BOBA, ALOE, AIYU};

class BubbleTea {

    private final int blackTea;

    private final int machaTea;

    private final int lycheeSyrup;

    private final int soyMilk;

    private final EnumSet<Toppings> toppings;

    public BubbleTea() {

        this.blackTea = 0;

        this.machaTea = 0;

        this.lycheeSyrup = 0;

        this.soyMilk = 0;

        toppings = new EnumSet<Toppings>();

    }

    private BubbleTea(int blackTea, int machaTea, int lycheeSyrup, int soyMilk, Enumset<Toppings> toppings) {

        this.blackTea = blackTea;

        this.machaTea = machaTea;

        this.lycheeSyrup = lycheeSyrup;

        this.soyMilk = soyMilk;

        this.toppings = toppings;

    }

    public BubbleTea addBlackTea(int amount) {

        return new BubbleTea(blackTea + amount, machaTea, lycheeSyrup, soyMilk,

                toppings);

    }

    public BubbleTea addMachaTea(int amount) {

        return new BubbleTea(blackTea, machaTea + amount, lycheeSyrup, soyMilk,

                toppings);

    }

    public BubbleTea addLycheeSyrup(int amount) {

        return new BubbleTea(blackTea, machaTea, lycheeSyrup + amount, soyMilk,

                toppings);

    }

    public BubbleTea addSoyMilk(int amount) {

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk + amount,

                toppings);

    }

    public BubbleTea addToppings(EnumSet<Toppings> addedToppings) {

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk,

                toppings.clone().addAll(addedToppings));

    }

}



