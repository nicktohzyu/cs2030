class Ba{
    String s = "";
    /*B(){
    s = "B";
    }*/
    Ba add(Ba b){
        s += b.s;
        return this;
    }
    /*B add(C c){
    s += c.s;
    return this;
    }*/
    public String toString(){
        return s;
    }
}
class C extends Ba{
    //String s = "";
    C(){
        s = "C";
    }
   /*C add(B b){
   s += b.s;
   return this;
   }
   C add(C c){
   s += c.s;
   return this;
   }
   public String toString(){
   return s;
   }*/
}
class B extends Ba{
    B(){
        s = "B";
    }
}
