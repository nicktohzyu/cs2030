import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Loader[] loaders = new Loader[300];
        Cruise c;
        while(n-- > 0){
            String id = s.next();
            if(id.charAt(0)=='S'){
                c = new SmallCruise(id, s.nextInt());
            } else {
                c = new BigCruise(id, s.nextInt(), s.nextInt(), s.nextInt());
            } 
            int loadersNeeded = c.getNumOfLoadersRequired();
            while(loadersNeeded-- > 0){
                for(int i = 0; i < 300; i++){
                    if(loaders[i] == null || loaders[i].canServe(c)){
                        loaders[i] = new Loader(i+1, c);
                        System.out.println(loaders[i]);
                        break;
                    }
                }
            }
        }
        s.close();
    }
}
