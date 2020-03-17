

class C{

    String s = "";

    C(){

        s = "C";

    }

    C add(B b){

        s += b.s;

        return this;

    }

    C add(C c){

        s += c.s;

        return this;

    }

    public String toString(){

        return s;

    }

}

