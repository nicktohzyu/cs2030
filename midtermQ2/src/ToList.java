import java.util.*;

class ToList<T> implements TypeCaster<T[], List<T>>{

    public List<T> cast(T[] tarray){

        return new ArrayList<T>(Arrays.asList(tarray));

    }

}