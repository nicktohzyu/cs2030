import java.util.*;

class A {

    String s = "";

    A(int i) {

        s = String.format("[A:%d]", i);

    }

    A(String s) {

        this.s = s;

    }

    A next(int i) {

        return new A(s + String.format("[A:%d]", i));

    }

    public String toString() {

        return s;

    }

}

