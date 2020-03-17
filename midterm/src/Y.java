class Y {

    X x;

    Y(X x) {

        this.x = x;

    }

    public String toString() {

        return String.format("Y->%s", x.toString());

    }

}