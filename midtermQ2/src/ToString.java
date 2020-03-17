
class ToString<S> implements TypeCaster<S, String>{

    public String cast(S s){

        return s.toString();

    }

}