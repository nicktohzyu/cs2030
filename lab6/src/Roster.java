import java.util.Optional;

public class Roster extends KeyableMap<String, String, KeyableMap<String, String, KeyableMap<String, String, Assessment>>> {
    Roster(String s) {
        super(s);
    }

    Roster put(Student s) {
        internalMap.put(s.getKey(), s);
        return this;
    }

    public String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        Optional<String> gradeOptional = this.get(student).flatMap(stu -> stu.get(module)).flatMap(mod -> mod.get(assessment)).map(ass -> ass.getGrade());
        final String[] grade = new String[1];
        gradeOptional.ifPresentOrElse(s -> grade[0] = s, () -> {grade[0] = String.format("NoSuchRecordException: No such record: %s %s %s", student, module, assessment);});
        return grade[0];
    }

    public static void throwException(String student, String module, String assessment) throws NoSuchRecordException {
        throw new NoSuchRecordException(String.format("No such record: %s %s %s", student, module, assessment));
    }
}
