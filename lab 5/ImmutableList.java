import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.*; 

class ImmutableList<T>{
    ArrayList<T> internalList;

    @SafeVarargs
    ImmutableList(T... a){
        internalList = new ArrayList<T>(Arrays.asList(a));
    }

    ImmutableList(List<T> l){
        internalList = new ArrayList<T>(l);
    }

    ImmutableList<T> add(T e){
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.add(e);
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> remove(T e){
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.remove(e);
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> replace(T e, T ne){
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.replaceAll(ie -> ie.equals(e) ? ne : ie );
        return new ImmutableList<T>(temp);
    }
    
    ImmutableList<T> filter(Predicate<? super T> pred){
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.removeIf(e -> !pred.test(e));
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> limit(long n){
        if(n < 0){
            throw new IllegalArgumentException("limit size < 0");
        }
        List<T> temp = internalList.subList(0, Math.min((int) n, internalList.size()));
        return new ImmutableList<T>(temp);
    }
    <R> ImmutableList<R> map(Function<? super T, ? extends R> func){
        ArrayList<R> temp = new ArrayList<R>();
        internalList.forEach(e -> temp.add(func.apply(e)));
        return new ImmutableList<R>(temp);
    }

    ImmutableList<T> sorted(){
        if(internalList.isEmpty()){
            return this;
        }
        if(!(internalList.get(0) instanceof Comparable)){
            throw new IllegalStateException("List elements do not implement Comparable");
        }
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.sort(null);
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> sorted(Comparator<? super T> comp){
        if(internalList.isEmpty()){
            return this;
        }
        if(!(internalList.get(0) instanceof Comparable)){
            throw new IllegalStateException("List elements do not implement Comparable");
        }
        if(comp == null){
            throw new NullPointerException("Comparator is null");
        }
        ArrayList<T> temp = new ArrayList<T>(internalList);
        temp.sort(comp);
        return new ImmutableList<T>(temp);
    }
    
    Object[] toArray(){
        return internalList.toArray();
    }

    <U> U[] toArray(U[] arr){
        try{
            return internalList.toArray(arr);
        } catch(ArrayStoreException e){
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        } catch (NullPointerException e){
            throw new NullPointerException("Input array cannot be null");
        }
    }

    @Override
    public String toString(){
        return Arrays.toString(internalList.toArray());
    }
}
