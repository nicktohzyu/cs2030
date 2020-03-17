import java.util.*;
class ListCaster{

    static <S, T> List<T> castList(List<S> slist, TypeCaster<S, T> typecaster){

        ArrayList<T> tlist= new ArrayList<T>();

        for(S s : slist){

            tlist.add(typecaster.cast(s));

        }

        return tlist

    }

}