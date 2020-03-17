import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchRecordException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster roster = new Roster("roster");
        while (n-- > 0) {
            String studentName = sc.next();
            KeyableMap<String, String, KeyableMap<String, String, Assessment>> student = roster.get(studentName);
            if(!exists(student)){
                student = new Student(studentName);
            }
            String moduleName = sc.next();
            KeyableMap<String, String, Assessment> module = student.get(moduleName);
            if(!exists(module)){
                module = new Module(moduleName);
            }
            Assessment assessment = new Assessment(sc.next(), sc.next());
            module.put(assessment);
            student.put(module);
            roster.put(student);
        }
        while(sc.hasNext()){
            try{
                System.out.println(roster.getGrade(sc.next(), sc.next(), sc.next()));
            } catch(Exception e){
                System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
            }
        }
    }

    public static void l2() {
        System.out.println(new Assessment("Lab1", "B"));
        System.out.println(new Assessment("Lab1", "B").getGrade());
        System.out.println(new Assessment("Lab1", "B").getKey());
        System.out.println(new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2", "A+")));
        System.out.println(new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2", "A+")).get("Lab1"));
        System.out.println(new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2", "A+")).get("Lab2"));
        System.out.println(new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2", "A+")).get("Lab3"));
        System.out.println(new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2", "A+")).get("Lab1").getGrade());
        System.out.println(new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))));
        System.out.println(new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040"));
        System.out.println(new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040").get("Lab1"));
        System.out.println(new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040").get("Lab1").getGrade());
        Student natasha = new Student("Natasha");
        System.out.println(natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B"))));
        System.out.println(natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2", "C"))));
        Student tony = new Student("Tony");
        System.out.println(tony.put(new Module("CS1231").put(new Assessment("Test", "A-"))));
        System.out.println(tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F"))));
    }

    public static void l3() throws NoSuchRecordException {
        Student natasha = new Student("Natasha");
        natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")));
        natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2", "C")));
        Student tony = new Student("Tony");
        tony.put(new Module("CS1231").put(new Assessment("Test", "A-")));
        tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F")));
        Roster roster = new Roster("AY1920").put(natasha).put(tony);
        System.out.println(roster);
        roster.get("Tony").get("CS1231").get("Test").getGrade();
        roster.get("Natasha").get("CS2040").get("Lab1").getGrade();
        roster.getGrade("Tony", "CS1231", "Test");
        roster.getGrade("Natasha", "CS2040", "Lab1");
        try {
            roster.getGrade("Steve", "CS1010", "Lab1");
        } catch (NoSuchRecordException e) {
            System.out.println(e.getMessage());
        }
        try {
            roster.getGrade("Tony", "CS1231", "Exam");
        } catch (NoSuchRecordException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean exists(Object o){
        try{
            o.toString();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
