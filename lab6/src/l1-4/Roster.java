public class Roster extends KeyableMap<String, String, KeyableMap<String, String, KeyableMap<String, String, Assessment>>> {
    Roster(String s) {
        super(s);
    }

    Roster put(Student s) {
        internalMap.put(s.getKey(), s);
        return this;
    }

    public String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        try{
            return internalMap.get(student).get(module).get(assessment).getGrade();
        } catch(Exception e){
            throw new NoSuchRecordException(String.format("No such record: %s %s %s", student, module, assessment));
        }
    }
}
