class B{

    String s = "";

    B(){

        s = "B";

    }

    B add(B b){

        s += b.s;

        return this;

    }

    B add(C c){

        s += c.s;

        return this;

    }

    public String toString(){

        return s;

    }

}

