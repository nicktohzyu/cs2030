import java.util.Optional;

public class Q1 {
    boolean canEnter(NRIC nric) throws InvalidArgumentException {
        if (nric == null) {
            throw new InvalidArgumentException();
        }
        Integer lastDigit = nric.lastDigit();
        if (lastDigit == null) {
            throw new InvalidArgumentException();
        }
        if (MyCalendar.currDate() % 2 == lastDigit % 2) {
            return true;
        } else {
            return false;
        }
    }
    boolean canEnterOpt(NRIC nric) throws InvalidArgumentException {
        try{return Optional.ofNullable(Optional.ofNullable(Optional.ofNullable(nric).orElseThrow(() -> new InvalidArgumentException()).lastDigit())
                .orElseThrow(() -> new InvalidArgumentException()))
                .map(x -> x % 2)
                .equals(MyCalendar.currDate() % 2);
        } catch(Exception e){

        }
        return true;
    }
    private static class MyCalendar {
        static int currDate() {
            return 0;
        }
    }
}

class NRIC {
    Integer lastDigit() {
        return 0;
    }
}